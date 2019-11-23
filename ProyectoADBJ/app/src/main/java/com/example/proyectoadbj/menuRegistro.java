package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class menuRegistro extends AppCompatActivity {

    private TextView txNombres;
    private TextView txApellidos;
    private TextView txPassword;
    private TextView txRepetirPassword;
    private TextView txUserNameRegistro;
    private Button btAceptarSub;
    private Button btCancelarSub;
    private RadioGroup rgGenero;
    private Spinner spPagoPlanes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txNombres = findViewById(R.id.txNombreRegistro);
        txApellidos = findViewById(R.id.txApellidosRegistro);
        txUserNameRegistro=findViewById(R.id.txUserNameRegistro);
        txPassword = findViewById(R.id.txPasswordRegistro);
        txRepetirPassword = findViewById(R.id.txRepetirPassword);
        btAceptarSub = findViewById(R.id.btAceptarSub);
        btCancelarSub = findViewById(R.id.btCancelarSub);
        rgGenero=findViewById(R.id.rgGenero);
        spPagoPlanes=findViewById(R.id.spPagoPlanes);

        //Acceso a BD
        final DAO dao = new DAO(menuRegistro.this);

        // Llenar spinner de planes
        UIHelpers.fillSpinner(spPagoPlanes,dao.getPlanes(),menuRegistro.this);

        btAceptarSub.setOnClickListener(new View.OnClickListener() {
            Stack<enumErrores> stackErrores = new Stack<>();

            @Override
            public void onClick(View v) {

                String password = txPassword.getText().toString();
                String rePassword = txRepetirPassword.getText().toString();

                if (txNombres.getText().toString().trim().isEmpty()) {
                    stackErrores.add(enumErrores.sinNombre);
                } else {
                    stackErrores.remove(enumErrores.sinNombre);
                }
                if (txApellidos.getText().toString().trim().isEmpty()) {
                    stackErrores.add(enumErrores.sinApellido);
                } else {
                    stackErrores.remove(enumErrores.sinApellido);
                }
                if (txUserNameRegistro.getText().toString().trim().isEmpty()) {
                    stackErrores.add(enumErrores.sinUserName);
                } else {
                    stackErrores.remove(enumErrores.sinUserName);
                }
                if (password.isEmpty()) {
                    stackErrores.add(enumErrores.sinPassword);
                } else {
                    stackErrores.remove(enumErrores.sinPassword);
                }
                if (password.compareTo(rePassword) != 0) {
                    stackErrores.add(enumErrores.passwordNoCoincide);
                } else {
                    stackErrores.remove(enumErrores.passwordNoCoincide);
                }



                // Muchos condicionales aca, mejorable.
                if (stackErrores.empty()) {

                    // Crear usuario

                    Usuario user = new Usuario();
                    user.setNombres(txNombres.getText().toString().trim());
                    user.setApellidos(txApellidos.getText().toString().trim());
                    user.setGenero(UIHelpers.getSelectedRadioText(rgGenero));
                    user.setEmail(txUserNameRegistro.getText().toString().trim());
                    user.setPassword(txRepetirPassword.getText().toString().trim());
                    user.setUsername(txUserNameRegistro.getText().toString().trim());


                    user.setPathFoto("p0");

                    // Verificacion de usuario existente
                    if (dao.authLogin(user.getUsername(),user.getPassword())) {
                        // User existe.
                        errorHandler.Toaster(enumErrores.usuarioYaExiste,menuRegistro.this);
                    }else if(dao.registerUser(user)){
                        errorHandler.Toaster(enumErrores.registroExitoso,menuRegistro.this);
                        Intent aMenuPago = new Intent(menuRegistro.this, menuPago.class);
                        aMenuPago.putExtra("user",user);
                        startActivity(aMenuPago);
                    }else {
                        errorHandler.Toaster(enumErrores.errorDeRegistro, menuRegistro.this);
                    }
                } else {
                    errorHandler.Toaster(stackErrores.lastElement(), menuRegistro.this);
                }
            }
        });

        btCancelarSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aMenuLogin = new Intent(menuRegistro.this, MainActivity.class);
                startActivity(aMenuLogin);
            }
        });

    }
}
