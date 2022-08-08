package com.example.pruebamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AgregarFar extends AppCompatActivity {

    Button btnAgFar;
    EditText txtNom, txtTel, txtUbi, txtImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_far);

        btnAgFar=(Button) findViewById(R.id.btnAgFar);
        txtNom=(EditText) findViewById(R.id.agNombrefar);
        txtTel=(EditText) findViewById(R.id.agTelefonofar);
        txtImagen=(EditText) findViewById(R.id.agImagenfar);
        txtUbi=(EditText) findViewById(R.id.agUbifar);

        btnAgFar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgFarm("http://192.168.100.11/MedicApp/agregarfarm.php");
            }
        });
    }

    public void AgFarm(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Intent ac=new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Registrado correctamente!", Toast.LENGTH_LONG).show();
                //startActivity(ac);
                //overridePendingTransition(R.anim.left_in, R.anim.left_out);

                txtTel.setText("");
                txtImagen.setText("");
                txtUbi.setText("");
                txtNom.setText("");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Error: "+ error, Toast.LENGTH_LONG);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> datos=new HashMap<>();
                datos.put("nombre", txtNom.getText().toString());
                datos.put("imagen", txtImagen.getText().toString());
                datos.put("telefono", txtTel.getText().toString());
                datos.put("ubicacion", txtUbi.getText().toString());

                return datos;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}