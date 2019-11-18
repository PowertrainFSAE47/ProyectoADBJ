package com.example.proyectoadbj;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentClases extends Fragment {

    private String activeUserName;

    public fragmentClases() {
        // Required empty public constructor
    }


    // Constructor para crear instancias de este fragmento
    public static fragmentClases crearFragmentClases(String activeUserName){
        fragmentClases frag=new fragmentClases();
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
        View view= inflater.inflate(R.layout.fragment_tab_clases, container, false);

        // Inicializar fragmento
        initClassFragment(container,view);


        return view;
    }

    public void initClassFragment(ViewGroup container, View view){

        //Views y controles
        ListView lvClases=view.findViewById(R.id.lvClases);

        // Conexion a BD.
        DAO dao = new DAO(container.getContext());
        Usuario user = dao.retrieveUser(activeUserName);




    }



}
