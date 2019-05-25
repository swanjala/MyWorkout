package com.mobile.myworkout.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.mobile.myworkout.model.datamodel.UserModel;
import com.mobile.myworkout.model.datarepository.AppRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {


    AppRepository appRepository;
    List<UserModel> userData;

    public UserViewModel(Application application){
        super(application);

    }

    public boolean checkEmailRegistration() {
        /* check if the email exits*/
        return false;
    }

    public boolean loginUserWithCredentials(UserModel userModel) {

        return false;
    }
    public boolean logOutUser() {
        /*Nullify shared preference values*/

        return false;
    }

    public List<UserModel> fetchPersonalData() {

        /*perform an ops here to fetch the data*/
        return  userData;

    }



}
