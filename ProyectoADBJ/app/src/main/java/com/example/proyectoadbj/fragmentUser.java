package com.example.proyectoadbj;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentUser extends Fragment {

    // Controles
    private TextView lblUserName;

    // Parametros
    private Usuario user;
    private String activeUserName;


    // Constructor para crear instancias de este fragmento
    public static fragmentUser crearFragmentUser(String activeUserName){
        fragmentUser frag=new fragmentUser();
        // Crear un bundle de argumentos para inyectar en el fragmento
        Bundle argumentos=new Bundle();
        // Acá se pueden insertar tantos argumentos como sea necesario en pares key-value
        argumentos.putString("activeUserName",activeUserName);

        // Registrar como argumentos.
        frag.setArguments(argumentos);
        return frag;
    }


    // Al crear un fragmento, se toma el bundle de argumentos
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Desempacar bundle y extraer nombre de usuario activo.
        activeUserName=getArguments().getString("activeUserName");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar fragmento
        View view = inflater.inflate(R.layout.fragment_tab_user, container, false);
        // Inicializar fragmento
        initUserFragment(container,view);

        return view;
    }

    private void initUserFragment(ViewGroup container, View view){

        //Views y controles
        lblUserName=(TextView)view.findViewById(R.id.lblUserName);

        // Conexion a BD.
        DAO dao = new DAO(container.getContext());
        Usuario user = dao.retrieveUser(activeUserName);
        // A quien encontramos acá?
        System.out.println("Encontrado usuario: " + user.getNombres() + " " + user.getApellidos());
        // Actualizar los controles encontrados en el fragment.
        lblUserName.setText(user.getNombres() + " " + user.getApellidos());
        // Añadir resto de inicializaciones.
    }

    public fragmentUser() {

    }
}
