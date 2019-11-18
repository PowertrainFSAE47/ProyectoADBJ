package com.example.proyectoadbj;


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


        return view;
    }

    public void initClassFragment(ViewGroup container, View view){

        //Views y controles
        ListView lvCalendario=view.findViewById(R.id.lvCalendario);

        // Conexion a BD.
        dao = new DAO(container.getContext());
        Usuario user = dao.retrieveUser(activeUserName);


        // Llenar el lvCalendario con todos los eventos del calendario de este usuario
        UIHelpers.fillListView(lvEventos, workoutStringList, this.getContext());


    }

    private void llenarlvCalendario() {

        // llenar ListView lvEventos.
        // Generamos lista de ::workoutEvent .
        // Esta lista puede convertirse en un arraylist de strings si se itera y se transforma usando workoutEvent::workoutDescription()
        //workoutEventList = dao.getWorkoutEventList(fullDate,spTrainings.getSelectedItem().toString());

        ArrayList<String> workoutStringList=new ArrayList<>();
        for (workoutEvent workout:workoutEventList) {
            // Agregar contenido a la lista del spinner
            workoutStringList.add(workout.workoutDescription());
        }

        // Llenar el listview con los eventos de ejercicio obtenidos.
        UIHelpers.fillListView(lvEventos, workoutStringList, this.getContext());
    }



}
