package com.example.pruebamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class AgregarMed extends AppCompatActivity {

    Button btnagregarMed;
    EditText txtNombre, txtEsp, txtTel, txtCed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_med);

        btnagregarMed=(Button) findViewById(R.id.btnAgMed);
        txtNombre=(EditText)findViewById(R.id.agNombre);
        txtCed=(EditText)findViewById(R.id.agCedula);
        txtEsp=(EditText)findViewById(R.id.agEspecialidad);
        txtTel=(EditText)findViewById(R.id.agTelefono);

        btnagregarMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgMed("http://192.168.100.11/MedicApp/agregarmed.php");
            }
        });
    }

    public void AgMed(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Intent ac=new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Registrado correctamente!", Toast.LENGTH_LONG).show();
                //startActivity(ac);
                //overridePendingTransition(R.anim.left_in, R.anim.left_out);
                txtCed.setText("");
                txtNombre.setText("");
                txtEsp.setText("");
                txtTel.setText("");

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
                datos.put("nombre", txtNombre.getText().toString());
                datos.put("cedula", txtCed.getText().toString());
                datos.put("telefono", txtTel.getText().toString());
                datos.put("especialidad", txtEsp.getText().toString());

                return datos;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}