package com.example.proyectoadbj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class menuInfo extends AppCompatActivity {

    private ToggleButton tbtMapa;
    private ImageView imgMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);

        tbtMapa = (ToggleButton) findViewById(R.id.tbtMapa);
        imgMapa = (ImageView) findViewById(R.id.imgMapa);
        botonMapa();
        imgMapa.setVisibility(View.INVISIBLE);

    }

    private void botonMapa(){
        tbtMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int img = 0;
                tbtMapa.setSelected(false);
                img = getResources().getIdentifier("@drawable/foto8", null, getPackageName());
                if (tbtMapa.isChecked()){
                    imgMapa.setVisibility(View.VISIBLE);
                }else{
                    imgMapa.setVisibility(View.INVISIBLE);
                }
                imgMapa.setImageResource(img);
            }
        });
    }
}
