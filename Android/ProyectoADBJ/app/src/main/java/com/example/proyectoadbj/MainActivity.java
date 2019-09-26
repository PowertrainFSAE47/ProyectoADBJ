package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instanciar botones y controles

        btLogin=(Button) findViewById(R.id.btLogin);
        txUser=(EditText) findViewById(R.id.txUser);
        txPassword=(EditText) findViewById(R.id.txPassword);

        //Desplegar toast en caso de que no se llenen algunos campos

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txUser.getText().toString().trim().isEmpty()){
                    //No tiene user
                    Toast.makeText(MainActivity.this,"No ingresó un usuario",Toast.LENGTH_SHORT).show();
                }else if (txPassword.getText().toString().trim().isEmpty()){
                    // No tiene password
                    Toast.makeText(MainActivity.this,"No ingresó un password",Toast.LENGTH_SHORT).show();
                }else{
                    // Tiene user y tiene clave
                    Toast.makeText(MainActivity.this,"WENA PERRO",Toast.LENGTH_SHORT).show();

                    //Borrar textos del login
                    txUser.setText("");
                    txPassword.setText("");

                    Intent aMenuPrincipal = new Intent(MainActivity.this, menuPrincipal.class);
                    startActivity(aMenuPrincipal);

                }
            }
        });



    }
}

