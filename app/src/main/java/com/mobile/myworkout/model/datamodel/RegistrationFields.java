package com.mobile.myworkout.model.datamodel;

public class RegistrationFields {
    private String email_address,password,gender;

    public RegistrationFields (UserModel userModel){

            this.email_address = userModel.getEmail();
            this.password = userModel.getPassword();
            this.gender = userModel.getGender();
        }

}
