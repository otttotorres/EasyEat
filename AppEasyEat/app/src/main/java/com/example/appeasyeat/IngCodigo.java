package com.example.appeasyeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.appeasyeat.Fragment.FragmentCodigo;
//Activity de Ingreso de Codigo de Restaurant
public class IngCodigo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ing_codigo);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.loginCodigo, new FragmentCodigo()).commit();
    }

    public void vamos(View v){
        Intent intent = new Intent(this, IngCodigo.class);
        startActivity(intent);
    }

}
