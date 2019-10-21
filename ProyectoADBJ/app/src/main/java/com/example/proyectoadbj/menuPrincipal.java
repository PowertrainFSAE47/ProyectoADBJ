package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuPrincipal extends AppCompatActivity {


    private Button btInfo;
    private Button btPlanes;
    private Button btGaleria;
    private Button btSubs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // Instanciar botones y controles

        btInfo=(Button) findViewById(R.id.btInfo);
        btPlanes=(Button) findViewById(R.id.btPlanes);
        btGaleria=(Button) findViewById(R.id.btGaleria);
        btSubs=(Button) findViewById(R.id.btSubs);


        btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent aMenuInfo = new Intent(menuPrincipal.this, menuInfo.class);
                startActivity(aMenuInfo);

            }
        });

        btPlanes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aMenuPlanes = new Intent(menuPrincipal.this, menuPlanes.class);
                startActivity(aMenuPlanes);
            }
        });

        btSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aMenuSubs = new Intent(menuPrincipal.this, menuRegistro.class);
                startActivity(aMenuSubs);
            }
        });












    }
}
