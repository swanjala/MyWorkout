package com.mobile.myworkout.model.datamodel;

public class LoginFields {

    private String email_address,password;

    public LoginFields(UserModel userModel){
        this.email_address = userModel.getEmail();
        this.password = userModel.getPassword();
    }
}
