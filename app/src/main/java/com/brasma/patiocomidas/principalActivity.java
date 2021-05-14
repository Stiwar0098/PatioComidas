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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.brasma.patiocomidas.adaptadores.adapterCarviewPrincipal;
import com.brasma.patiocomidas.entidades.empresasCardviewPrincipal;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerViewEmpresas;
    private adapterCarviewPrincipal adapterEmpresas;
    private List<empresasCardviewPrincipal> listaEmpresas;
    private EditText txtBuscar;
    private Context context;
    private GoogleSignInClient mGoogleSignInClient;
    private Button btnCerrarSesion;
    private FusedLocationProviderClient ubicacion;
    private double latitud;
    private double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        context=this;
        txtBuscar=(EditText)findViewById(R.id.txtBuscar_Principal);
        btnCerrarSesion=(Button)findViewById(R.id.btnCerrarSesion_Principal);

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
                    filtrar(s.toString());
                }else{
                    cargarRecycler(listaEmpresas);
                }
            }
        });
        btnCerrarSesion.setOnClickListener(this);
        cargarUbicacion();
    }




    private void cargarRecycler(List<empresasCardviewPrincipal> lista){
        // crear lista de carview dentro del recycleview
        recyclerViewEmpresas = (RecyclerView) findViewById(R.id.recycler_Principal);
        recyclerViewEmpresas.setLayoutManager(new LinearLayoutManager(this));
        adapterEmpresas = new adapterCarviewPrincipal(lista,this);
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
        listaEmpresas.add(new empresasCardviewPrincipal(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"Mi covacha","Restaurant","Av la republica y 24 de mayo",-3.4729557,-80.2371387,0.0,false,false,false,false,"restaurant",false,false,false,false,false,false,false,false,false,false,false));
        listaEmpresas.add(new empresasCardviewPrincipal(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"cosecha","Restaurant","Av la republica y 24 de mayo",-3.4828109002677876,-80.225047217398,0.0,false,false,false,false,"restaurant",false,false,false,false,false,false,false,false,false,false,false));
        listaEmpresas.add(new empresasCardviewPrincipal(Uri.parse("https://img.goraymi.com/2018/05/07/9f504b99ce05011a601bb9572b09bd2b_lg.jpg"),"Conver","Restaurant","Av la republica y 24 de mayo",-3.4729557,-80.2371387,0.0,false,false,false,false,"restaurant",false,false,false,false,false,false,false,false,false,false,false));
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
}
