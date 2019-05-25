package com.mobile.myworkout.view.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.myworkout.R;
import com.mobile.myworkout.view.navigation.FragmentNavigator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  HomeFragment extends Fragment {

    @BindView(R.id.tv_email_address)
    EditText tv_email_address;

    @BindView(R.id.cl_next)
    ConstraintLayout navigation_container;


    private String emailAddress;
    FragmentNavigator fragmentNavigator = new FragmentNavigator();

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             ViewGroup viewContainer,
                             Bundle savedInstanceState){

        View view = layoutInflater.inflate(R.layout.fragment_main,
                viewContainer,false);
        ButterKnife.bind(this,view);
        return  view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);



        navigation_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = tv_email_address.getText().toString();
                Log.d("address",emailAddress);
                if (emailAddress.equals("")){
                    Toast.makeText(getContext(),"Email cannot be blank",
                            Toast.LENGTH_LONG).show();
                } else{

                    /* TODO
                         Make a view model call to check if the email exists
                     *  */
                    if (emailAddress.equals("true")){

                       if (!fragmentNavigator.fragmentLoader(new LoginFragment(),
                                getFragmentManager())){
                           Toast.makeText(getContext(),"Unable to load login page",
                                   Toast.LENGTH_LONG).show();

                       };

                    } else {
                        if(! fragmentNavigator.fragmentLoader(new RegistrationFragment(),
                                getFragmentManager())){
                            Toast.makeText(getContext(),"Unable to load registration page",
                                    Toast.LENGTH_LONG).show();
                        };

                    }
                }
            }
        });

    }

}
