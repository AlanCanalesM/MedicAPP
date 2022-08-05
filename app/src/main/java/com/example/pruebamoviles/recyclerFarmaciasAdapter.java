package com.example.pruebamoviles;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class recyclerFarmaciasAdapter extends RecyclerView.Adapter<recyclerFarmaciasAdapter.ViewHolder> implements View.OnClickListener {


    private  View.OnClickListener listener;



    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtNombre, txtTelefono;
        private ImageView imgFarmacia;

        public ViewHolder(View itemView){
            super(itemView);
            txtNombre=(TextView) itemView.findViewById(R.id.nombreFarm);
            txtTelefono=(TextView) itemView.findViewById(R.id.telefonoFarm);
            imgFarmacia=(ImageView) itemView.findViewById(R.id.imgFarmacia);

        }
    }

    public List<FarmaciaModelo> farmaciasLista;

    public recyclerFarmaciasAdapter(List<FarmaciaModelo> farmaciasLista){

        this.farmaciasLista=farmaciasLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemfarmacia, parent, false);
        recyclerFarmaciasAdapter.ViewHolder viewHolder=new recyclerFarmaciasAdapter.ViewHolder(view);
        //view.setOnClickListener((View.OnClickListener) this);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerFarmaciasAdapter.ViewHolder viewHolder, int position) {

        viewHolder.txtNombre.setText(farmaciasLista.get(position).getNombre());
        viewHolder.txtTelefono.setText(farmaciasLista.get(position).getTelefono());
        Glide.with(viewHolder.itemView).load(farmaciasLista.get(position).getImagen()).into(viewHolder.imgFarmacia);

    }

    @Override
    public int getItemCount() {
        return farmaciasLista.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }
}
