package com.mobile.myworkout.model.datarepository.networkutils;

import com.mobile.myworkout.model.datamodel.LoginFields;
import com.mobile.myworkout.model.datamodel.RegistrationFields;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkService {

    @POST("auth/login")
    Call<NetworkData> login(@Body LoginFields login);

    @POST("auth/register")
    Call<NetworkData> register(@Body RegistrationFields registrationDetails);

    @POST("auth/email")
    Call<NetworkData> checkEmail(@Body LoginFields login);

    @GET("profile")
    Call<NetworkData> getProfile();


}
