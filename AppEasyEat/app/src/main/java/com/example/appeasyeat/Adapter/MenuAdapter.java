package com.example.appeasyeat.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appeasyeat.Carta;
import com.example.appeasyeat.Clases.LlenarPedido;
import com.example.appeasyeat.Clases.Menu;
import com.example.appeasyeat.R;

import java.util.ArrayList;

//Este Adapter pertenece al Fragment de Menu....
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> implements View.OnClickListener {

    ArrayList<Menu> listaMenu;
    ArrayList<Menu> pedido = new ArrayList<>();
    private View.OnClickListener listener;

    //Constructor MenuAdapter
    public MenuAdapter(ArrayList<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    @Override
    //Aqui hacemos la integracion con el Layout item_menu... Para poder isualizar en RecyclerView
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, null, false);
        view.setOnClickListener(this);
        return new MenuHolder(view);
    }
    //Campos a visualizar en el RecyclerView
    @Override
    public void onBindViewHolder(MenuHolder holder, final int position) {
        holder.codigo.setText(listaMenu.get(position).getId());
        holder.nombre.setText(listaMenu.get(position).getNombre());
        holder.precio.setText(listaMenu.get(position).getPrecio() + " €");
        //Añadir a la lista Pedido
        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Se añadio " + listaMenu.get(position).getNombre() + " a tu lista ", Toast.LENGTH_SHORT).show();

                Menu menu = new Menu();
                menu.setId(listaMenu.get(position).getId());
                menu.setNombre(listaMenu.get(position).getNombre());
                menu.setPrecio(listaMenu.get(position).getPrecio());
                //Agrega a la lsita de Pedidos....
                Carta.listaPedido.add(menu);
            }
        });
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return listaMenu.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }

    }
    //Inicializamos los campos de item_menu...
    public class MenuHolder extends RecyclerView.ViewHolder {
        TextView codigo;
        TextView precio;
        TextView nombre;
        Button buttonAdd;
        Button buttonPedido;

        public MenuHolder(View itemView) {

            super(itemView);

            codigo = (TextView) itemView.findViewById(R.id.tv_id);
            nombre = (TextView) itemView.findViewById(R.id.tv_nombre);
            precio = (TextView) itemView.findViewById(R.id.tv_precio);
            buttonAdd = (Button) itemView.findViewById(R.id.btn_carrito);
            buttonPedido = (Button) itemView.findViewById(R.id.btn_pedido);

        }
    }
}
