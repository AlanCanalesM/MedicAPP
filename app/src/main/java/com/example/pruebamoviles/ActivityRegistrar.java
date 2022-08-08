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

public class ActivityRegistrar extends AppCompatActivity {

    EditText editName,editUser, editPass, editEmail;
    Button btnRegistrar, btnCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        editName=(EditText) findViewById(R.id.editName);
        editUser=(EditText) findViewById(R.id.editUser);
        editEmail=(EditText) findViewById(R.id.editEmail);
        editPass=(EditText) findViewById(R.id.editPass);
        btnRegistrar=(Button) findViewById(R.id.btnRegistrar);
        //btnCancelar=(Button) findViewById(R.id.btnCancelar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar("http://192.168.1.68/medicapp/registrar.php");
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }


    public void registrar(String URL){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent ac=new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Registrado correctamente!", Toast.LENGTH_LONG).show();
                startActivity(ac);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);

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
                datos.put("nombre", editName.getText().toString());
                datos.put("username", editUser.getText().toString());
                datos.put("email", editEmail.getText().toString());
                datos.put("password", editPass.getText().toString());

                return datos;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}