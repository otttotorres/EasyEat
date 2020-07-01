package com.example.appeasyeat.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeasyeat.MainActivity;
import com.example.appeasyeat.R;
import org.json.JSONObject;


public class FragmentRegistrar extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue rq;
    JsonRequest jrq;
    EditText cajaCorreo, cajaContra;
    Button btnRegistrarU, btnVolver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_registrar, container, false);
        cajaCorreo = (EditText) vista.findViewById(R.id.ed_usuario2);
        cajaContra = (EditText) vista.findViewById(R.id.ed_password2);
        btnRegistrarU = (Button) vista.findViewById(R.id.btn_entrar2);
        btnVolver = (Button) vista.findViewById(R.id.btn_inicio_secion);
        rq = Volley.newRequestQueue(getContext());

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnRegistrarU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        return vista;

    }


    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Se registró al usuario "+ cajaCorreo.getText().toString(),Toast.LENGTH_SHORT).show();

        limpiarCajas();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se pudo registrar al usuario "+ error.toString(),Toast.LENGTH_SHORT).show();
    }

    void limpiarCajas(){
        cajaCorreo.setText("");
        cajaContra.setText("");
    }

    private void registrarUsuario(){

        String url ="http://192.168.1.44/login/registrarUsuario.php?correo="+cajaCorreo.getText().toString()+
                "&password="+cajaContra.getText().toString();

        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);

        rq.add(jrq);
    }


}