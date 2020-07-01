package com.example.appeasyeat.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeasyeat.Adapter.MenuAdapter;
import com.example.appeasyeat.Clases.Bebida;
import com.example.appeasyeat.Adapter.BebidaAdapter;
import com.example.appeasyeat.Clases.Menu;
import com.example.appeasyeat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Bebida_Fragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    //Inicializando Variables
    private String mParam1;
    private String mParam2;
    RecyclerView recycleLista;
    ArrayList<Menu> listaBebida;

    String codigoDelRestaurant;

    RequestQueue rq;
    JsonRequest jrq;

    private OnFragmentInteractionListener mListener;

    public Bebida_Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Bebida_Fragment newInstance(String param1, String param2) {
        Bebida_Fragment fragment = new Bebida_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_bebida_, container, false);

        String codigo = getArguments().getString("codigoRestaurant");
        //Relacionado variables inicializadas
        codigoDelRestaurant = codigo;
        listaBebida = new ArrayList<>();
        recycleLista = (RecyclerView) vista.findViewById(R.id.reycleBebida);
        recycleLista.setLayoutManager(new LinearLayoutManager(getContext()));

        rq = Volley.newRequestQueue(getContext());

        rellenarBebida();


        return vista;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se pudo rellenar el menu " + error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Menu menu = null;
        JSONArray json = response.optJSONArray("datos");
        try {
            for (int i = 0; i < json.length(); i++) {
                menu = new Menu();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                menu.setId(jsonObject.optString("id"));
                menu.setNombre(jsonObject.optString("nombre"));
                menu.setPrecio(jsonObject.optString("precio"));

                listaBebida.add(menu);


            }
            //Relacionado el PostreAdapter con la listaBebida de base de datos
            BebidaAdapter adapter = new BebidaAdapter(listaBebida);
            recycleLista.setAdapter(adapter);

        } catch (JSONException e) {

        }
    }

    private void rellenarBebida() {
        String url = "http://192.168.0.19/login/bebidasRestaurant.php?codigo=" + codigoDelRestaurant;

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        rq.add(jrq);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Callback method that an error has been occurred with the provided error code and optional
     * user-readable message.
     *
     * @param error
     */


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
