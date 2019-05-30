package com.mobile.myworkout.model.datarepository.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mobile.myworkout.model.datamodel.LoginFields;
import com.mobile.myworkout.model.datamodel.RegistrationFields;
import com.mobile.myworkout.model.datamodel.UserModel;
import com.mobile.myworkout.model.datarepository.networkutils.NetworkData;
import com.mobile.myworkout.model.datarepository.networkutils.NetworkService;

import retrofit2.Call;

/*Class implements retrofit network call instances
 * and provides them when needed by the application*/
public class APIManager {

    private UserModel mUserModel = new UserModel();

    private CallInstance callInstance = new CallInstance();
    private Context mContext;

    public APIManager(Context context) {
        this.mContext = context;
        this.callInstance.setInstance(RetrofitInstance.retrofitInstance(context));
    }

    public APIManager(UserModel userModel, Context context) {
        this.mUserModel = userModel;
        this.callInstance.setInstance(RetrofitInstance.retrofitInstance(context));
    }

    public Call<NetworkData> registerUser() {

        return callInstance.getInstance().register(new RegistrationFields(mUserModel));
    }

    public Call<NetworkData> login() {

        return callInstance.getInstance().login(new LoginFields(mUserModel));

    }


    public Call<NetworkData> checkEmail() {

        return callInstance.getInstance().checkEmail(new LoginFields(mUserModel));

    }

    public Call<NetworkData> getUserData() {

        NetworkService service = callInstance.getInstance();

        return service.getProfile();

    }


}
