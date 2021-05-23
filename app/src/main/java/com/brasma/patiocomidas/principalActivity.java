package com.brasma.patiocomidas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.brasma.patiocomidas.adaptadores.adapterCarviewPrincipal;
import com.brasma.patiocomidas.entidades.Procesos;
import com.brasma.patiocomidas.entidades.Restaurante;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener, FiltrarRestaurantes.finalizoDialogFiltrar {

    private RecyclerView recyclerViewEmpresas;
    private adapterCarviewPrincipal adapterEmpresas;
    private List<Restaurante> listaEmpresas;
    private TextInputEditText txtBuscar;
    private Context context;
    private GoogleSignInClient mGoogleSignInClient;
    private Button btnCerrarSesion;
    private FusedLocationProviderClient ubicacion;
    private ImageButton btnFiltrar;
    private double latitud;
    private double longitud;
    private BottomSheetDialog botBoottomSheetDialog;
    Spinner spinnerFiltrar;
    List<String> listCiudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumidor_activity_principal);
        context=this;
        Procesos.cargandoIniciar(this);
        txtBuscar=(TextInputEditText)findViewById(R.id.txtBuscar_Principal);
        btnCerrarSesion=(Button)findViewById(R.id.btnCerrarSesion_Principal);
        btnFiltrar=(ImageButton)findViewById(R.id.btnFiltrar_Principal);
        //cerrar sesion
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

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
                    filtrar(s.toString(),"");
                }else{
                    cargarRecycler(listaEmpresas);
                }
            }
        });
        btnCerrarSesion.setOnClickListener(this);
        btnFiltrar.setOnClickListener(this);
        cargarUbicacion();
    }




    private void cargarRecycler(List<Restaurante> lista){
        // crear lista de carview dentro del recycleview
        recyclerViewEmpresas = (RecyclerView) findViewById(R.id.recycler_Principal);
        recyclerViewEmpresas.setLayoutManager(new  LinearLayoutManager(this));
        adapterEmpresas = new adapterCarviewPrincipal(lista);
        recyclerViewEmpresas.setAdapter(adapterEmpresas);
        adapterEmpresas.setOnClickListener2(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurante us = listaEmpresas.get(recyclerViewEmpresas.getChildAdapterPosition(v));
                abrirCategoria();
            }
        });
        Procesos.cargandoDetener();
    }

    private void cargarListadoEmpresas() {
        listaEmpresas=new ArrayList<>();
        listaEmpresas.add(new Restaurante(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"Mi covacha","Restaurant","Av la republica y 24 de mayo",-3.4729557,-80.2371387,0.0,"restaurant",true,true,true,true,true,true,"Huaquillas"));
        listaEmpresas.add(new Restaurante(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"cosecha","Restaurant","Av la republica y 24 de mayo",-3.4828109002677876,-80.225047217398,0.0,"quiosco",true,true,true,true,false,false,"Machala"));
        listaEmpresas.add(new Restaurante(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"Conver","Restaurant","Av la republica y 24 de mayo",-3.4729557,-80.2371387,0.0,"carpa",true,false,true,true,true,true,"Santa Rosa"));
        for (int i=0;i<listaEmpresas.size(); i++){
           listaEmpresas.get(i).setDistancia(distance(latitud,longitud,listaEmpresas.get(i).getLatitud(),listaEmpresas.get(i).getLongitud()));
        }
        Toast.makeText(this, listaEmpresas.get(1).getDistancia()+"dkdkdkdkkdkdkkd", Toast.LENGTH_SHORT).show();
        cargarRecycler(listaEmpresas);
    }
    private void cargarUbicacion() {
        ubicacion= LocationServices.getFusedLocationProviderClient(context);
        ubicacion.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    latitud=location.getLatitude();
                    longitud=location.getLongitude();
                    cargarListadoEmpresas();
                }
            }
        });
    }
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double distance1 = SphericalUtil. computeDistanceBetween(new LatLng(lat1,lon1), new LatLng(lat2,lon2));
        double earthRadius = 6371.0;
        double latDiff = Math.toRadians(lat2-lat1);
        double lngDiff = Math.toRadians(lon2-lon1);
        double a =  Math.sin(latDiff /2.0) * Math.sin(latDiff /2.0) +
                Math.cos(Math.toRadians(lat1))*
                        Math.cos(Math.toRadians(lat2))* Math.sin(lngDiff /2.0) *
                        Math.sin(lngDiff /2.0);
        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance2 = earthRadius * c;
        double meterConversion = 1609.0;
        distance2= distance2 * meterConversion;
        distance1=(distance1+distance2)/2.0;
        return distance1;
    }
    private void abrirCategoria(){
        Intent intent= new Intent(this, VerCategoriaActivity.class);
        startActivity(intent);
    }

    private void filtrar(String buscar, String ciudad){
        List<Restaurante> aux2=new ArrayList<>();
        for (Restaurante aux:listaEmpresas) {
            if(aux.getNombreEmpresa().toLowerCase().contains(buscar.toLowerCase())){
                if (!ciudad.isEmpty()){
                    if (aux.getCiudad().toLowerCase().equals(ciudad.toLowerCase())){
                        aux2.add(aux);
                    }
                }else{
                    aux2.add(aux);
                }
            }
        }
        cargarRecycler(aux2);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCerrarSesion_Principal:
                cerrarSesion();
                break;
            case R.id.btnFiltrar_Principal:
                    abrirFiltrar();
                break;
        }
    }

    private void abrirFiltrar(){
        botBoottomSheetDialog=new BottomSheetDialog(PrincipalActivity.this, R.style.BottomSheetTheme);

        View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.consumidor_dialog_filtrar_res,
                (LinearLayout) findViewById(R.id.bottom_sheetContainer));

        spinnerFiltrar=(Spinner)sheetView.findViewById(R.id.spinnerCiudad_FiltrarPrincipal);
        sheetView.findViewById(R.id.btnFiltrar_FiltrarRestaurant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aux =spinnerFiltrar.getSelectedItem().toString();
                if(!aux.equals("Seleccione")){
                    filtrar("",aux);
                }else{
                    Toast.makeText(PrincipalActivity.this, "Seleccione una ciudad", Toast.LENGTH_SHORT).show();
                }
                botBoottomSheetDialog.dismiss();
            }
        });
        botBoottomSheetDialog.setContentView(sheetView);
        cargarCiudadesSpinner();
        botBoottomSheetDialog.show();
    }
    public void cargarCiudadesSpinner(){
        listCiudades= new ArrayList<>();
        listCiudades.add("Seleccione");
        listCiudades.add("Huaquillas");
        listCiudades.add("Machala");
        listCiudades.add("Santa Rosa");
        ArrayAdapter<String> listaCiudades2=new ArrayAdapter<String>(context,R.layout.consumidor_item_spinner,listCiudades);
        if (listaCiudades2!=null){
            spinnerFiltrar.setAdapter(listaCiudades2);
        }else{
            Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
        }
    }

    private void cerrarSesion() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(PrincipalActivity.this,"Sesion cerrada",Toast.LENGTH_SHORT).show();
                        abrirLogin();
                    }
                });
    }

    private void abrirLogin() {
        Intent intent= new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void filtrado(String ciudad) {
        Toast.makeText(context, ciudad, Toast.LENGTH_SHORT).show();
        filtrar("",ciudad);
    }
}
