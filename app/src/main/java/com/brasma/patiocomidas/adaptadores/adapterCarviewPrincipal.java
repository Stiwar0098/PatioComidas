package com.brasma.patiocomidas.adaptadores;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brasma.patiocomidas.PrincipalActivity;
import com.brasma.patiocomidas.R;
import com.brasma.patiocomidas.activity_registro;
import com.brasma.patiocomidas.entidades.empresasCardviewPrincipal;
import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.SphericalUtil;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class adapterCarviewPrincipal extends RecyclerView.Adapter<adapterCarviewPrincipal.ViewHolder> implements View.OnClickListener {

    public static List<empresasCardviewPrincipal> listaEmpresas;
    private View.OnClickListener listener;
    public static Context context;



    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public adapterCarviewPrincipal(List<empresasCardviewPrincipal> listaEmpresa,Context contextAux) {
        this.listaEmpresas = listaEmpresa;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_principal, parent, false);
        context = parent.getContext();
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreEmpresa, tipo, direccion,distancia;
        private ImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
//            context = itemView.getContext();
            foto=(ImageView) itemView.findViewById(R.id.imageView_CarviewPrincipal);
            nombreEmpresa = (TextView) itemView.findViewById(R.id.lblNombreEmpresa_CardviewPrincipal);
            tipo = (TextView) itemView.findViewById(R.id.lblTipoEmpresa_CarviewPrincipal);
            direccion = (TextView) itemView.findViewById(R.id.lblDireccionEmpresa_CarviewPrincipal);
            distancia = (TextView) itemView.findViewById(R.id.lblDistancia_CardviewPrincipal);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        double distancia=listaEmpresas.get(position).getDistancia();
        DecimalFormat df = new DecimalFormat(".#");
        Picasso.get().load(listaEmpresas.get(position).getFoto()).into(holder.foto);
        holder.nombreEmpresa.setText(listaEmpresas.get(position).getNombreEmpresa());
        holder.tipo.setText(listaEmpresas.get(position).getTipo());
        holder.direccion.setText(listaEmpresas.get(position).getDireccion());
        if (distancia>999.999){
            distancia=distancia/1000.0;
            holder.distancia.setText("Aprox. a "+df.format(distancia)+" kilometros");
        }else{
            holder.distancia.setText("Aprox. a "+df.format(distancia)+" metros");
        }

    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }
}
