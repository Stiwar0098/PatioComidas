package com.brasma.patiocomidas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class activity_registro extends AppCompatActivity {

    Button btnCerrarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnCerrarSesion= (Button)findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(this);
    }

}
