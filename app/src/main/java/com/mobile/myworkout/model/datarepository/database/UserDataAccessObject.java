package com.mobile.myworkout.model.datarepository.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mobile.myworkout.model.datamodel.UserModel;

import java.util.List;

@Dao
public interface UserDataAccessObject {

    @Query("SELECT * FROM userTable where email LIKE :emailAddress")
    List<UserModel> getUserByEmail(String emailAddress);

    @Insert
    void createNewUser(UserModel userModel);

}
