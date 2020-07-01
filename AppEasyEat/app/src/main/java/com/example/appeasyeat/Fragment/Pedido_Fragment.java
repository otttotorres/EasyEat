package com.example.appeasyeat.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appeasyeat.Adapter.PedidoAdaptar;
import com.example.appeasyeat.Carta;
import com.example.appeasyeat.Clases.LlenarPedido;
import com.example.appeasyeat.Clases.Menu;
import com.example.appeasyeat.R;

import java.util.ArrayList;


public class Pedido_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Inicializando variables
    private String mParam1;
    private String mParam2;

    RecyclerView recycleLista;
    ArrayList<Menu> listaPedido;
    private OnFragmentInteractionListener mListener;

    public Pedido_Fragment() {
    }


    public static Pedido_Fragment newInstance(String param1, String param2) {
        Pedido_Fragment fragment = new Pedido_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static void mostrarTotal(ArrayList<Menu> listaPedido) {

        double total = 0;

        if (listaPedido.size() == 0) {

        } else {
            for (Menu m : listaPedido) {
                total = (total + Double.parseDouble(m.getPrecio())) * 100 / 100;
            }
            Carta.totalPedido.setText(String.valueOf(total) + " €");

        }
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
        View vista = inflater.inflate(R.layout.fragment_pedido_, container, false);
        //Relacionado Variables
        listaPedido = new ArrayList<>();
        recycleLista = (RecyclerView) vista.findViewById(R.id.reyclepedido);
        recycleLista.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarlista();
        //Pasando la lista de Pedido al PedidoAdapter
        PedidoAdaptar adapter = new PedidoAdaptar(listaPedido);
        recycleLista.setAdapter(adapter);

        return vista;
    }

    private void llenarlista() {

        listaPedido = Carta.listaPedido;

    }


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


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
