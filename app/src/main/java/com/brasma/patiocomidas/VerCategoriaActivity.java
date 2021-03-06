package com.brasma.patiocomidas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.brasma.patiocomidas.adaptadores.adapterCardviewCategoria;
import com.brasma.patiocomidas.adaptadores.adapterCarviewPlatos;
import com.brasma.patiocomidas.entidades.Categorias;
import com.brasma.patiocomidas.entidades.Platos;
import com.brasma.patiocomidas.entidades.Procesos;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class VerCategoriaActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerViewCategoria;
    private List<Categorias> listaCategoria;
    private List<Platos> listaPlatos;
    private ImageButton btnFiltrar;
    private BottomSheetDialog botBoottomSheetDialog;
    private RadioButton radioButtonCategoria,radioButtonMenu;
    private TextInputLayout txtBuscar;
    private boolean isCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumidor_activity_ver_categoria);
        Procesos.cargandoIniciar(this);
        isCategoria=true;
        btnFiltrar=(ImageButton)findViewById(R.id.btnFiltrar_VerCategoria);
        txtBuscar=(TextInputLayout)findViewById(R.id.txtBuscar_VerCategoria);
        txtBuscar.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isCategoria){
                    if(!s.toString().equals("")){
                        filtrar(s.toString());
                    }else{
                        cargarRecyclerCategoria();
                    }
                }else{
                    if(!s.toString().equals("")){
                        filtrar(s.toString());
                    }else{
                        cargarRecyclerPlatos();
                    }
                }

            }
        });
        btnFiltrar.setOnClickListener(this);
        cargarListas();
        cargarRecyclerCategoria();
    }

    private void cargarListas() {
        listaCategoria=new ArrayList<>();
        listaCategoria.add(new Categorias("Embutidos"));
        listaCategoria.add(new Categorias("Gaseosas"));
        listaCategoria.add(new Categorias("Almuerzos"));
        listaCategoria.add(new Categorias("Merienda"));
        listaPlatos=new ArrayList<>();
        listaPlatos.add(new Platos(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"arroz","muy rico","Embutidos",5.5));
        listaPlatos.add(new Platos(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"seco","muy rico","Almuerzos",50));
        listaPlatos.add(new Platos(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"menestra","muy rico","Merienda",3.75));
        listaPlatos.add(new Platos(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"frejol","muy rico","Gaseosas",2));
    }

    private void filtrar(String buscar){
        //Procesos.cargandoIniciar(this);
        List<Categorias> aux2=new ArrayList<>();
        List<Platos> aux3=new ArrayList<>();
        if(isCategoria){
            for (Categorias aux:listaCategoria) {
                if(aux.getNombre().toLowerCase().contains(buscar.toLowerCase())){
                    aux2.add(aux);
                }
            }
            cargarRecycler(aux2,null);
        }else{
            aux2=new ArrayList<>();
            for (Platos aux:listaPlatos) {
                if(aux.getNombre().toLowerCase().contains(buscar.toLowerCase())){
                    aux3.add(aux);
                }
            }
            cargarRecycler(null,aux3);
        }
    }
    private void cargarRecyclerCategoria() {
        cargarRecycler(listaCategoria,null);
    }

    private void cargarRecyclerPlatos() {cargarRecycler(null,listaPlatos);}
    private void abrirFiltrar(){
        botBoottomSheetDialog=new BottomSheetDialog(VerCategoriaActivity.this, R.style.BottomSheetTheme);

        View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.consumidor_dialog_filtrar_categoria,
                (LinearLayout) findViewById(R.id.bottom_sheetContainer));
        radioButtonCategoria =(RadioButton)sheetView.findViewById(R.id.rdbCategoria_FiltrarCategoria);
        radioButtonMenu=(RadioButton)sheetView.findViewById(R.id.rdbMenu_FiltrarCategoria);
        if(isCategoria){
            radioButtonCategoria.setChecked(true);
        }else{
            radioButtonMenu.setChecked(true);
        }
        radioButtonCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCategoria=true;
                cargarRecyclerCategoria();
                botBoottomSheetDialog.dismiss();
            }
        });

        radioButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCategoria=false;
                cargarRecyclerPlatos();
                botBoottomSheetDialog.dismiss();
            }
        });

        /*sheetView.findViewById(R.id.btnFiltrar_FiltrarCategoria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButtonCategoria.isChecked()){
                    isCategoria=true;
                    cargarRecyclerCategoria();
                }else{
                    isCategoria=false;
                    cargarRecyclerPlatos();
                }
                botBoottomSheetDialog.dismiss();
            }
        });*/
        botBoottomSheetDialog.setContentView(sheetView);
        botBoottomSheetDialog.show();
    }
    adapterCardviewCategoria adapterCategoria;
    adapterCarviewPlatos adapterPlatos;
    private void cargarRecycler(List<Categorias> listaCategoria2,List<Platos> listaPlatos2){
        // crear lista de carview dentro del recycleview
        recyclerViewCategoria=(RecyclerView)findViewById(R.id.recyclerView_VerCtegoria);
        recyclerViewCategoria.setLayoutManager(new LinearLayoutManager(this));
        if(isCategoria){
            adapterCategoria = new adapterCardviewCategoria(listaCategoria2);
            recyclerViewCategoria.setAdapter(adapterCategoria);
            adapterCategoria.setOnClickListener2(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirMenu();
                }
            });
        }else{
            adapterPlatos = new adapterCarviewPlatos(listaPlatos2);
            recyclerViewCategoria.setAdapter(adapterPlatos);
        }
        Procesos.cargandoDetener();
    }

    private void abrirMenu() {
        Intent intent= new Intent(this, VerMenuDeCategoria.class);
        startActivity(intent);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFiltrar_VerCategoria:
                abrirFiltrar();
                break;
        }
    }
}
