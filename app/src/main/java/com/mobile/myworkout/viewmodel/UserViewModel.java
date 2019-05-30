package com.mobile.myworkout.viewmodel;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.mobile.myworkout.model.datamodel.UserModel;
import com.mobile.myworkout.model.datarepository.AppRepository;
import com.mobile.myworkout.view.navigation.FragmentNavigator;

import java.util.List;

public class UserViewModel extends AndroidViewModel {


    LiveData<List<UserModel>> userList;
    private boolean availableEmail;
    private AppRepository appRepository;
    private List<UserModel> userData;
    private Context context;
    private FragmentNavigator fragmentNavigator = new FragmentNavigator();


    public UserViewModel(Application application) {
        super(application);
        appRepository = new AppRepository(application);

        this.context = application.getApplicationContext();

    }

    @TargetApi(Build.VERSION_CODES.N)
    public void findUserEmail(String email, FragmentManager fragmentManager) {

        appRepository.verifyEmail(email, fragmentManager);

    }

    public void createNewUser(UserModel userModel) {

        appRepository.addNewUser(userModel);
    }

    public void loginUserWithCredentials(UserModel userModel) {

        appRepository.loginUser(userModel);
    }

    @SuppressLint("ApplySharedPref")
    public boolean logOutUser() {

        try {
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(context);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("auth_token", "");
            editor.commit();

        } catch (Exception e) {

            Toast.makeText(context, "Unable to log you out at the moment",
                    Toast.LENGTH_LONG).show();

            return false;
        }

        return true;
    }

    /*WIP : the data flow intended is dependent on a working endpoint(currently broken),
     * meaning that the data in the database would be outdated and not synced to the interned
     * currently using Shared preferences*/

    public List<UserModel> fetchPersonalData(String email) {
        userData = appRepository.fetchProfileData(email);
        return userData;

    }


}
