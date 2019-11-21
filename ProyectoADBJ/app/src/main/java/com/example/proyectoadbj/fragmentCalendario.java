package com.example.proyectoadbj;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentCalendario extends Fragment {


    // Fields:

    String fullDate;
    private String activeUserName;
    ArrayList<workoutEvent> workoutEventList;

    // Data access object y querydump
    DAO dao;
    queryDump q=new queryDump();

    // Controles
    ListView lvCalendario;
    Usuario usuario;


    public fragmentCalendario() {
        // Required empty public constructor
    }


    // Constructor para crear instancias de este fragmento
    public static fragmentCalendario crearFragmentClases(String activeUserName){
        fragmentCalendario frag=new fragmentCalendario();
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
        View view= inflater.inflate(R.layout.fragment_tab_calendario, container, false);

        // Inicializar fragmento
        initClassFragment(container,view);

        // Creacion del objeto de acceso a BD
        dao = new DAO(container.getContext());

        return view;
    }

    public void initClassFragment(ViewGroup container, View view){

        //Views y controles
        lvCalendario=view.findViewById(R.id.lvCalendario);

        // Obtener id del usuario
        usuario = dao.retrieveUser(activeUserName);
        int idUsuario=dao.getIdFromUsuario(activeUserName);
        // Llenar lista
        UIHelpers.fillListView(lvCalendario,dao.getCalendarEvents(idUsuario),container.getContext());

    }

    private void llenarCalendario(int idUsuario, Context context) {



    }



}
