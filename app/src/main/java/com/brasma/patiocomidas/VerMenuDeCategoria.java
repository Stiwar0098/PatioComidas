package com.brasma.patiocomidas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class VerMenuDeCategoria extends AppCompatActivity {
    private RecyclerView recyclerViewCategoria;
    private List<String> listaPlatosDeCategoria;
    private ImageButton btnFiltrar;
    private BottomSheetDialog botBoottomSheetDialog;
    private TextInputLayout txtBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumidor_activity_ver_menu_de_categoria);
    }
}
