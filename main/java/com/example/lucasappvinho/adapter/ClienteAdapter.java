package com.example.lucasappvinho.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.model.User;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private List<User> listaClientes;

    public ClienteAdapter(List<User> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        User cliente = listaClientes.get(position);
        holder.textNome.setText(cliente.getName());
        holder.textEmail.setText(cliente.getEmail());
        holder.textTelefone.setText(cliente.getPhone());
        holder.textEndereco.setText(cliente.getEndereco());
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView textNome, textEmail, textTelefone, textEndereco;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textNome);
            textEmail = itemView.findViewById(R.id.textEmail);
            textTelefone = itemView.findViewById(R.id.textTelefone);
            textEndereco = itemView.findViewById(R.id.textEndereco);
        }
    }
}
