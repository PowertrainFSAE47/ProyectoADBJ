package com.example.proyectoadbj;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pgAdapter extends FragmentPagerAdapter {

    private int numTabs;

    public pgAdapter(FragmentManager fm,int numTabs) {
        super(fm);
        this.numTabs=numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        // Cada objeto construido acá es una clase que representa fragments
        // Lo curioso es que cada vez que cambia de ventana, construye un objeto de la clase
        // Es necesario evaluar la persistencia o colocar condicionales para evitar crear objetos innecesarios.

        switch(position) {
            case 0:
                return new tabUser();
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
        return 0;
    }
}
