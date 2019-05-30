package com.mobile.myworkout.model.datarepository;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.mobile.myworkout.model.datamodel.UserModel;
import com.mobile.myworkout.model.datarepository.database.MyWorkoutDatabase;
import com.mobile.myworkout.model.datarepository.database.UserDataAccessObject;
import com.mobile.myworkout.model.datarepository.datatasks.EmailNetworkTask;
import com.mobile.myworkout.model.datarepository.datatasks.LoginTask;
import com.mobile.myworkout.model.datarepository.datatasks.RegistrationTask;

import java.util.List;

public class AppRepository {

    private static MyWorkoutDatabase myWorkoutDatabase;
    private Application mApplication;
    private UserDataAccessObject userDataAccessObject;
    private UserModel mUserModel = new UserModel();


    public AppRepository(@NonNull Application application) {

        this.mApplication = application;

        myWorkoutDatabase = MyWorkoutDatabase.getDatabase(mApplication);
        userDataAccessObject = myWorkoutDatabase.userDataAccessObject();
    }

    public void verifyEmail(String email, FragmentManager fragmentManager) {

        mUserModel.setEmail(email);

        new EmailNetworkTask(mApplication.getApplicationContext(), fragmentManager).execute(mUserModel);
    }


    public void addNewUser(UserModel userModel) {

        new RegistrationTask(myWorkoutDatabase.userDataAccessObject(),
                mApplication.getApplicationContext(), userModel).execute();

    }

    public void loginUser(UserModel userModel) {
        new LoginTask(mApplication.getApplicationContext(), userModel).execute();
    }

    /*WIP : the data flow intended is dependent on a working endpoint(currently broken),
    * meaning that the data in the database would be outdated and not synced to the interned
    * currently using Shared preferences*/
    public List<UserModel> fetchProfileData(String email) {

        return userDataAccessObject.getUserByEmail(email);

    }

}
