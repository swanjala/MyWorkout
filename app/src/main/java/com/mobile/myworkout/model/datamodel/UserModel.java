package com.mobile.myworkout.model.datamodel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/*TODO
 - Depricate this and use a service
* instead of persisting the user on DB*/

@Entity(tableName = "userTable")
public class UserModel {

    @PrimaryKey(autoGenerate = true)
    private  int id;

    private String email;
    private String password;
    private String gender;

    public UserModel(String email, String password, String gender){
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public UserModel(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String userGender) {
        this.gender = userGender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
