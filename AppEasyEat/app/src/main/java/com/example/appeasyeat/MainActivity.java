package com.example.appeasyeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.appeasyeat.Fragment.FragmentLogin;
//Activity Inicio de la aplicacion
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.login, new FragmentLogin()).commit();
    }

   public void entar(View v){
       Intent intent = new Intent(this, IngCodigo.class);
       startActivity(intent);
   }

}
