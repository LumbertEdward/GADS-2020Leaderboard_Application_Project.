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


public class SkillIQLeaders extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ProgressBar progressBar;
    private SkillIQInterface skillIQInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.skillIQRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        progressBar = (ProgressBar) v.findViewById(R.id.progressIQ);
        getIQData();
        return v;
    }

    private void getIQData() {
        progressBar.setVisibility(View.VISIBLE);
        skillIQInterface = SkillIQRetrofitClass.getRetrofitIQ().create(SkillIQInterface.class);
        Call<List<SkillIQLeadersData>> listCall = skillIQInterface.getSkillIQData();
        listCall.enqueue(new Callback<List<SkillIQLeadersData>>() {
            @Override
            public void onResponse(Call<List<SkillIQLeadersData>> call, Response<List<SkillIQLeadersData>> response) {
                progressBar.setVisibility(View.GONE);
                getData(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillIQLeadersData>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    private void getData(List<SkillIQLeadersData> body) {
        adapter = new SkillIQAdapter(body, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
