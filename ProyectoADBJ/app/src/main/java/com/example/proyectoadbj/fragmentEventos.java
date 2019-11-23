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


    // Fields:

    String fullDate;
    private String activeUserName;
    ArrayList<workoutEvent> workoutEventList;



    // Data access object y querydump
    DAO dao;
    queryDump q=new queryDump();


    // El bundle savedinstancestate sirve para pasar variables de entorno al fragmento en el momento de su creacion
    // En este caso, se le traspasa el usuario activo.


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
                llenarlvEventos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                llenarlvEventos();
            }
        });

        // Acciones a ejecutar cuando cambiamos la fecha
        calendarioEventos.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                fullDate=dayOfMonth + "/" + month + "/" + year;
                llenarlvEventos();
            }
        });

        lvEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /* En un clic largo hay que lanzar un dialogo de alerta que confirma
                al usuario que va a registrarse en un evento.
                Crear una entrada al calendario*/

                //Poner evento en el calendario
                int estado=dao.joinWorkoutEvent(workoutEventList.get(position),activeUserName);

                if (estado==1){
                    errorHandler.Toaster(enumErrores.usuarioRegistradoEnCalendario,view.getContext());
                }
                if(estado==0){
                    errorHandler.Toaster(enumErrores.errorDeRegistro,view.getContext());
                }
                if(estado==-1){
                    errorHandler.Toaster(enumErrores.ustedYaEstaRegistrado,view.getContext());
                }
                
                return true;
            }
        });

    }

    public fragmentEventos() {
        // Required empty public constructor
    }

    private void llenarlvEventos() {

        // llenar ListView lvEventos.
        // Generamos lista de ::workoutEvent .
        // Esta lista puede convertirse en un arraylist de strings si se itera y se transforma usando workoutEvent::workoutDescription()
        workoutEventList = dao.getWorkoutEventList(fullDate,spTrainings.getSelectedItem().toString());

        ArrayList<String> workoutStringList=new ArrayList<>();
        for (workoutEvent workout:workoutEventList) {
            // Agregar contenido a la lista del spinner
            workoutStringList.add(workout.workoutDescription());
        }

        // Llenar el listview con los eventos de ejercicio obtenidos.
        UIHelpers.fillListView(lvEventos, workoutStringList, this.getContext());
    }


}
