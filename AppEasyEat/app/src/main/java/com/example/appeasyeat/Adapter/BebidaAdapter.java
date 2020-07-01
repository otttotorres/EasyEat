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
import com.example.appeasyeat.Clases.Bebida;
import com.example.appeasyeat.Clases.LlenarPedido;
import com.example.appeasyeat.Clases.Menu;
import com.example.appeasyeat.R;

import java.util.ArrayList;
//Este Adapter pertenece al Fragment de Bebida....
public class BebidaAdapter extends RecyclerView.Adapter<BebidaAdapter.BebidaHolder> {

    ArrayList<Menu>listaBebida;
    //Constructor de la clase BebidaAdapter
    public BebidaAdapter(ArrayList<Menu> listaBebida) {this.listaBebida = listaBebida;
    }

    @NonNull
    @Override //Aqui hacemos la integracion con el Layout item_menu
    public BebidaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,null,false);

        return new BebidaHolder(view);
    }
    //Campos a visualizar en el RecyclerView
    @Override
    public void onBindViewHolder(@NonNull BebidaHolder holder, final int position) {
        holder.codigo.setText(listaBebida.get(position).getId());
        holder.nombre.setText(listaBebida.get(position).getNombre());
        holder.precio.setText(listaBebida.get(position).getPrecio()+" €");
        //Añadir a la lista Pedido
        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"Se añadio "+listaBebida.get(position).getNombre()+" a tu lista ",Toast.LENGTH_SHORT).show();

                Menu menu = new Menu();
                menu.setId(listaBebida.get(position).getId());
                menu.setNombre(listaBebida.get(position).getNombre());
                menu.setPrecio(listaBebida.get(position).getPrecio());
                //Agrega a la lsita de Pedidos....
                Carta.listaPedido.add(menu);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listaBebida.size();
    }

    //Inicializamos los campos de item_menu...
    public class BebidaHolder extends RecyclerView.ViewHolder {
        TextView codigo;
        TextView precio;
        TextView nombre;
        Button buttonAdd;

        public BebidaHolder(@NonNull View itemView) {
            super(itemView);
            codigo=(TextView) itemView.findViewById(R.id.tv_id);
            nombre=(TextView) itemView.findViewById(R.id.tv_nombre);
            precio=(TextView) itemView.findViewById(R.id.tv_precio);
            buttonAdd= (Button) itemView.findViewById(R.id.btn_carrito);

        }
    }
}
