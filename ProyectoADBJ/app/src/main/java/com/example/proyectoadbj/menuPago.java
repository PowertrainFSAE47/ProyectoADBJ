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

    private TextView lbPrecioRegistrar;
    private TextView lbUsernameRegistrar;
    private TextView lbPlanRegistrar;
    private TextView lbNombreRegistrar;

    private DAO dao;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        // Botones de pago
        btPagar = findViewById(R.id.btPagar);
        btNoPagar = findViewById(R.id.btNoPagar);
        btPagar.setEnabled(true);

        // Radio group
        rgPagos = findViewById(R.id.rgPagos);

        // Textviews
        lbNombreRegistrar=findViewById(R.id.lbNombreRegistrar);
        lbUsernameRegistrar=findViewById(R.id.lbUsernameRegistrar);
        lbPlanRegistrar=findViewById(R.id.lbPlanRegistrar);
        lbPrecioRegistrar=findViewById(R.id.lbPrecioRegistrar);

        //Obtener intent
        Intent intentRegistro = getIntent();

        // Objeto usuario traido desde registro.
        user = intentRegistro.getParcelableExtra("user");


        // Actualizar datos registrados.
        lbNombreRegistrar.setText(user.getNombres()+" "+user.getApellidos());
        lbUsernameRegistrar.setText(user.getUsername());
        lbPlanRegistrar.setText("Plan: "+user.getSubscripcion().getNombrePlan());
        lbPrecioRegistrar.setText(UIHelpers.moneyFormatter(user.getSubscripcion().getPrecioAnual()));

        dao= new DAO(menuPago.this);

        btNoPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btPagar.isEnabled()){
                    Intent aMenuRegistro = new Intent(menuPago.this, menuRegistro.class);
                    startActivity(aMenuRegistro);
                }else{
                    Intent aMainActivity = new Intent(menuPago.this, MainActivity.class);
                    startActivity(aMainActivity);
                }

            }
        });

        btPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dao.getIdFromUsername(user.getUsername())==-1){
                    // usuario no existe, registrar.
                    boolean flag=dao.registerUser(user);
                    if (flag){
                        errorHandler.Toaster(enumErrores.registroExitoso,menuPago.this);
                        btPagar.setEnabled(false);
                    }else{
                        errorHandler.Toaster(enumErrores.errorDeRegistro,menuPago.this);
                    }
                }else{
                    //usuario ya existe
                    errorHandler.Toaster(enumErrores.usuarioYaExiste,menuPago.this);
                }



            }
        });
    }
}
