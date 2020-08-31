package com.example.gads2020leaderboardapplicationproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LearningRetrofitClass {
    private static Retrofit retrofit;
    private static final String baseURL = "https://gadsapi.herokuapp.com/";
    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
