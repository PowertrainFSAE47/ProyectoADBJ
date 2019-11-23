package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class menuPago extends AppCompatActivity {




    private Button btPagar;
    private Button btNoPagar;

    private RadioGroup rgPagos;

    private TextView lbNombreRegistrado;
    private TextView lbCorreoRegistrado;

    private Spinner spPagoPlanes;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        // Botones de pago
        btPagar = findViewById(R.id.btPagar);
        btNoPagar = findViewById(R.id.btNoPagar);

        // Lista de planes;
        spPagoPlanes=findViewById(R.id.spPagoPlanes);

        // Radio group
        rgPagos = findViewById(R.id.rgPagos);
        lbNombreRegistrado=findViewById(R.id.lbNombreRegistrado);
        lbCorreoRegistrado=findViewById(R.id.lbCorreoRegistrado);

        //Obtener intent
        Intent intentRegistro = getIntent();
        Usuario myUser = intentRegistro.getParcelableExtra("user");
        String activeUsername=myUser.getUsername();

        // Actualizar datos registrados.
        lbNombreRegistrado.setText(myUser.getNombres()+" "+myUser.getApellidos());
        lbCorreoRegistrado.setText(myUser.getEmail());

        // Llenar listview con planes disponibles.

        dao= new DAO(menuPago.this);
        queryDump q=new queryDump();


        btNoPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aMainActivity = new Intent(menuPago.this, MainActivity.class);
                startActivity(aMainActivity);
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
