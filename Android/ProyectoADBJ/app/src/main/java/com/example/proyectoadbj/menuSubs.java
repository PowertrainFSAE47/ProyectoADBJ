package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class menuSubs extends AppCompatActivity {

    private TextView txNombre;
    private TextView txApellido;
    private TextView txMail;

    private Button btAceptarSub;
    private Button btCancelarSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_subs);

        txNombre=findViewById(R.id.txNombre);
        txApellido=findViewById(R.id.txApellido);
        txMail=findViewById(R.id.txMail);
        btAceptarSub=findViewById(R.id.btAceptarSub);
        btCancelarSub=findViewById(R.id.btCancelarSub);

        btAceptarSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txNombre.getText().toString().trim().isEmpty()){
                    //No tiene Nombre
                    Toast.makeText(menuSubs.this,"No ingresó un Nombre",Toast.LENGTH_SHORT).show();
                }else if (txApellido.getText().toString().trim().isEmpty()){
                    // No tiene password
                    Toast.makeText(menuSubs.this,"No ingresó un Apellido",Toast.LENGTH_SHORT).show();
                }else{

                    //Borrar textos del login
                    txNombre.setText("");
                    txApellido.setText("");
                    txMail.setText("");
                }

                Intent aMenuDatos = new Intent(menuSubs.this, menuDatosUsuario.class);
                startActivity(aMenuDatos);
            }
        });

        btCancelarSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aLogin = new Intent(menuSubs.this, MainActivity.class);
                startActivity(aLogin);
            }
        });

    }
}
