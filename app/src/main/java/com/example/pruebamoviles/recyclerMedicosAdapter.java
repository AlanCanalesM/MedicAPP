package com.example.pruebamoviles;

import android.app.LauncherActivity;
import android.content.ClipData;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class recyclerMedicosAdapter extends RecyclerView.Adapter<recyclerMedicosAdapter.ViewHolder> implements View.OnClickListener {


    private  View.OnClickListener listener;

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
            tvMedNom=(TextView) itemView.findViewById(R.id.nombreMed);
            tvMedEsp=(TextView) itemView.findViewById(R.id.especialidadMed);
            imagenMed=(ImageView) itemView.findViewById(R.id.imgMed);
        }


    }

    public List<MedicoModelo> medicoLista;
    private List<MedicoModelo> originalMed;


    public recyclerMedicosAdapter(List<MedicoModelo> medicoLista) {
        this.medicoLista = medicoLista;
        this.originalMed=new ArrayList<>();
        originalMed.addAll(medicoLista);
    }

    @NonNull
    @Override
    public recyclerMedicosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_medicos, viewGroup, false);
        recyclerMedicosAdapter.ViewHolder viewHolder=new recyclerMedicosAdapter.ViewHolder(view);
        //view.setOnClickListener((View.OnClickListener) this);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerMedicosAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvMedNom.setText(medicoLista.get(i).getNombre());
        viewHolder.tvMedEsp.setText(medicoLista.get(i).getEspecialidad());
        //Glide.with(viewHolder.itemView).load(ensaladaLista.get(i).getImagen()).into(viewHolder.imagenEnsa);
    }

    public void filtro(String strSearch){
        if(strSearch.length()==0){
            medicoLista.clear();
            medicoLista.addAll(originalMed);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                medicoLista.clear();
               List<MedicoModelo> collect=originalMed.stream().filter(i->i.getEspecialidad().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());


                medicoLista.addAll(collect);
            }else{
                medicoLista.clear();
                for( MedicoModelo i:originalMed){

                    if(i.getNombre().toLowerCase().contains(strSearch)){
                        medicoLista.add(i);
                    }

                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return medicoLista.size();
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
