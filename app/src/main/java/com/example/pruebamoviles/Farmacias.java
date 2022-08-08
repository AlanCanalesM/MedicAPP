package com.example.pruebamoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Farmacias extends AppCompatActivity {

    private RecyclerView recyclerFarmacia;
    private recyclerFarmaciasAdapter recyclerFarmaciasAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacias);

        recyclerFarmacia=(RecyclerView) findViewById(R.id.recyclerFarmacias);
        recyclerFarmacia.setLayoutManager(new LinearLayoutManager(this));
        searchView=(SearchView)findViewById(R.id.svSearchMed);
        MostrarFarmacias("http://192.168.100.11/MedicApp/mostrarFarmacias.php");
    }

    public void MostrarFarmacias(String URL){

        List<FarmaciaModelo> farmacias = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject farmacia = array.getJSONObject(i);

                        farmacias.add(new FarmaciaModelo(farmacia.getString(
                                "nombre"),
                                farmacia.getString("imagen"),
                                farmacia.getString("telefono"),
                                farmacia.getString("ubicacion")
                        ));


                    }

                    recyclerFarmaciasAdapter = new recyclerFarmaciasAdapter(farmacias);
                    recyclerFarmaciasAdapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Intent intent=new Intent(getApplicationContext(), Medico_Datos.class);
                            /*Bundle bolsa = new Bundle();
                            bolsa.putString("nombre", medicos.get(recyclerMedicos.getChildAdapterPosition(view)).getNombre());
                            bolsa.putString("especialidad", medicos.get(recyclerMedicos.getChildAdapterPosition(view)).getEspecialidad());
                            bolsa.putString("cedula", medicos.get(recyclerMedicos.getChildAdapterPosition(view)).getCedula());
                            bolsa.putString("telefono", medicos.get(recyclerMedicos.getChildAdapterPosition(view)).getTelefono());
                            intent.putExtras(bolsa);*/
                            //startActivity(intent);
                            String nombre = farmacias.get(recyclerFarmacia.getChildAdapterPosition(view)).getNombre().toString();
                            String ubicacion=farmacias.get(recyclerFarmacia.getChildAdapterPosition(view)).getUbicacion().toString();
                            //Toast.makeText(getApplicationContext(), "Nombre: "+ farmacias.get(recyclerFarmacia.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                            Uri intentUri = Uri.parse("geo:"+ ubicacion+"?z=16&q="+ubicacion+"("+nombre+")");
                Intent intent = new Intent(Intent.ACTION_VIEW, intentUri);
                startActivity(intent);
                        }
                    });
                    recyclerFarmacia.setAdapter(recyclerFarmaciasAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en conexion", Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}