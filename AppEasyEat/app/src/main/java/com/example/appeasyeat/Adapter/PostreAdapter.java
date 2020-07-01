package com.example.appeasyeat.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appeasyeat.Carta;
import com.example.appeasyeat.Clases.LlenarPedido;
import com.example.appeasyeat.Clases.Menu;
import com.example.appeasyeat.Clases.Postres;
import com.example.appeasyeat.R;

import java.util.ArrayList;

//Este Adapter pertenece al Fragment de Postre....
public class PostreAdapter extends RecyclerView.Adapter<PostreAdapter.PostresHolder> implements View.OnClickListener {

    ArrayList<Menu> listaPostres;
    private View.OnClickListener listener;
    //Constructor de la Clase PostreAdapter
    public PostreAdapter(ArrayList<Menu> listaPostres) {
        this.listaPostres = listaPostres;
    }

    @NonNull
    @Override //Aqui hacemos la integracion con el Layout item_menu
    public PostresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, null, false);
        return new PostresHolder(view);
    }
    //Campos a visualizar en el RecyclerView
    @Override
    public void onBindViewHolder(@NonNull PostresHolder holder, final int position) {
        holder.codigo.setText(listaPostres.get(position).getId());
        holder.nombre.setText(listaPostres.get(position).getNombre());
        holder.precio.setText(listaPostres.get(position).getPrecio() + " €");
        //Añadir a la lista Pedido
        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Se añadio " + listaPostres.get(position).getNombre() + " a tu lista ", Toast.LENGTH_SHORT).show();

                Menu menu = new Menu();
                menu.setId(listaPostres.get(position).getId());
                menu.setNombre(listaPostres.get(position).getNombre());
                menu.setPrecio(listaPostres.get(position).getPrecio());
                //Agrega a la lsita de Pedidos....
                Carta.listaPedido.add(menu);

            }
        });

    }

    @Override
    public int getItemCount() {

        return listaPostres.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }
    //Inicializamos los campos de item_menu...
    public class PostresHolder extends RecyclerView.ViewHolder {
        TextView codigo;
        TextView precio;
        TextView nombre;
        Button buttonAdd;

        public PostresHolder(@NonNull View itemView) {
            super(itemView);
            codigo = (TextView) itemView.findViewById(R.id.tv_id);
            nombre = (TextView) itemView.findViewById(R.id.tv_nombre);
            precio = (TextView) itemView.findViewById(R.id.tv_precio);
            buttonAdd = (Button) itemView.findViewById(R.id.btn_carrito);

        }
    }
}





