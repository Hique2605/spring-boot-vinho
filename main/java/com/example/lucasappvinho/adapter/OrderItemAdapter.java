package com.example.lucasappvinho.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.model.OrderItem;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    private List<OrderItem> itemList;

    public OrderItemAdapter(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        OrderItem item = itemList.get(position);

        holder.txtNomeVinho.setText(item.getVinho().getNome());
        holder.txtQuantidade.setText("Quantidade: " + item.getQuantity());
        holder.txtPreco.setText("Preço: R$ " + String.format("%.2f", item.getPrice()));
        double subtotal = item.getQuantity() * item.getPrice();
        holder.txtSubtotal.setText("Subtotal: R$ " + String.format("%.2f", subtotal));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class OrderItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtNomeVinho, txtQuantidade, txtPreco, txtSubtotal;

        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeVinho = itemView.findViewById(R.id.txtNomeVinho);
            txtQuantidade = itemView.findViewById(R.id.txtQuantidade);
            txtPreco = itemView.findViewById(R.id.txtPreco);
            txtSubtotal = itemView.findViewById(R.id.txtSubtotal);
        }
    }
}
