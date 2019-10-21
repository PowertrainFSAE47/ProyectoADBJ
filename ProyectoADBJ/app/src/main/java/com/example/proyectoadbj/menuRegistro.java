package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class menuRegistro extends AppCompatActivity {

    private TextView txNombres;
    private TextView txApellidos;
    private TextView txMail;
    private TextView txPassword;
    private TextView txRepetirPassword;


    private Button btAceptarSub;
    private Button btCancelarSub;

    private RadioGroup rgGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txNombres = findViewById(R.id.txNombres);
        txApellidos = findViewById(R.id.txApellidos);
        txMail = findViewById(R.id.txMail);
        txPassword = findViewById(R.id.txPassword);
        txRepetirPassword = findViewById(R.id.txRepetirPassword);
        btAceptarSub = findViewById(R.id.btAceptarSub);
        btCancelarSub = findViewById(R.id.btCancelarSub);
        rgGenero=findViewById(R.id.rgGenero);


        // Almacenar claves


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
                if (txMail.getText().toString().trim().isEmpty()) {
                    stackErrores.add(enumErrores.sinEmail);
                } else {
                    stackErrores.remove(enumErrores.sinEmail);
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


                if (stackErrores.empty()) {

                    // Crear usuario

                    Usuario myUser = new Usuario();
                    myUser.setNombres(txNombres.getText().toString().trim());
                    myUser.setApellidos(txApellidos.getText().toString().trim());
                    myUser.setGenero(UIHelpers.getSelectedRadioText(rgGenero));
                    myUser.setPassword(txApellidos.getText().toString().trim());
                    myUser.setEmail(txMail.getText().toString().trim());

                    // Usuario debe ser enviado a la base de datos o algo.


                    Intent aMenuPago = new Intent(menuRegistro.this, menuPago.class);
                    aMenuPago.putExtra("myUser",myUser);
                    startActivity(aMenuPago);
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
