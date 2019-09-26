package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class menuDatosUsuario extends AppCompatActivity {

    private ListView lvDatosSubs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_datos_usuario);

        lvDatosSubs = (ListView) findViewById(R.id.lvDatosSub);
        ArrayList <String> lista = new ArrayList<String>();

    }
}
