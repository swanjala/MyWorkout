package com.mobile.myworkout.model.datarepository.networkutils;

import com.google.gson.annotations.SerializedName;
import com.mobile.myworkout.model.datamodel.UserModel;

import java.util.List;

public class NetworkData {

    @SerializedName("email")
    private String email;


    @SerializedName("token")
    private String token;


    @SerializedName("message")
    private String message;


    @SerializedName("profile")
    private List<UserModel> profile;

    @SerializedName("gender")
    private String gender;


    @SerializedName("email_availability")
    private String emailAvailability;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmailAvailability() {
        return emailAvailability;
    }

    public void setEmailAvailability(String emailAvailability) {
        this.emailAvailability = emailAvailability;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<UserModel> getProfile() {
        return profile;
    }

    public void setProfile(List<UserModel> profile) {
        this.profile = profile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
