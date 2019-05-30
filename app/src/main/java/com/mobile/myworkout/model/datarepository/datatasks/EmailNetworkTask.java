package com.mobile.myworkout.model.datarepository.datatasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.mobile.myworkout.model.datamodel.UserModel;
import com.mobile.myworkout.model.datarepository.api.APIManager;
import com.mobile.myworkout.model.datarepository.networkutils.NetworkData;
import com.mobile.myworkout.view.fragments.LoginFragment;
import com.mobile.myworkout.view.fragments.RegistrationFragment;
import com.mobile.myworkout.view.navigation.FragmentNavigator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailNetworkTask extends AsyncTask<UserModel, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private Context mContext;

    private FragmentManager mFragmentManager;
    private FragmentNavigator fragmentNavigator = new FragmentNavigator();


    public EmailNetworkTask(Context context, FragmentManager fragmentManager) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    protected Boolean doInBackground(final UserModel... params) {

        Call<NetworkData> call = new APIManager(params[0], mContext).checkEmail();

        call.enqueue(new Callback<NetworkData>() {
            @Override
            public void onResponse(@NonNull Call<NetworkData> call,
                                   @NonNull Response<NetworkData> response) {

                SharedPreferences preferences =
                        PreferenceManager.getDefaultSharedPreferences(mContext);
                boolean emailAvailability;

                if (response.body() != null) {

                    emailAvailability = Boolean.parseBoolean(response.body()
                            .getEmailAvailability());

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("email_availability", emailAvailability);
                    editor.commit();


//                    SharedPreferences preferences =
//                            PreferenceManager.getDefaultSharedPreferences(mContext);

                    //availableEmail  = preferences.getBoolean("email_availability",false);

                    if (emailAvailability){

                        if (!fragmentNavigator.fragmentLoader(new LoginFragment(),
                                mFragmentManager,params[0].getEmail())){
                            Toast.makeText(mContext,"Unable to load login page",
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {
                        if(!fragmentNavigator.fragmentLoader(new RegistrationFragment(),
                                mFragmentManager,params[0].getEmail())){
                            Toast.makeText(mContext,"Unable to load registration page",
                                    Toast.LENGTH_LONG).show();
                        }

                    }



                }
            }

            @Override
            public void onFailure(@NonNull Call<NetworkData> call,
                                  @NonNull Throwable t) {

            }
        });

        return true;
    }
}