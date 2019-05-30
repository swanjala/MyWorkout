package com.mobile.myworkout.model.datarepository.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.mobile.myworkout.model.datarepository.api.APIManager;
import com.mobile.myworkout.model.datarepository.networkutils.NetworkData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*Class that should ensure that the local and remote database are in sync
 * for users who might want to access their profile without an internet connection
  * this is not currently in use due to last minute profile endpoint failure*/

public class DatabaseManager extends AsyncTask<Void, Void, Void> {

    public UserDataAccessObject userDataAccessObject;


    private Context mContext;

    public DatabaseManager(Context context, MyWorkoutDatabase database){
        this.mContext = context;
        userDataAccessObject = database.userDataAccessObject();
    }

    @Override
    protected  Void doInBackground(final Void... params){

        APIManager apiManager = new APIManager(mContext);

        Call<NetworkData> syncCall = apiManager.getUserData();

        syncCall.enqueue(new Callback<NetworkData>() {
            @Override
            public void onResponse(Call<NetworkData> call, Response<NetworkData> response) {


                Log.d("Body", String.valueOf(response.body()));
                if (response.body()!= null) {
                   if (response.body().getProfile() != null) {
                       for (int index = 0; index < response.body().getProfile().size() ; index++) {
                           userDataAccessObject.createNewUser(response.body().getProfile().get(index));
                       }

                   }
               }
            }

            @Override
            public void onFailure(Call<NetworkData> call, Throwable t) {

            }
        });

        return  null;
    }
}
