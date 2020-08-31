package com.example.gads2020leaderboardapplicationproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class submitRetrofit {
    private static Retrofit retrofit;
    private static final String baseURl = "https://docs.google.com/forms/d/e/";
    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

