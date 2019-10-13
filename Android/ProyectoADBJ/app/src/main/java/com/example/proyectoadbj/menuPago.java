package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class menuPago extends AppCompatActivity {

    private Button btPagar;
    private Button btNoPagar;

    private RadioButton rbCredito;
    private RadioButton rbDebito;
    private RadioButton rbOnepay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        // Botones de pago
        btPagar = (Button) findViewById(R.id.btPagar);
        btNoPagar = (Button) findViewById(R.id.btNoPagar);

        // Radio botones

        rbCredito = (RadioButton) findViewById(R.id.rbCredito);
        rbDebito = (RadioButton) findViewById(R.id.rbDebito);
        rbOnepay = (RadioButton) findViewById(R.id.rbOnepay);


        btNoPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aMenuRegistro = new Intent(menuPago.this, menuRegistro.class);
                startActivity(aMenuRegistro);
            }
        });

        btPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorHandler.Toaster(enumErrores.noImplementado,menuPago.this);
            }
        });
    }
}
