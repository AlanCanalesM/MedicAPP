package com.example.pruebamoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class Medicos extends AppCompatActivity {

    private RecyclerView recyclerMedicos;
    private recyclerMedicosAdapter recyclerMedicosAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos);

        recyclerMedicos = (RecyclerView) findViewById(R.id.recyclerMedicos);
        recyclerMedicos.setLayoutManager(new LinearLayoutManager(this));
        MostrarMedicos("http://192.168.100.11/MedicApp/mostrarMedicos.php");

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

                        medicos.add(new MedicoModelo(medico.getString(
                                "Nombre"),
                                medico.getString("Especialidad"),
                                medico.getString("Cedula"),
                                medico.getString("Telefono")
                        ));


                    }

                    recyclerMedicosAdapter1 = new recyclerMedicosAdapter(medicos);
                    recyclerMedicosAdapter1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(getApplicationContext(), Medico_Datos.class);
                            Bundle bolsa = new Bundle();
                            bolsa.putString("nombre", medicos.get(recyclerMedicos.getChildAdapterPosition(view)).getNombre());
                            bolsa.putString("especialidad", medicos.get(recyclerMedicos.getChildAdapterPosition(view)).getEspecialidad());
                            bolsa.putString("cedula", medicos.get(recyclerMedicos.getChildAdapterPosition(view)).getCedula());
                            bolsa.putString("telefono", medicos.get(recyclerMedicos.getChildAdapterPosition(view)).getTelefono());
                            intent.putExtras(bolsa);
                            startActivity(intent);
                           // Toast.makeText(Medicos.this, "Datos"+ medicos.get
                               //      (recyclerMedicos.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                        }
                    });
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