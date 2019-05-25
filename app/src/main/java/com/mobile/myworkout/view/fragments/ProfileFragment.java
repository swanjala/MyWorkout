package com.mobile.myworkout.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobile.myworkout.R;
import com.mobile.myworkout.views.navigation.FragmentNavigator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    @BindView(R.id.img_profile_picture)
    ImageView img_profile_pic;

    @BindView(R.id.bt_logout)
    Button bt_logout;

    @BindView(R.id.rv_user_details)
    RecyclerView rv_user_details;

    FragmentNavigator fragmentNavigator = new FragmentNavigator();


    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_profile,
                viewGroup, false);
        ButterKnife.bind(this,view);
        return view;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!fragmentNavigator.fragmentLoader(new HomeFragment(),getFragmentManager())){
                    Toast.makeText(getContext(),
                            "Unable to log you out",Toast.LENGTH_LONG).show();
                };
            }
        });

    }

}
