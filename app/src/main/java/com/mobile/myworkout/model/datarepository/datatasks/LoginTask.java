package com.mobile.myworkout.model.datarepository.datatasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.mobile.myworkout.activities.WorkoutActivity;
import com.mobile.myworkout.model.datamodel.UserModel;
import com.mobile.myworkout.model.datarepository.api.APIManager;
import com.mobile.myworkout.model.datarepository.networkutils.NetworkData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTask extends AsyncTask<Void, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private UserModel mUserModel;

    public LoginTask(Context context, UserModel userModel) {
        this.mContext = context;
        mUserModel = userModel;
    }

    @Override
    protected Boolean doInBackground(final Void... params) {

        Call<NetworkData> call = new APIManager(mUserModel, mContext).login();

        call.enqueue(new Callback<NetworkData>() {
            @Override
            public void onResponse(@NonNull Call<NetworkData> call,
                                   @NonNull Response<NetworkData> response) {

                SharedPreferences preferences =
                        PreferenceManager.getDefaultSharedPreferences(mContext);

                if (response.body() != null) {

                    if (response.body().getToken() != null) {

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("auth_token", response.body().getToken());
                        editor.putString("email", response.body().getEmail());
                        editor.putString("gender", response.body().getGender());
                        editor.commit();


                        Intent intent = new Intent(mContext, WorkoutActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);

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