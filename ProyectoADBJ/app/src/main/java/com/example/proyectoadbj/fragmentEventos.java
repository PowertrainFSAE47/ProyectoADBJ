package com.example.proyectoadbj;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentEventos extends Fragment {



    // Controles y views
    CalendarView calendarioEventos;
    Spinner spTrainings;
    Spinner spEventos;


    // Data access object y querydump
    DAO dao;
    queryDump q;







    private String activeUserName;

    public static fragmentEventos crearFragmentEventos(String activeUserName){
        fragmentEventos frag=new fragmentEventos();
        // Crear un bundle de argumentos para inyectar en el fragmento
        Bundle argumentos=new Bundle();
        // Acá se pueden insertar tantos argumentos como sea necesario en pares key-value
        argumentos.putString("activeUserName",activeUserName);
        // Registrar como argumentos.
        frag.setArguments(argumentos);
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Desempacar bundle y extraer nombre de usuario activo.
        activeUserName=getArguments().getString("activeUserName");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tab_eventos, container, false);
        // Inicializar fragmento una vez inflada la vista.
        initEventosFragment(container,view);

        return view;
    }



    private void initEventosFragment(final ViewGroup container, View view){

        //Obtener views y controles
        spTrainings=view.findViewById(R.id.spTrainings);
        calendarioEventos=view.findViewById(R.id.calendarioEventos);
        spEventos=view.findViewById(R.id.spEventos);

        // Conexion a BD.
        dao = new DAO(container.getContext());
        final queryDump q=new queryDump();

        // Llenar spinner de trainings disponibles
        final ArrayList<String> trainings=dao.getColumn(q.getTrainings);
        UIHelpers.fillSpinner(spTrainings,trainings,container.getContext());


        calendarioEventos.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Llenar spinner de eventos disponibles
                String sql = q.getEventosDisponibles(dayOfMonth + "/" + month + "/" + year);
                System.out.println("Consultando eventos para: "+dayOfMonth + "/" + month + "/" + year);
                System.out.println(sql);
                ArrayList<String> eventos = dao.getConcatRows(sql, new String[]{ " desde: ", " hasta: "," con: "," ",""});
                UIHelpers.fillSpinner(spEventos, eventos, container.getContext());
            }
        });

    }

    public fragmentEventos() {
        // Required empty public constructor
    }
}
