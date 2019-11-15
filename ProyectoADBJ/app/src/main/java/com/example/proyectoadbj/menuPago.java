package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class menuPago extends AppCompatActivity {




    private Button btPagar;
    private Button btNoPagar;

    private RadioGroup rgPagos;

    private TextView lbNombreRegistrado;
    private TextView lbCorreoRegistrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        // Botones de pago
        btPagar = findViewById(R.id.btPagar);
        btNoPagar = findViewById(R.id.btNoPagar);

        // Radio group
        rgPagos = findViewById(R.id.rgPagos);
        lbNombreRegistrado=findViewById(R.id.lbNombreRegistrado);
        lbCorreoRegistrado=findViewById(R.id.lbCorreoRegistrado);

        //Obtener intent
        Intent intentRegistro = getIntent();
        Usuario myUser = intentRegistro.getParcelableExtra("user");

        // Actualizar datos registrados.
        lbNombreRegistrado.setText(myUser.getNombres()+" "+myUser.getApellidos());
        lbCorreoRegistrado.setText(myUser.getEmail());

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
