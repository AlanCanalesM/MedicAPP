package com.example.pruebamoviles;

import android.content.Intent;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;


import com.an.biometric.BiometricCallback;
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
import java.util.concurrent.Executor;


public class MainActivity extends AppCompatActivity {

    Button btninicio, btnregistrar;
    EditText txtCorreo;
    EditText txtPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btninicio=(Button) findViewById(R.id.BTinicio);
        txtCorreo= (EditText) findViewById(R.id.ETcorreo);
        txtPass= (EditText) findViewById(R.id.ETpass);
        btnregistrar=(Button)findViewById(R.id.button2);

        btninicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion("http://192.168.100.11/MedicApp/login.php");
            }
        });

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent re=new Intent(getApplicationContext(), ActivityRegistrar.class);
                startActivity(re);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);*/

                Uri intentUri = Uri.parse("geo:41.382,2.170?z=16&q=41.382,2.170(Esta+Es+La+Etiqueta)");
                Intent intent = new Intent(Intent.ACTION_VIEW, intentUri);
                startActivity(intent);
            }
        });





    }

    public void acticarHuella(){
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Por favor verifica tu huella dactilar")
                .setDescription("Usar tu autenticacion biometrica es necesaria")
                .setNegativeButtonText("Cancelar")
                .build();
        getPrompt().authenticate(promptInfo);

    }

    private BiometricPrompt getPrompt(){
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser(errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                notifyUser("Autenticacion exitosa!");

                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyUser("Autenticacion fallida!");
                /*Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i2);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);*/
            }
        };

        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);
        return biometricPrompt;
    }

    private void notifyUser(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public void iniciarSesion(String URL){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.isEmpty()){
                    acticarHuella();

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