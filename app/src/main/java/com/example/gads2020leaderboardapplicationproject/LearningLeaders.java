package com.example.gads2020leaderboardapplicationproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeaders extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private LearningInterface learningInterface;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.learningRecyclerView);
        progressBar = (ProgressBar) v.findViewById(R.id.progressLearning);
        layoutManager = new LinearLayoutManager(getContext());
        getLearningData();
        return v;
    }

    private void getLearningData() {
        progressBar.setVisibility(View.VISIBLE);
        learningInterface = LearningRetrofitClass.getRetrofit().create(LearningInterface.class);
        Call<List<LearningLeadersData>> listCall = learningInterface.getLearningLeadersData();
        listCall.enqueue(new Callback<List<LearningLeadersData>>() {
            @Override
            public void onResponse(Call<List<LearningLeadersData>> call, Response<List<LearningLeadersData>> response) {
                progressBar.setVisibility(View.GONE);
                getData(response.body());
            }

            @Override
            public void onFailure(Call<List<LearningLeadersData>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getData(List<LearningLeadersData> body) {
       adapter = new LearningAdapter(body, getContext());
       recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setHasFixedSize(true);
    }

}
