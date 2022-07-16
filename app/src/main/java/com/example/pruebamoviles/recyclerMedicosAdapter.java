package com.example.pruebamoviles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerMedicosAdapter extends RecyclerView.Adapter<recyclerMedicosAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private View.OnClickListener listener2;

        public void setOnClickListener(View.OnClickListener listener){
            this.listener2=listener;
        }


        @Override
        public void onClick(View view) {
            if(listener2!=null){
                listener2.onClick(view);
            }

        }

        private TextView tvMedNom, tvMedEsp;
        private ImageView imagenMed;

        public ViewHolder(View itemView){
            super(itemView);
            tvMedNom=(TextView) itemView.findViewById(R.id.tvMedNom);
            tvMedEsp=(TextView) itemView.findViewById(R.id.tvMedEsp);
            imagenMed=(ImageView) itemView.findViewById(R.id.imagenMed);
        }


    }

    public List<MedicoModelo> medicoLista;

    public recyclerMedicosAdapter(List<MedicoModelo> medicoLista) {
        this.medicoLista = medicoLista;
    }

    @NonNull
    @Override
    public recyclerMedicosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_medico, viewGroup, false);
        recyclerMedicosAdapter.ViewHolder viewHolder=new recyclerMedicosAdapter.ViewHolder(view);
        //view.setOnClickListener((View.OnClickListener) this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerMedicosAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvMedNom.setText(medicoLista.get(i).getNombre());
        viewHolder.tvMedEsp.setText(medicoLista.get(i).getEspecialidad());
        //Glide.with(viewHolder.itemView).load(ensaladaLista.get(i).getImagen()).into(viewHolder.imagenEnsa);
    }



    @Override
    public int getItemCount() {
        return medicoLista.size();
    }

}
