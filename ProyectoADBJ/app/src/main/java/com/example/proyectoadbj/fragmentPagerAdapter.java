package com.example.proyectoadbj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class fragmentPagerAdapter extends FragmentPagerAdapter {

    private int numTabs;
    private String activeUserName;

    private String tabTitles[] = new String[] { "YO", "EVENTOS", "MI CALENDARIO","TRAINERS","EQUIPOS" };


    public fragmentPagerAdapter(FragmentManager fm, int numTabs,String activeUserName) {
        super(fm);
        this.numTabs=numTabs;
        this.activeUserName=activeUserName;


    }

    @Override
    public Fragment getItem(int position) {
        // Cada objeto construido acá es una clase que representa fragments
        // Lo curioso es que cada vez que cambia de ventana, construye un objeto de la clase
        // Es necesario evaluar la persistencia o colocar condicionales para evitar crear objetos innecesarios.

        switch(position) {
            case 0:
                return fragmentUser.crearFragmentUser(activeUserName);
            case 1:
                return new tabStats();
            case 2:
                return new tabClases();
            case 3:
                return new tabTrainers();
            case 4:
                return new tabMaquinas();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
