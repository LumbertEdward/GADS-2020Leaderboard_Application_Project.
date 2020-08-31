package com.example.gads2020leaderboardapplicationproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class LeaderBoard extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
     private Button btn;

     private LearningLeaders learningLeaders;
     private SkillIQLeaders skillIQLeaders;
     private viewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
         viewPager = (ViewPager) findViewById(R.id.viewPager);
         tabLayout = (TabLayout) findViewById(R.id.tab);
         btn = (Button) findViewById(R.id.btnSubmit);
         toolbar = (Toolbar) findViewById(R.id.toolbar);

         setSupportActionBar(toolbar);

         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(LeaderBoard.this, Submit.class));
             }
         });

         getViewPager();
    }

    private void getViewPager() {
        learningLeaders = new LearningLeaders();
        skillIQLeaders = new SkillIQLeaders();
        mViewPagerAdapter = new viewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPagerAdapter.getFragment(learningLeaders, "Learning Leaders");
        mViewPagerAdapter.getFragment(skillIQLeaders, "Skill IQ Leaders");
        viewPager.setAdapter(mViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
