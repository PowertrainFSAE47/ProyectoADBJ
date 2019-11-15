package com.example.proyectoadbj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {

    // Con asistencia de https://guides.codepath.com/android/Google-Play-Style-Tabs-using-TabLayout

    private TabItem tabUser, tabStats, tabClases, tabTrainers, tabMaquinas;
    private PagerAdapter pgAdapter;
    private TextView lblUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Importacion del objeto user desde el login
        Intent desdeLogin = getIntent();
        final Usuario user = desdeLogin.getParcelableExtra("user");

        //Inicialización de elementos de la interfaz.
        lblUser = (TextView) findViewById(R.id.lblUser);
        lblUser.setText(user.getUsername());

        //TabLayout
        TabLayout dashTabLayout = (TabLayout) findViewById(R.id.dashTabLayout);
        // dashViewPager es el ViewPager definido en el XML
        ViewPager dashViewPager = (ViewPager) findViewById(R.id.dashViewPager);


        // Obtener el ViewPager y setear su PagerAdapter para que pueda mostrar elementos
        PagerAdapter pagerAdapter = new fragmentPagerAdapter(getSupportFragmentManager(), dashTabLayout.getTabCount(), user.getUsername());
        dashViewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager

        dashTabLayout.setupWithViewPager(dashViewPager);

    }
}
