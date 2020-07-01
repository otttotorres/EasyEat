package com.example.appeasyeat.Fragment;

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
import com.example.appeasyeat.Carta;
import com.example.appeasyeat.R;
import com.example.appeasyeat.Restaurant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FragmentCodigo extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue rq;
    JsonRequest jrq;
    EditText cajaRestaurant;
    Button btnAcceso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_fragment_codigo, container, false);
        cajaRestaurant = (EditText) vista.findViewById(R.id.editText2);
        btnAcceso = (Button) vista.findViewById(R.id.btn_restaurant2);
        rq = Volley.newRequestQueue(getContext());
        btnAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accederRestaurant();
            }
        });

        return vista;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se encontró el restaurante " + error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Restaurant restaurant = new Restaurant();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            restaurant.setNombre(jsonObject.optString("nombre"));
            restaurant.setCodigo(jsonObject.optString("codigo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getContext(), Carta.class);
        intent.putExtra("codigoRestaurant", restaurant.getCodigo());
        startActivity(intent);
    }

    private void accederRestaurant() {

        String url = "http://192.168.1.44/login/accesoRestaurant.php?codigo=" + cajaRestaurant.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
    }
}
