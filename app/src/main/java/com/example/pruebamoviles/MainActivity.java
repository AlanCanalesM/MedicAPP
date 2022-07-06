package com.example.pruebamoviles;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button btninicio;
    TextInputEditText txtCorreo;
    TextInputEditText txtPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btninicio=(Button) findViewById(R.id.BTinicio);
        txtCorreo=(TextInputEditText) findViewById(R.id.ETcorreo);
        txtPass=(TextInputEditText) findViewById(R.id.ETpass);

        btninicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion("https://loncheriasftw.000webhostapp.com/loncheria/inicioSesion.php");
            }
        });


    }


    public void iniciarSesion(String URL){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.isEmpty()){
                    Intent i=new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(i);

                }else{
                    Toast.makeText(getApplicationContext(), "Credenciales erroneas", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<>();
                parametros.put("usuario", txtCorreo.getText().toString());
                parametros.put("password", txtPass.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}