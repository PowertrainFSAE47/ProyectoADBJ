package com.example.proyectoadbj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {

    private TabLayout dashTabLayout;
    private ViewPager dashViewPager;
    private TabItem tabUser, tabStats, tabClases, tabTrainers, tabMaquinas;
    private PagerAdapter pgAdapter;
    private TextView lblUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Importacion del objeto user desde el login
        Intent desdeLogin = getIntent();
        Usuario user = desdeLogin.getParcelableExtra("user");

        // Paso de argumentos a los fragmentos.

        final Bundle bundleTabUser=new Bundle();
        bundleTabUser.putParcelable("user",user);


        //TabLayout y ViewPager
        dashTabLayout = (TabLayout) findViewById(R.id.dashTabLayout);
        dashViewPager = (ViewPager) findViewById(R.id.dashViewPager);

        // Importacion de elementos de la interfaz.
        tabUser = (TabItem) findViewById(R.id.tabUser);
        tabStats = (TabItem) findViewById(R.id.tabStats);
        tabClases = (TabItem) findViewById(R.id.tabClases);
        tabTrainers = (TabItem) findViewById(R.id.tabTrainers);
        tabMaquinas = (TabItem) findViewById(R.id.tabMaquinas);

        lblUser=(TextView) findViewById(R.id.lblUser);



        //Inicialización de elementos de la interfaz.
        lblUser.setText(user.getUsername());





        pgAdapter = new pgAdapter(getSupportFragmentManager(), dashTabLayout.getTabCount());
        dashViewPager.setAdapter(pgAdapter);

        dashTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();
                dashViewPager.setCurrentItem(tabPosition);

                switch (tabPosition) {
                    case 0:
                        // TabUser
                        Fragment activeFragment=getSupportFragmentManager().findFragmentById(R.id.tabUser);
                        activeFragment.setArguments(bundleTabUser);
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

        dashViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(dashTabLayout));

    }
}
