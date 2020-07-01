package com.example.easyeatrestaurant.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.easyeatrestaurant.Crear_cta;
import com.example.easyeatrestaurant.RecuperarPassword;
import com.example.easyeatrestaurant.Clases.Restaurant;
import com.example.easyeatrestaurant.Eleccion;
import com.example.easyeatrestaurant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FragmentLoginRes extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    RequestQueue rq;
    JsonRequest jrq;
    EditText cajaUser, cajaPwd ,cajaCodigorest;
    Button btnLogin, btn_olvida_login, btn_crear_cuenta2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_login_res, container, false);
        cajaCodigorest = (EditText) vista.findViewById(R.id.ed_codigoRes);
        cajaUser = (EditText) vista.findViewById(R.id.ed_usuario);
        cajaPwd = (EditText) vista.findViewById(R.id.ed_password);
        btnLogin = (Button) vista.findViewById(R.id.btn_entrar);
        rq = Volley.newRequestQueue(getContext());
        btn_olvida_login = (Button) vista.findViewById(R.id.btn_olvido_pass2);
        btn_crear_cuenta2 = (Button) vista.findViewById(R.id.btn_crear_cta2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        btn_olvida_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RecuperarPassword.class);
                startActivity(intent);
            }
        });


        btn_crear_cuenta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Crear_cta.class);
                startActivity(intent);
            }
        });

        return vista;
    }

    @Override
    public void onResponse(JSONObject response ) {
        Restaurant restaurant = new Restaurant();
        Toast.makeText(getContext(),"Se ha encontrado al usuario "+ cajaUser.getText().toString(),Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            restaurant.setNombre(jsonObject.optString("user"));
            restaurant.sertPassword(jsonObject.optString("pwd"));
            restaurant.setCodigo(jsonObject.optString("codigo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getContext(), Eleccion.class);
        startActivity(intent);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se encontró al usuario "+ error.toString(),Toast.LENGTH_SHORT).show();
    }

    private void iniciarSesion(){

        String url ="http://192.168.0.19/login/iniciarSesionRestaurant.php?correo="+cajaUser.getText().toString()+
                "&password="+cajaPwd.getText().toString()+cajaCodigorest.getText().toString()  ;
        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);

        rq.add(jrq);
    }

}
