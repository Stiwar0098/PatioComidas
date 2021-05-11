package com.brasma.patiocomidas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.brasma.patiocomidas.adaptadores.adapterCarviewPrincipal;
import com.brasma.patiocomidas.entidades.empresasCardviewPrincipal;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEmpresas;
    private adapterCarviewPrincipal adapterEmpresas;
    private List<empresasCardviewPrincipal> listaEmpresas;
    private EditText txtBuscar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        context=this;
        txtBuscar=(EditText)findViewById(R.id.txtBuscar_Principal);
        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    filtrar(s.toString());
                }else{
                    cargarRecycler(listaEmpresas);
                }
            }
        });
        cargarListadoEmpresas();
        cargarRecycler(listaEmpresas);
    }
    private void cargarRecycler(List<empresasCardviewPrincipal> lista){
        // crear lista de carview dentro del recycleview
        recyclerViewEmpresas = (RecyclerView) findViewById(R.id.recycler_Principal);
        recyclerViewEmpresas.setLayoutManager(new LinearLayoutManager(this));
        adapterEmpresas = new adapterCarviewPrincipal(lista);
        recyclerViewEmpresas.setAdapter(adapterEmpresas);
        adapterEmpresas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empresasCardviewPrincipal us = listaEmpresas.get(recyclerViewEmpresas.getChildAdapterPosition(v));
                if(v.getId()==R.id.btnVerMenu_CarviewPrincipal){
                    abrirMenu();
                }
            }
        });
    }

    private void cargarListadoEmpresas() {
        listaEmpresas=new ArrayList<>();
        listaEmpresas.add(new empresasCardviewPrincipal(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"Mi covacha","Restaurant","Av la republica y 24 de mayo"));
        listaEmpresas.add(new empresasCardviewPrincipal(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"cosecha","Restaurant","Av la republica y 24 de mayo"));
        listaEmpresas.add(new empresasCardviewPrincipal(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"Conver","Restaurant","Av la republica y 24 de mayo"));
        /*for (int i=0;i<=20;i++){
           listaEmpresas.add(new empresasCardviewPrincipal(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"Mi covacha","Restaurant","Av la republica y 24 de mayo"));
        }*/
    }

    private void abrirMenu(){
        Intent intent= new Intent(this, VerMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void filtrar(String buscar){
        List<empresasCardviewPrincipal> aux2=new ArrayList<>();
        for (empresasCardviewPrincipal aux:listaEmpresas) {
            if(aux.getNombreEmpresa().toLowerCase().contains(buscar.toLowerCase())){
                aux2.add(aux);
            }
        }
        cargarRecycler(aux2);
    }
}
