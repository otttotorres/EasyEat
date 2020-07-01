package com.example.easyeatrestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.easyeatrestaurant.Fragment.FragmentLoginRes;
import com.example.easyeatrestaurant.R;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fl_login, new FragmentLoginRes()).commit();

    }
}
