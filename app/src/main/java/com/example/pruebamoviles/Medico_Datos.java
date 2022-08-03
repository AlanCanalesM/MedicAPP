package com.example.pruebamoviles;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class Medico_Datos extends AppCompatActivity {


    TextView txtNombre;
    TextView txtEspecialidad;
    TextView txtCedula;
    TextView txtTelefono;


    //private RecyclerView recyclerMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_datos);

        Button btnLlamar = findViewById(R.id.btnCall);
        Bundle bolsa = getIntent().getExtras();

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtNombre.setText(bolsa.getString("nombre"));

        txtEspecialidad =(TextView) findViewById(R.id.txtEspecialidad);
        txtEspecialidad.setText(bolsa.getString("especialidad"));

        txtCedula =(TextView) findViewById(R.id.txtCedula);
        txtCedula.setText(bolsa.getString("cedula"));

        txtTelefono =(TextView) findViewById(R.id.txtTelefono);
        txtTelefono.setText(bolsa.getString("telefono"));


        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = bolsa.getString("telefono");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);
                //Toast.makeText(Medico_Datos.this, "Datos"+ medicos.get
                    //    (btnLlamar.getCh).show();

            }
        });
        //MostrarMedicos("http://192.168.1.67/MedicApp/mostrarMedicos.php");


    }
/*
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
                                medico.getString("Cedula"),
                                medico.getString("Telefono")
                        ));



                    }




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

*/
}