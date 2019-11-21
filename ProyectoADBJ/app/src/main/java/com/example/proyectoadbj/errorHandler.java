package com.example.proyectoadbj;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class errorHandler {

    // Clase para manejar todos los errores de la aplicacion.


    public static void Toaster(enumErrores err, Context context) {

        // Crear toast
        Toast myToast = new Toast(context);
        myToast.setGravity(Gravity.TOP, 0, 0);

        switch (err) {
            case loginError:
                // Mostrar mensaje de error
                myToast.makeText(context, "Usuario o contraseña inválidos", Toast.LENGTH_SHORT).show();
                break;
            case sinNombre:
                myToast.makeText(context, "Debe ingresar un nombre", Toast.LENGTH_SHORT).show();
                break;
            case sinApellido:
                myToast.makeText(context, "Debe ingresar un apellido", Toast.LENGTH_SHORT).show();
                break;
            case sinPassword:
                myToast.makeText(context, "Ingrese un password", Toast.LENGTH_SHORT).show();
                break;
            case passwordNoCoincide:
                myToast.makeText(context, "Ambos passwords deben coincidir", Toast.LENGTH_SHORT).show();
                break;

            case sinEmail:
                myToast.makeText(context, "Ingrese un email", Toast.LENGTH_SHORT).show();
                break;

            case noImplementado:
                myToast.makeText(context, "Funcionalidad no implementada", Toast.LENGTH_SHORT).show();
                break;
            case errorDeRegistro:
                myToast.makeText(context, "ERROR DE REGISTRO!", Toast.LENGTH_SHORT).show();
                break;
            case registroExitoso:
                myToast.makeText(context, "Registro Exitoso!", Toast.LENGTH_SHORT).show();
                break;
            case sinUserName:
                myToast.makeText(context, "Debe introducir un nombre de usuario", Toast.LENGTH_SHORT).show();
                break;
            case usuarioYaExiste:
                myToast.makeText(context, "El usuario ya existe en el sistema", Toast.LENGTH_SHORT).show();
                break;
            case usuarioRegistradoEnCalendario:
                myToast.makeText(context, "Te registraste exitosamente en esta actividad!", Toast.LENGTH_SHORT).show();
                break;
            case ustedYaEstaRegistrado:
                myToast.makeText(context, "Esta actividad ya se encuentra en su calendario!", Toast.LENGTH_SHORT).show();
                break;
            case eliminadoCorrectamente:
                myToast.makeText(context, "Registro eliminado correctamente!", Toast.LENGTH_SHORT).show();
                break;
            case noSePuedeEliminar:
                myToast.makeText(context, "No se puede eliminar este registro!", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show();
        }
    }

    public static void alert(enumErrores err, Context context) {
        switch (err) {
            case loginError:
                break;
            case sinNombre:
                break;
            case sinApellido:
                break;
            case sinPassword:
                break;
            case passwordNoCoincide:
                break;
            default:
                Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show();
        }
    }
}
