package com.mobile.myworkout.model.datarepository.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobile.myworkout.R;
import com.mobile.myworkout.model.datarepository.networkutils.NetworkService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static NetworkService retrofitInstance(Context context){

        String baseUrl = context
                .getString(R.string.api_base_url);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String token = sharedPreferences.getString(context
                .getString(R.string.auth_token_key_string), "");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Interceptor interceptor = chain -> {
                Request modifiedRequest = chain.request().newBuilder()
                        .addHeader(context.getString(R.string.api_interceptor_auth_header),
                                "Bearer "+ token).build();

                return chain.proceed(modifiedRequest);
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client);

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(NetworkService.class);
    }
}
