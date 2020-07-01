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
import com.example.appeasyeat.Adapter.PostreAdapter;
import com.example.appeasyeat.Clases.Menu;
import com.example.appeasyeat.Adapter.MenuAdapter;
import com.example.appeasyeat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Menu_Fragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Inicializacion de variables
    RecyclerView recycleLista;
    ArrayList<Menu> listaMenu;
    String codigoDelRestaurant;


    RequestQueue rq;
    JsonRequest jrq;

    private OnFragmentInteractionListener mListener;

    public Menu_Fragment() {

    }


    public static Menu_Fragment newInstance(String param1, String param2) {
        Menu_Fragment fragment = new Menu_Fragment();
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
        final View vista = inflater.inflate(R.layout.fragment_menu_, container, false);

        String codigo = getArguments().getString("codigoRestaurant");
        codigoDelRestaurant = codigo;
        //Relacionado variables inicializadas
        listaMenu = new ArrayList<>();
        recycleLista = (RecyclerView) vista.findViewById(R.id.reyclemenu);
        recycleLista.setLayoutManager(new LinearLayoutManager(getContext()));

        rq = Volley.newRequestQueue(getContext());

        rellenarMenu();


        return vista;


        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_menu_, container, false);
    }

    private void rellenarMenu() {
        String url = "http://192.168.0.19/login/comidaRestaurant.php?codigo=" + codigoDelRestaurant;

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        rq.add(jrq);
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

                listaMenu.add(menu);


            }

            MenuAdapter adapter = new MenuAdapter(listaMenu);
            recycleLista.setAdapter(adapter);

        } catch (JSONException e) {

        }
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
