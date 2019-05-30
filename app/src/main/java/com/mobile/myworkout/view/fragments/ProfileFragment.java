package com.mobile.myworkout.view.fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobile.myworkout.R;
import com.mobile.myworkout.model.datamodel.UserModel;
import com.mobile.myworkout.view.adapters.ProfileAdapter;
import com.mobile.myworkout.view.navigation.FragmentNavigator;
import com.mobile.myworkout.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    @BindView(R.id.img_profile_picture)
    ImageView img_profile_pic;

    @BindView(R.id.bt_logout)
    Button bt_logout;

    @BindView(R.id.rv_user_details)
    RecyclerView rv_user_details;

    private ProfileAdapter profileAdapter;

    HashMap<String, String> profileInfo =
            new HashMap<>();

    ArrayList<HashMap<String,String>> profileData = new ArrayList<>();

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
    @SuppressLint("NewApi")
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


        UserViewModel userViewModel = ViewModelProviders
                .of(this)
                .get(UserViewModel.class);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext());

        rv_user_details.setLayoutManager(linearLayoutManager);

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());


        profileInfo.put("Email", preferences.getString("email", ""));
        profileData.add(profileInfo);
        profileInfo.put("Gender", preferences.getString("gender", ""));
        profileData.add(profileInfo);

        profileAdapter = new ProfileAdapter(getContext(), profileData);

        rv_user_details.setAdapter(profileAdapter);
        profileAdapter.notifyDataSetChanged();

        bt_logout.setOnClickListener(v -> {

            if(userViewModel.logOutUser()) {

                if (!fragmentNavigator.fragmentLoader(new HomeFragment(),
                        getFragmentManager(), null)) {
                    Toast.makeText(getContext(),
                            "Unable to load the home screen", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
