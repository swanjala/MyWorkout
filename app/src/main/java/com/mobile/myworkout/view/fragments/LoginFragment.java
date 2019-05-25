package com.mobile.myworkout.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.myworkout.R;
import com.mobile.myworkout.view.navigation.FragmentNavigator;


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

    private  boolean loginState = true;
    FragmentNavigator fragmentNavigator = new FragmentNavigator();

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_login, viewGroup,
                false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        im_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Check the view model to check if
                * the user exists and create a session token*/

                if (loginState){
                    /*Load profile fragment*/
                    if (! fragmentNavigator.fragmentLoader(new ProfileFragment()
                            ,getFragmentManager())){
                        Toast.makeText(getContext(),
                                "Unable to load the profile page",
                                Toast.LENGTH_LONG).show();
                    };
                } else {
                    Toast.makeText(getContext(),
                            "Wrong password for this email",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        im_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!fragmentNavigator.fragmentLoader(new HomeFragment(),
                        getFragmentManager())){
                    Toast.makeText(getContext(),
                            "Unable to load the home page",
                            Toast.LENGTH_LONG).show();

                };
            }
        });

    }

}
