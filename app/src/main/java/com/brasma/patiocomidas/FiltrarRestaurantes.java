package com.brasma.patiocomidas;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FiltrarRestaurantes {

    final Context context;
    Dialog dialogo;
    CardView cardViewBuscarUsuario;
    RecyclerView recyclerViewBuscarUsuario;
    Spinner spinnerFiltrar;
    TextView lblSinUsuarios;
    List<String> listCiudades;
    Button btnFiltrar;
    private finalizoDialogFiltrar interfaz;

    public FiltrarRestaurantes(Context context1,finalizoDialogFiltrar activity) {
        context = context1;
        interfaz=activity;
        dialogo = new Dialog(context);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);//false
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.getWindow().setGravity(Gravity.BOTTOM);
        dialogo.setContentView(R.layout.consumidor_dialog_filtrar_res);
        spinnerFiltrar=(Spinner)dialogo.findViewById(R.id.spinnerCiudad_FiltrarPrincipal);
        btnFiltrar=(Button)dialogo.findViewById(R.id.btnFiltrar_FiltrarRestaurant);
        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaz.filtrado(spinnerFiltrar.getSelectedItem().toString());
                dialogo.dismiss();
            }
        });
        cargarCiudadesSpinner();
        //lblSinUsuarios=(TextView)dialogo.findViewById(R.id.lblUsuariosRegistrados);
        //TextInputLayout textInputBuscarUsuario = (TextInputLayout) dialogo.findViewById(R.id.txtImputBuscarUsuario_DialogBuscarUsuario);
        //ImageButton btnBuscarUuario = (ImageButton) dialogo.findViewById(R.id.btnBuscarUsuario_DialogBuscarUsuario);
        /*btnBuscarUuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatos("");
            }
        });*/
        dialogo.show();
    }
    public void cargarCiudadesSpinner(){
        listCiudades= new ArrayList<>();
        listCiudades.add("Huaquillas");
        listCiudades.add("Machala");
        listCiudades.add("Santa Rosa");
        ArrayAdapter<String> listaCiudades2=new ArrayAdapter<String>(context,R.layout.consumidor_item_spinner,listCiudades);
        spinnerFiltrar.setAdapter(listaCiudades2);
    }
    public interface finalizoDialogFiltrar {
        void filtrado(String ciudad);
    }
}
