package com.example.easyeatrestaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyeatrestaurant.Clases.Comanda;
import com.example.easyeatrestaurant.R;

import java.util.ArrayList;

public class CocinaAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private ArrayList<Comanda> listaItem;
    private View.OnClickListener listener;

    public CocinaAdapter(Context context, ArrayList<Comanda> item) {
        this.context = context;
        this.listaItem = item;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_comanda,null);
        view.setOnClickListener(this);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        Holder holder1 = (Holder) holder;
        holder1.mesa.setText("Mesa.." + listaItem.get(position).getMesa());
        holder1.item1.setText(listaItem.get(position).getItem1());
        holder1.item2.setText(listaItem.get(position).getItem2());
        holder1.item3.setText(listaItem.get(position).getItem2());

        holder1.eliminaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Eliminar", Toast.LENGTH_SHORT).show();
                listaItem.remove(position);
                notifyDataSetChanged();
            }
        });

    }


    @Override
            public int getItemCount() {
                return listaItem.size();
            }

            public void setOnClickListener(View.OnClickListener listener) {
                this.listener = listener;
            }

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }

            }

            public static class Holder extends RecyclerView.ViewHolder {
                TextView mesa;
                TextView item1;
                TextView item2;
                TextView item3;
                ImageView eliminaPedido;

                public Holder(@NonNull View itemView) {

                    super(itemView);
                    mesa = (TextView) itemView.findViewById(R.id.tv_Mesa);
                    item1 = (TextView) itemView.findViewById(R.id.tv_item1);
                    item2 = (TextView) itemView.findViewById(R.id.tv_item2);
                    item3 = (TextView) itemView.findViewById(R.id.tv_item3);
                    eliminaPedido = (ImageView) itemView.findViewById(R.id.image_delete);

                }
            }
        }

