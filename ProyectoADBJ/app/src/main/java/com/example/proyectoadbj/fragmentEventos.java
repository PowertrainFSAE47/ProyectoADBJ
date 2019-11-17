package com.example.proyectoadbj;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentEventos extends Fragment {


    // Controles y views
    CalendarView calendarioEventos;
    Spinner spTrainings;
    ListView lvEventos;

    // fecha activa
    int selectedDay, selectedMonth, selectedYear;


    // Data access object y querydump
    DAO dao;
    queryDump q=new queryDump();


    private String activeUserName;

    public static fragmentEventos crearFragmentEventos(String activeUserName) {
        fragmentEventos frag = new fragmentEventos();
        // Crear un bundle de argumentos para inyectar en el fragmento
        Bundle argumentos = new Bundle();
        // Acá se pueden insertar tantos argumentos como sea necesario en pares key-value
        argumentos.putString("activeUserName", activeUserName);
        // Registrar como argumentos.
        frag.setArguments(argumentos);
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Desempacar bundle y extraer nombre de usuario activo.
        activeUserName = getArguments().getString("activeUserName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_eventos, container, false);
        // Inicializar fragmento una vez inflada la vista.
        initEventosFragment(container, view);

        return view;
    }


    private void initEventosFragment(final ViewGroup container, View view) {

        //Obtener views y controles
        spTrainings = view.findViewById(R.id.spTrainings);
        calendarioEventos = view.findViewById(R.id.calendarioEventos);
        lvEventos = view.findViewById(R.id.lvEventos);

        // Conexion a BD.
        dao = new DAO(container.getContext());

        // Llenar spinner de trainings disponibles
        final ArrayList<String> trainings = dao.getColumn(q.getTrainings);
        UIHelpers.fillSpinner(spTrainings, trainings, container.getContext());


        // Acciones a ejecutar cuando cambiamos el training
        spTrainings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listarEventos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                listarEventos();
            }
        });

        // Acciones a ejecutar cuando cambiamos la fecha
        calendarioEventos.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                selectedDay = dayOfMonth;
                selectedMonth = month;
                selectedYear = year;
                listarEventos();


            }
        });

    }

    public fragmentEventos() {
        // Required empty public constructor
    }

    private void listarEventos() {

        // Obtener training seleccionado
        String trainingSeleccionado = spTrainings.getSelectedItem().toString();
        // Extraer data del calendario y consultar a la DB
        String sql="";
        if (trainingSeleccionado.compareTo("Todas")==0){
            sql=q.getEventosDisponibles(selectedDay + "/" + selectedMonth + "/" + selectedYear);
        }else{
            sql = q.getEventosFiltrados(selectedDay + "/" + selectedMonth + "/" + selectedYear, trainingSeleccionado);
        }
        System.out.println(sql);
        ArrayList<String> eventos = dao.getConcatRows(sql, new String[]{"De ", " a ", " con: ", " ", ""});
        //Llenar spinner de eventos disponibles
        UIHelpers.fillListView(lvEventos, eventos, this.getContext());
    }
}
