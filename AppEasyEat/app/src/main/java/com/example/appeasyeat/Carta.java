package com.example.appeasyeat;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeasyeat.Clases.Menu;
import com.example.appeasyeat.Fragment.Bebida_Fragment;
import com.example.appeasyeat.Fragment.Menu_Fragment;
import com.example.appeasyeat.Fragment.Pedido_Fragment;
import com.example.appeasyeat.Fragment.Postre_Fragment;
import org.json.JSONObject;
import java.util.ArrayList;
//Implementando todos los fragment que se van usar en esta Activity...
public class Carta extends AppCompatActivity implements Menu_Fragment.OnFragmentInteractionListener,
        Bebida_Fragment.OnFragmentInteractionListener, Postre_Fragment.OnFragmentInteractionListener,
        Pedido_Fragment.OnFragmentInteractionListener, Response.Listener<JSONObject>, Response.ErrorListener {
    //Inicialiando Variables
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button pedido;
    private Button ir_comer;
    public static TextView totalPedido; //Variables en el Fragment Pedido
    private static TextView numeroMesa; //Variables en el Fragment Pedido
    Menu_Fragment menufr;
    Bebida_Fragment bebidafr;
    Postre_Fragment postrefr;
    Pedido_Fragment pedidofr;
    RequestQueue rq;
    JsonRequest jrq;

    public static ArrayList<Menu> listaPedido = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        String codigoRest = getIntent().getStringExtra("codigoRestaurant");
        Bundle args = new Bundle();
        args.putString("codigoRestaurant", codigoRest);
        //Relacionando los fragment
        menufr = new Menu_Fragment();
        menufr.setArguments(args);
        bebidafr = new Bebida_Fragment();
        bebidafr.setArguments(args);
        postrefr = new Postre_Fragment();
        postrefr.setArguments(args);
        pedidofr = new Pedido_Fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, menufr).commit();

        rq = Volley.newRequestQueue(this);
        //Relacionando variables inicializadas
        bt1 = (Button) findViewById(R.id.btn1);
        bt2 = (Button) findViewById(R.id.btn2);
        bt3 = (Button) findViewById(R.id.btn3);
        numeroMesa =(TextView) findViewById(R.id.etn_mesa);
        ir_comer = (Button) findViewById(R.id.btn_comer);
        pedido = (Button) findViewById(R.id.btn_pedido);
        totalPedido = (TextView)findViewById(R.id.tv_total) ;

        //Haciendo visible los campos de del fragment Pedido
        pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ir_comer.setVisibility(v.getVisibility());
                totalPedido.setVisibility(v.getVisibility());
                numeroMesa.setVisibility(v.getVisibility());
                pedido.setVisibility(v.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, pedidofr).commit();

            }
        });

        ir_comer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Se ha realizado su pedido con exito", Toast.LENGTH_SHORT).show();
                aComer();
            }
        });


    }

    private void aComer() {

        for (Menu comanda: listaPedido){

            Toast.makeText(this,"Marico "+comanda.getNombre(),Toast.LENGTH_SHORT).show();

            String url = "http://192.168.0.19/login/hacerPedido.php?id="+comanda.getId()+"&nombre="+comanda.getNombre()+"&mesa="+numeroMesa +"&precio="+comanda.getPrecio();

            jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

            rq.add(jrq);

        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this,"No se registró el pedido ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(this,"Se pudo registrar el pedido ",Toast.LENGTH_SHORT).show();
    }

    public void onclick(View v) {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.btn1:
                trans.replace(R.id.contenedor, menufr);
                ir_comer.setVisibility(v.GONE);
                totalPedido.setVisibility(v.GONE);
                pedido.setVisibility(v.getVisibility());
                numeroMesa.setVisibility(v.GONE);
                break;
            case R.id.btn2:
                trans.replace(R.id.contenedor, bebidafr);
                ir_comer.setVisibility(v.GONE);
                totalPedido.setVisibility(v.GONE);
                pedido.setVisibility(v.getVisibility());
                numeroMesa.setVisibility(v.GONE);
                break;
            case R.id.btn3:
                trans.replace(R.id.contenedor, postrefr);
                ir_comer.setVisibility(v.GONE);
                totalPedido.setVisibility(v.GONE);
                pedido.setVisibility(v.getVisibility());
                numeroMesa.setVisibility(v.GONE);
                break;

        }
        trans.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
