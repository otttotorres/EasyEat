package com.example.easyeatrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Eleccion extends AppCompatActivity {

    private Button btn_admin, btn_cocina, btn_sala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eleccion);

        btn_cocina = (Button)findViewById(R.id.b_cocina) ;
        btn_admin  = (Button)findViewById(R.id.b_admin) ;
        btn_sala = (Button)findViewById(R.id.b_sala) ;

        btn_cocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            irCocina();
            }
        });

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAdmin();
            }
        });

        btn_sala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              irSala();
            }
        });

    }

    public void irSala(){

        Intent intent = new Intent(this, Sala.class);
        startActivity(intent);

    }

    public void irAdmin(){

        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);

    }

    public void irCocina(){

        Intent intent = new Intent(this, Cocina.class);
        startActivity(intent);

    }

}
