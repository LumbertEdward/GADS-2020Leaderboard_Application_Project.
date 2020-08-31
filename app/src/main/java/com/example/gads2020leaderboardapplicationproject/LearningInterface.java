package com.example.gads2020leaderboardapplicationproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningInterface {
    @GET("api/hours")
    Call<List<LearningLeadersData>> getLearningLeadersData();
}
