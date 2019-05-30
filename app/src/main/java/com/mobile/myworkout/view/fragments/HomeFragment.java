package com.mobile.myworkout.view.fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mobile.myworkout.R;
import com.mobile.myworkout.view.utils.InputValidations;
import com.mobile.myworkout.viewmodel.UserViewModel;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.tv_email_address)
    EditText tv_email_address;

    @BindView(R.id.cl_next)
    ConstraintLayout navigation_container;

    @BindView(R.id.pb_home)
    ProgressBar home_progressbar;

    private String emailAddress;


    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater,
                             ViewGroup viewContainer,
                             Bundle savedInstanceState) {

        View view = layoutInflater.inflate(R.layout.fragment_main,
                viewContainer, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UserViewModel userViewModel = ViewModelProviders
                .of(this)
                .get(UserViewModel.class);

        home_progressbar.setVisibility(View.GONE);
        home_progressbar.setIndeterminate(true);


        navigation_container.setOnClickListener(v -> {
            emailAddress = tv_email_address.getText().toString();

            if (new InputValidations().isValidEmail(emailAddress)) {

                try {
                    new EmailChecker(emailAddress)
                            .execute(userViewModel).get();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(getContext(), this.getString(R.string.email_valid_string),
                        Toast.LENGTH_LONG).show();
            }

        });

    }

    @SuppressLint("StaticFieldLeak")
    private class EmailChecker extends AsyncTask<UserViewModel, Void, Boolean> {

        String mEmailAddress;

        EmailChecker(String email) {
            this.mEmailAddress = email;
        }

        @Override
        protected Boolean doInBackground(UserViewModel... params) {

            params[0].findUserEmail(mEmailAddress, getFragmentManager());

            return true;
        }

        @Override
        protected void onPreExecute() {
            home_progressbar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(Boolean complete) {
            home_progressbar.setVisibility(View.GONE);
        }

    }

}
