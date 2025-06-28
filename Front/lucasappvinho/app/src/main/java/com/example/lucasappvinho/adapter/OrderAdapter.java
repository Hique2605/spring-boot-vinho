package com.example.lucasappvinho.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.model.Order;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pedido, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.textOrderId.setText("Pedido #" + order.getId());

        if (order.getMoment() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            }
            holder.textOrderMoment.setText(order.getMoment().toString());  // Formate como quiser
        }

        holder.textClientName.setText("Cliente: " + order.getClient().getName());
        holder.textStatus.setText("Status: " + String.valueOf(order.getOrderStatus()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView textOrderId;
        TextView textOrderMoment;
        TextView textClientName;
        TextView textStatus;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textOrderId = itemView.findViewById(R.id.textOrderId);
            textOrderMoment = itemView.findViewById(R.id.textOrderMoment);
            textClientName = itemView.findViewById(R.id.textClientName);
            textStatus = itemView.findViewById(R.id.textStatus);
        }
    }
}

