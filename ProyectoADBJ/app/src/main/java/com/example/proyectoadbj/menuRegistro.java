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
    //private TextView txMail;
    private TextView txPassword;
    private TextView txRepetirPassword;
    private TextView txUserNameRegistro;


    private Button btAceptarSub;
    private Button btCancelarSub;

    private RadioGroup rgGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txNombres = findViewById(R.id.txNombreRegistro);
        txApellidos = findViewById(R.id.txApellidosRegistro);
        //txMail = findViewById(R.id.txUserNameRegistro);
        txUserNameRegistro=findViewById(R.id.txUserNameRegistro);
        txPassword = findViewById(R.id.txPasswordRegistro);
        txRepetirPassword = findViewById(R.id.txRepetirPassword);
        btAceptarSub = findViewById(R.id.btAceptarSub);
        btCancelarSub = findViewById(R.id.btCancelarSub);
        rgGenero=findViewById(R.id.rgGenero);

        //Acceso a BD
        final DAO dao = new DAO(menuRegistro.this);

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



                if (stackErrores.empty()) {

                    // Crear usuario

                    Usuario user = new Usuario();
                    user.setNombres(txNombres.getText().toString().trim());
                    user.setApellidos(txApellidos.getText().toString().trim());
                    user.setGenero(UIHelpers.getSelectedRadioText(rgGenero));
                    // Falta Casilla Email
                    user.setEmail(txUserNameRegistro.getText().toString().trim());

                    user.setPassword(txRepetirPassword.getText().toString().trim());
                    user.setUsername(txUserNameRegistro.getText().toString().trim());
                    // Implementar toma/upload de fotos, se podrá?
                    user.setPathFoto("p0");

                    // Usuario debe ser enviado a la base de datos o algo.

                    // Conexion a BD. Importante try catch en DAO, no existe


                    if (dao.registerUser(user)){
                        errorHandler.Toaster(enumErrores.registroExitoso,menuRegistro.this);
                        Intent aMenuPago = new Intent(menuRegistro.this, menuPago.class);
                        aMenuPago.putExtra("user",user);
                        startActivity(aMenuPago);
                    }else{
                        errorHandler.Toaster(enumErrores.errorDeRegistro,menuRegistro.this);
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
