package com.example.appeasyeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

import com.example.appeasyeat.Fragment.FragmentRegistrar;
//Activity para que el usuario nuevo cree su cta....
public class Crear_cta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cta);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.crear_Cuenta, new FragmentRegistrar()).commit();
    }

    }

