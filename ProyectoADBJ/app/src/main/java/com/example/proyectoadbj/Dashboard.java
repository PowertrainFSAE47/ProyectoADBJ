package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {

    private TabLayout tlDashboard;
    private ViewPager vpDashboard;
    private TabItem tabUser,tabStats,tabClases,tabTrainers,tabMaquinas;
    private PagerAdapter pagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        //
        tlDashboard=(TabLayout) findViewById(R.id.tlDashboard);
        tabUser=(TabItem) findViewById(R.id.tabUser);
        tabStats=(TabItem) findViewById(R.id.tabStats);
        tabClases=(TabItem) findViewById(R.id.tabClases);
        tabTrainers=(TabItem) findViewById(R.id.tabTrainers);
        tabMaquinas=(TabItem) findViewById(R.id.tabMaquinas);




    }
}
