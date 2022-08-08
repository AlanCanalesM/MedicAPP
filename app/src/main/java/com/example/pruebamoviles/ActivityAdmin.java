package com.example.pruebamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ActivityAdmin extends AppCompatActivity {

    LinearLayout llfar, llmed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        llfar=(LinearLayout) findViewById(R.id.layoutfar);
        llmed=(LinearLayout) findViewById(R.id.layoutmed);


        llmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AgregarMed.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
            }
        });

        llfar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AgregarFar.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
            }
        });
    }
}