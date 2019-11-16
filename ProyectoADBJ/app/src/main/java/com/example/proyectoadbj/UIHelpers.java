package com.example.proyectoadbj;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class UIHelpers {


    public static String getSelectedRadioText(RadioGroup rg){
        // Obtener radioboton presionado

        int radioButtonID = rg.getCheckedRadioButtonId();
        System.out.println("Radiobutton ID: "+radioButtonID);
        View radioButton = rg.findViewById(radioButtonID);
        int idx = rg.indexOfChild(radioButton);
        RadioButton r = (RadioButton) rg.getChildAt(idx);
        return r.getText().toString();
    }

    public static void fillSpinner(Spinner spinner, ArrayList<String> lista, Context context){

        ArrayAdapter<String> adapter = new ArrayAdapter <> (context, android.R.layout.simple_dropdown_item_1line,lista);
        spinner.setAdapter(adapter);
    }

}
