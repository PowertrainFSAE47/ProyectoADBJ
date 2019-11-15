package com.example.proyectoadbj;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

}
