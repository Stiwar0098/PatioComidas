package com.brasma.patiocomidas.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brasma.patiocomidas.R;
import com.brasma.patiocomidas.entidades.Platos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterCarviewPlatos extends RecyclerView.Adapter<adapterCarviewPlatos.ViewHolder> implements View.OnClickListener {

    public  List<Platos> listaEmpresas;
    private View.OnClickListener listener;
    public static Context context;



    public void setOnClickListener2(View.OnClickListener listener) {
        this.listener = listener;
    }

    public adapterCarviewPlatos(List<Platos> listaEmpresa) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumidor_cardview_platos, parent, false);
        //context = parent.getContext();
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nombre,descripcion,precio;
        private ImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            nombre= (TextView) itemView.findViewById(R.id.lblNombrePlato_cardviewPlato);
            descripcion=(TextView)itemView.findViewById(R.id.lblDescripcionPlato_CardviewPlato);
            precio=(TextView)itemView.findViewById(R.id.lblPrecio_cardviewPlato);
            foto=(ImageView)itemView.findViewById(R.id.imagePlato_cardviewPlato);
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
        Platos op=listaEmpresas.get(position);
        Picasso.get().load(op.getFoto()).into(holder.foto);
        holder.nombre.setText(op.getNombre());
        holder.descripcion.setText(op.getDescripcion());
        holder.precio.setText(op.getPrecio()+"");
    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }
}
