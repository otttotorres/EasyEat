package com.example.appeasyeat.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appeasyeat.Clases.Menu;
import com.example.appeasyeat.Fragment.Pedido_Fragment;
import com.example.appeasyeat.R;

import java.util.ArrayList;
//Este Adapter pertenece al Fragment de Pedido....
public class PedidoAdaptar extends RecyclerView.Adapter<PedidoAdaptar.PedidoHolder> implements View.OnClickListener {
    private View.OnClickListener listener;
    ArrayList<Menu> pedido = new ArrayList<>();

    public PedidoAdaptar(ArrayList<Menu> pedido) {
        this.pedido = pedido;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override  //Aqui hacemos la integracion con el Layout item_menu
    public PedidoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, null, false);
        view.setOnClickListener(this);
        return new PedidoAdaptar.PedidoHolder(view);
    }

    //Campos a visualizar en el RecyclerView
    @Override
    public void onBindViewHolder(@NonNull PedidoHolder holder, final int position) {
        holder.codigo.setText(pedido.get(position).getId());
        holder.nombre.setText(pedido.get(position).getNombre());
        holder.precio.setText(pedido.get(position).getPrecio() + " €");
        //Boton para eliminar un campo especifico del Pedido
        holder.eliminaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Eliminar", Toast.LENGTH_SHORT).show();
                //Removemos de la lista Pedidos... lo que el usuario elige...
                pedido.remove(position);
                notifyDataSetChanged();
            }
        });

        Pedido_Fragment.mostrarTotal(pedido);

    }

    public interface onItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return pedido.size();
    }

    //Inicializamos los campos de item_menu...
    public class PedidoHolder extends RecyclerView.ViewHolder {
        TextView codigo;
        TextView precio;
        TextView nombre;
        ImageView eliminaPedido;

        public PedidoHolder(@NonNull View itemView) {
            super(itemView);
            codigo = (TextView) itemView.findViewById(R.id.tv_id);
            nombre = (TextView) itemView.findViewById(R.id.tv_nombre);
            precio = (TextView) itemView.findViewById(R.id.tv_precio);
            eliminaPedido = (ImageView) itemView.findViewById(R.id.image_delete);

        }
    }
}
