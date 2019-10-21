package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btLogin;
    private EditText txUser;
    private EditText txPassword;
    private Button btSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instanciar botones y controles

        btLogin = (Button) findViewById(R.id.btLogin);
        btSignup = (Button) findViewById(R.id.btSignup);
        txUser = (EditText) findViewById(R.id.txUser);
        txPassword = (EditText) findViewById(R.id.txPassword);

        //Desplegar toast en caso de que no se llenen algunos campos

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txUser.getText().toString().trim().isEmpty()) {
                    //No tiene user
                    errorHandler.Toaster(enumErrores.sinNombre, MainActivity.this);
                } else if (txPassword.getText().toString().trim().isEmpty()) {
                    // No tiene password
                    errorHandler.Toaster(enumErrores.sinPassword, MainActivity.this);
                } else {
                    //Borrar textos del login
                    txUser.setText("");
                    txPassword.setText("");

                    Intent aDashboard = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(aDashboard);




                }
            }
        });

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aMenuSubs = new Intent(MainActivity.this, menuRegistro.class);
                startActivity(aMenuSubs);
            }
        });


    }
}

