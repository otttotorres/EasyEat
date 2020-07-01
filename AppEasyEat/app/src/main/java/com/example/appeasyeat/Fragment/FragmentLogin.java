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
import com.example.appeasyeat.Crear_cta;
import com.example.appeasyeat.IngCodigo;
import com.example.appeasyeat.R;
import com.example.appeasyeat.RecuperarPassword;
import com.example.appeasyeat.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentLogin extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    RequestQueue rq;
    JsonRequest jrq;
    EditText cajaUser, cajaPwd;
    Button btnLogin, btn_olvida_login, btn_crear_cuenta2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_fragment_login, container, false);

        cajaUser = (EditText) vista.findViewById(R.id.ed_usuario3);
        cajaPwd = (EditText) vista.findViewById(R.id.ed_password3);
        btnLogin = (Button) vista.findViewById(R.id.btn_entrar3);
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
    public void onResponse(JSONObject response) {
        Usuario usuario = new Usuario();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setUser(jsonObject.optString("correo"));
            usuario.setPwd(jsonObject.optString("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getContext(), IngCodigo.class);
        startActivity(intent);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se encontró al usuario "+ error.toString(),Toast.LENGTH_SHORT).show();
    }

    private void iniciarSesion(){

        String url ="http://192.168.1.44/login/iniciarSesion.php?correo="+cajaUser.getText().toString()+
                "&password="+cajaPwd.getText().toString();

        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);

        rq.add(jrq);
    }
}
