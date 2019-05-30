package com.mobile.myworkout.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.myworkout.R;
import com.mobile.myworkout.model.datamodel.UserModel;
import com.mobile.myworkout.view.utils.InputValidations;
import com.mobile.myworkout.viewmodel.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class RegistrationFragment extends Fragment {

    @BindView(R.id.img_cancel_registration)
    ImageView backPressImage;

    @BindView(R.id.img_signup)
    ImageView signUpImage;

    @BindView(R.id.tv_email_address)
    TextView tv_email_address;

    @BindView(R.id.tv_password)
    TextView tv_password;
    UserModel userModel = new UserModel();
    private String userEmail, password;
    private String checkedGender = "Female";

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater,
                             ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_registration,
                viewGroup, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FragmentManager fragmentManager = getFragmentManager();


        UserViewModel userViewModel = ViewModelProviders
                .of(this)
                .get(UserViewModel.class);

        if (getArguments() != null) {
            tv_email_address.setText(getArguments().getString("email"));
        }


        signUpImage.setOnClickListener(v -> {


            userEmail = tv_email_address.getText().toString();

            if (new InputValidations().isValidEmail(userEmail)) {

                userModel.setEmail(userEmail);

            } else {
                Toast.makeText(getContext(), "Enter Valid email", Toast.LENGTH_LONG).show();
            }


            password = tv_password.getText().toString();

            if (password.equals("")) {
                Toast.makeText(getContext(), "Enter password", Toast.LENGTH_LONG).show();
            } else if (password.length() < 6) {
                Toast.makeText(getContext(),
                        "Enter password more than 6 characters", Toast.LENGTH_LONG).show();

            } else {
                userModel.setPassword(password);
                userModel.setGender(checkedGender);
                userViewModel.createNewUser(userModel);
            }

        });

        backPressImage.setOnClickListener(v -> {

            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor editor = preferences.edit();

            editor.putBoolean("email_availability", false);
            editor.commit();

            fragmentManager.popBackStack();

        });

    }

    @OnCheckedChanged({R.id.rb_female, R.id.rb_male})
    public void onRadioButtonCheckChanged(CompoundButton button,
                                          boolean checked) {
        if (checked) {
            switch (button.getId()) {

                case R.id.rb_male:
                    this.checkedGender = "Male";
                    break;

                case R.id.rb_female:
                    this.checkedGender = "Female";
                    break;

            }
        }
    }

}

