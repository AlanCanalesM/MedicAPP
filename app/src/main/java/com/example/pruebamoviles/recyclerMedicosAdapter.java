package com.example.pruebamoviles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerMedicosAdapter extends RecyclerView.Adapter<recyclerMedicosAdapter.ViewHolder> {



    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private View.OnClickListener listener;

        public void setOnClickListener(View.OnClickListener listener){
            this.listener=listener;
        }

        @Override
        public void onClick(View view) {

            if (listener != null) {

                    listener.onClick(view);
            }
        }

        private TextView tvMedNom, tvMedEsp;

        public ViewHolder(View itemView){
        super(itemView);

        tvMedNom=(TextView)itemView.findViewById(R.id.tvMedNom);
        tvMedEsp=(TextView) itemView.findViewById(R.id.tvMedEsp);
        }
    }

    public List<MedicoModelo> listaMedicos;

    public recyclerMedicosAdapter(List<MedicoModelo> listaMedicos){

        this.listaMedicos=listaMedicos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medico, parent, false);
        recyclerMedicosAdapter.ViewHolder viewHolder=new recyclerMedicosAdapter.ViewHolder(view);
        view.setOnClickListener((View.OnClickListener) this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerMedicosAdapter.ViewHolder viewHolder, int position) {

        viewHolder.tvMedNom.setText(listaMedicos.get(position).getNombre());
        viewHolder.tvMedEsp.setText(listaMedicos.get(position).getEspecialidad());
        //Glide.with(viewHolder.itemView).load(ensaladaLista.get(i).getImagen()).into(viewHolder.imagenEnsa);

    }

    @Override
    public int getItemCount() {
        return listaMedicos.size();
    }

}
