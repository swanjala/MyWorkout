package com.mobile.myworkout.view.fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobile.myworkout.R;
import com.mobile.myworkout.view.adapters.ProfileAdapter;
import com.mobile.myworkout.view.navigation.FragmentNavigator;
import com.mobile.myworkout.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    @BindView(R.id.img_profile_picture)
    ImageView img_profile_pic;

    @BindView(R.id.bt_logout)
    Button bt_logout;

    @BindView(R.id.rv_user_details)
    RecyclerView rv_user_details;

    HashMap<String, String> profileInfo =
            new HashMap<>();

    ArrayList<HashMap<String, String>> profileData = new ArrayList<>();

    FragmentNavigator fragmentNavigator = new FragmentNavigator();

    private ProfileAdapter profileAdapter;

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_profile,
                viewGroup, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @SuppressLint("NewApi")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        UserViewModel userViewModel = ViewModelProviders
                .of(this)
                .get(UserViewModel.class);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext());

        rv_user_details.setLayoutManager(linearLayoutManager);

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());


        profileInfo.put(getString(R.string.key_email_map_data),
                preferences.getString(getString(R.string.key_email), ""));

        profileData.add(profileInfo);

        profileInfo.put(getString(R.string.tv_gender_map_data),
                preferences.getString(getString(R.string.value_gender_data), ""));

        profileData.add(profileInfo);

        profileAdapter = new ProfileAdapter(getContext(), profileData);

        rv_user_details.setAdapter(profileAdapter);
        profileAdapter.notifyDataSetChanged();

        bt_logout.setOnClickListener(v -> {

            if (userViewModel.logOutUser()) {

                if (!fragmentNavigator.fragmentLoader(new HomeFragment(),
                        getFragmentManager(), null)) {
                    Toast.makeText(getContext(),getString(R.string.toast_load_home_error_message)
                            , Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
