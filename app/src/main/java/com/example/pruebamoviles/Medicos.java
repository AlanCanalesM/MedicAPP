package com.example.pruebamoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class Medicos extends AppCompatActivity {

    private RecyclerView recyclerMedicos;
    private recyclerMedicosAdapter recyclerMedicosAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos);

        recyclerMedicos=(RecyclerView)findViewById(R.id.recyclerMedicos);
        recyclerMedicos.setLayoutManager(new LinearLayoutManager(this));
        MostrarMedicos("http://192.168.100.123/MedicApp/mostrarMedicos.php");

    }

    public void MostrarMedicos(String URL) {

        List<MedicoModelo> medicos = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject medico = array.getJSONObject(i);

                        medicos.add(new MedicoModelo(medico.getString("Nombre"),
                                medico.getString("Especialidad"),
                                medico.getString("Cedula")
                                ));



                    }

                    recyclerMedicosAdapter1 = new recyclerMedicosAdapter(medicos);
                    recyclerMedicos.setAdapter(recyclerMedicosAdapter1);


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