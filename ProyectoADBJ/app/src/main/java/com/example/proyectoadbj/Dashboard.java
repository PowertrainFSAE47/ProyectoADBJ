package com.example.proyectoadbj;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {

    private TabLayout tlDashboard;
    private ViewPager vpDashboard;
    private TabItem tabUser, tabStats, tabClases, tabTrainers, tabMaquinas;
    private PagerAdapter pgAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        // Importacion de elementos de la interfaz.
        tlDashboard = (TabLayout) findViewById(R.id.tlDashboard);
        tabUser = (TabItem) findViewById(R.id.tabUser);
        tabStats = (TabItem) findViewById(R.id.tabStats);
        tabClases = (TabItem) findViewById(R.id.tabClases);
        tabTrainers = (TabItem) findViewById(R.id.tabTrainers);
        tabMaquinas = (TabItem) findViewById(R.id.tabMaquinas);
        vpDashboard = (ViewPager) findViewById(R.id.vpDashboard);

        pgAdapter = new pgAdapter(getSupportFragmentManager(), tlDashboard.getTabCount());
        vpDashboard.setAdapter(pgAdapter);


        tlDashboard.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();
                vpDashboard.setCurrentItem(tabPosition);

                switch (tabPosition) {
                    case 0:
                        pgAdapter.notifyDataSetChanged();

                    case 1:
                        pgAdapter.notifyDataSetChanged();
                    case 2:
                        pgAdapter.notifyDataSetChanged();
                    case 3:
                        pgAdapter.notifyDataSetChanged();
                    case 4:
                        pgAdapter.notifyDataSetChanged();
                    case 5:
                        pgAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vpDashboard.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlDashboard));

    }
}
