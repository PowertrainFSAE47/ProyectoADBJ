package com.example.proyectoadbj;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class tabUser extends Fragment {


    private TextView lblUserName;



    private Usuario user;

    public tabUser() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_tab_user, container, false);
        lblUserName=(TextView)v.findViewById(R.id.lblUserName);

        // Problema con el contexto aqui!
        DAO dao = new DAO(container.getContext());
        user=dao.retrieveUser("terminator2");

        System.out.println("Encontrado usuario: "+user.getNombres()+" "+ user.getApellidos());

        lblUserName.setText(user.getNombres()+" "+user.getApellidos());

        return v;

    }

}
