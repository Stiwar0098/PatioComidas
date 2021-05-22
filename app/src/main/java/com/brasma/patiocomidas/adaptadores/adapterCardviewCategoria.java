package com.brasma.patiocomidas.adaptadores;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.brasma.patiocomidas.R;
import com.brasma.patiocomidas.entidades.empresasCardviewPrincipal;
import com.brasma.patiocomidas.infoServicios;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class adapterCardviewCategoria extends RecyclerView.Adapter<adapterCardviewCategoria.ViewHolder> implements View.OnClickListener {
    public List<String> listaEmpresas;
    private View.OnClickListener listener;
    public static Context context;



    public void setOnClickListener2(View.OnClickListener listener) {
        this.listener = listener;
    }
    public adapterCardviewCategoria(List<String> listaEmpresa) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumidor_cardview_categoria, parent, false);
        //context = parent.getContext();
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nombre;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            nombre = (TextView) itemView.findViewById(R.id.lblNombreCategoria_CardviewCategoria);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String op=listaEmpresas.get(position);
        holder.nombre.setText(op);
    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }
}
