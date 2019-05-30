package com.mobile.myworkout.model.datarepository.datatasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.mobile.myworkout.activities.WorkoutActivity;
import com.mobile.myworkout.model.datamodel.UserModel;
import com.mobile.myworkout.model.datarepository.api.APIManager;
import com.mobile.myworkout.model.datarepository.database.UserDataAccessObject;
import com.mobile.myworkout.model.datarepository.networkutils.NetworkData;
import com.mobile.myworkout.view.navigation.FragmentNavigator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationTask extends AsyncTask<Void, Void, Boolean[]> {

    private UserDataAccessObject mUserDataAccessObject;
    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private UserModel mUserModel;


    public RegistrationTask(UserDataAccessObject userDataAccessObject,
                     Context context, UserModel userModel){
        mUserDataAccessObject = userDataAccessObject;
        this.mContext = context;
        this.mUserModel = userModel;
    }


    @Override
    protected Boolean[] doInBackground(final Void... params){


        APIManager apiManager = new APIManager(mUserModel, mContext);
        final Boolean[] dataResult = {false};

        Call<NetworkData> call = apiManager.registerUser();

        call.enqueue(new Callback<NetworkData>() {
            @Override
            public void onResponse(Call<NetworkData> call, Response<NetworkData> response) {


                if (response.body() != null && response.body().getToken() != null) {


                    Log.d("auth from reg", response.body().getToken());
                    SharedPreferences preferences = PreferenceManager.
                            getDefaultSharedPreferences(mContext);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("auth_token", response.body().getToken());
                    editor.putString("email", response.body().getEmail());
                    editor.putString("gender", response.body().getGender());
                    editor.commit();


//                    mUserDataAccessObject
//                            .createNewUser(mUserModel);


                    dataResult[0] = true;

                    Intent intent = new Intent(mContext, WorkoutActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);

                    Log.d("data res", String.valueOf(dataResult[0]));
                } else {
                    Toast.makeText(mContext,"Email already exists",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NetworkData> call, Throwable t) {

                Log.d("Failure", t.getLocalizedMessage());

            }
        });

        return null;
    }


}