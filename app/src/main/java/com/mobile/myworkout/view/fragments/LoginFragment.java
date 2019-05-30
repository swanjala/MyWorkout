package com.mobile.myworkout.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.myworkout.R;
import com.mobile.myworkout.model.datamodel.UserModel;
import com.mobile.myworkout.view.navigation.FragmentNavigator;
import com.mobile.myworkout.viewmodel.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment {

    @BindView(R.id.tv_email_address)
    TextView tv_email;

    @BindView(R.id.tv_password)
    TextView tv_password;

    @BindView(R.id.img_login)
    ImageView im_login;

    @BindView(R.id.img_cancel)
    ImageView im_cancel;
    FragmentNavigator fragmentNavigator = new FragmentNavigator();
    private UserViewModel userViewModel;

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_login, viewGroup,
                false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.userViewModel = ViewModelProviders
                .of(this)
                .get(UserViewModel.class);
        UserModel userModel = new UserModel();

        if (getArguments() != null) {
            tv_email.setText(getArguments().getString("email"));
        }


        im_login.setOnClickListener(v -> {

            String email = tv_email.getText().toString();
            String password = tv_password.getText().toString();
            userModel.setEmail(email);
            userModel.setPassword(password);

            userViewModel.loginUserWithCredentials(userModel);


        });

        im_cancel.setOnClickListener(v -> {

            if (!fragmentNavigator.fragmentLoader(new HomeFragment(),
                    getFragmentManager(), null)) {
                Toast.makeText(getContext(),
                        "Unable to load the home page",
                        Toast.LENGTH_LONG).show();

            }
        });

    }

}
