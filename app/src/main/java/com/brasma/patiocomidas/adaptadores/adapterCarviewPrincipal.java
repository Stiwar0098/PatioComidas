package com.brasma.patiocomidas.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brasma.patiocomidas.R;
import com.brasma.patiocomidas.entidades.Restaurante;
import com.brasma.patiocomidas.infoServicios;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class adapterCarviewPrincipal extends RecyclerView.Adapter<adapterCarviewPrincipal.ViewHolder> implements View.OnClickListener {

    public static List<Restaurante> listaEmpresas;
    private View.OnClickListener listener;
    public static Context context;



    public void setOnClickListener2(View.OnClickListener listener) {
        this.listener = listener;
    }

    public adapterCarviewPrincipal(List<Restaurante> listaEmpresa) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumidor_cardview_principal, parent, false);
        //context = parent.getContext();
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nombreEmpresa, tipo,ciudad,distancia,lblAbiertoCerrado;
        private ImageView foto,restaurant,quiosco,carpa,servirse,llevar,delivery,wifi,credito;
        private ImageButton btnInfoServisio;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            foto=(ImageView) itemView.findViewById(R.id.imageView_CarviewPrincipal);
            nombreEmpresa = (TextView) itemView.findViewById(R.id.lblNombreEmpresa_CardviewPrincipal);
            tipo = (TextView) itemView.findViewById(R.id.lblTipoEmpresa_CarviewPrincipal);
            ciudad= (TextView) itemView.findViewById(R.id.lblDireccionEmpresa_CarviewPrincipal);
            lblAbiertoCerrado= (TextView) itemView.findViewById(R.id.lblAbierto_Cerrado_CardviewPrincipal);
            distancia = (TextView) itemView.findViewById(R.id.lblDistancia_CardviewPrincipal);
            btnInfoServisio=(ImageButton)itemView.findViewById(R.id.btnInfo_CarviewPrincipal);
            restaurant=(ImageView)itemView.findViewById(R.id.imageRestaurant_cardviewPrincipal);
            quiosco=(ImageView)itemView.findViewById(R.id.imageKiosko_cardviewPrincipal);
            carpa=(ImageView)itemView.findViewById(R.id.imageCarpa_cardviewPrincipal);
            servirse=(ImageView)itemView.findViewById(R.id.imageParaServirse_cardviewPrincipal);
            llevar=(ImageView)itemView.findViewById(R.id.imageParaLlevar_cardviewPrincipal);
            delivery=(ImageView)itemView.findViewById(R.id.imageDelivery_cardviewPrincipal);
            wifi=(ImageView)itemView.findViewById(R.id.imageWifi_cardviewPrincipal);
            credito=(ImageView)itemView.findViewById(R.id.imageTarjetaCredito_cardviewPrincipal);
        }
        void setOnClickListener() {
            btnInfoServisio.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.btnInfo_CarviewPrincipal:
                    intent=new Intent(context, infoServicios.class);
                    context.startActivity(intent);
                    break;
            }

        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurante op=listaEmpresas.get(position);
        double distancia=op.getDistancia();
        DecimalFormat df = new DecimalFormat(".#");
        Picasso.get().load(op.getFoto()).into(holder.foto);
        holder.nombreEmpresa.setText(op.getNombreEmpresa());
        holder.tipo.setText(op.getTipo());
        holder.ciudad.setText(op.getCiudad());
        holder.setOnClickListener();
        if (distancia>999.999){
            distancia=distancia/1000.0;
            holder.distancia.setText("Aprox. a "+df.format(distancia)+" kilometros");
        }else{
            holder.distancia.setText("Aprox. a "+df.format(distancia)+" metros");
        }
        if (op.getTipoLocal().equals("restaurant")){
            holder.restaurant.setVisibility(View.VISIBLE);
        }else  if (op.getTipoLocal().equals("quiosco")){
            holder.quiosco.setVisibility(View.VISIBLE);
        }else {
            holder.carpa.setVisibility(View.VISIBLE);
        }
        if (op.isParaServirse()){
            holder.servirse.setVisibility(View.VISIBLE);
        }
        if(op.isParaLlevar()){
            holder.llevar.setVisibility(View.VISIBLE);
        }
        if(op.isDeliveryPropio()){
            holder.delivery.setVisibility(View.VISIBLE);
        }
        if(op.isWifi()){
            holder.wifi.setVisibility(View.VISIBLE);
        }
        if(op.isAceptaTarjetaCredito()){
            holder.credito.setVisibility(View.VISIBLE);
        }

        if(op.isAbierCerrad()){
            holder.lblAbiertoCerrado.setTextColor(context.getResources().getColor(R.color.colorVerde));
            holder.lblAbiertoCerrado.setText(R.string.Abierto);
        }else{
            holder.lblAbiertoCerrado.setTextColor(context.getResources().getColor(R.color.colorRojo));
            holder.lblAbiertoCerrado.setText(R.string.Cerrado);
        }

    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }
}
