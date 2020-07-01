package com.example.easyeatrestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarPassword extends AppCompatActivity {

    private EditText etEmail;
    private Button boton;

    private String email = "";
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);
        FragmentManager fm = getSupportFragmentManager();
     //   fm.beginTransaction().replace(R.id.fl_RecuperarPassword, new FragmentRecuperarPassword()).commit();
        etEmail = (EditText) findViewById(R.id.edt_email);
        boton = (Button) findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = etEmail.getText().toString();

                if (!email.isEmpty()) {
                    mDialog.setMessage("Espere un momento...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPassword();

                } else {
                    Toast.makeText(RecuperarPassword.this, "Debe ingresar el email", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }



    public void recuperarPassword(View v){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    private void resetPassword() {

        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RecuperarPassword.this, "Se ha enviado un correo para reestablecer contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RecuperarPassword.this, "No se pudo enviar el correo de reestablecer contraseña", Toast.LENGTH_SHORT).show();
                }

                mDialog.dismiss();
            }
        });
    }
    }

