package com.example.gads2020leaderboardapplicationproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillIQRetrofitClass {
    private static Retrofit retrofit;
    private static final String baseURL = "https://gadsapi.herokuapp.com/";
    public static Retrofit getRetrofitIQ(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
