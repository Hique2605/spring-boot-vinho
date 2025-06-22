package com.example.lucasappvinho.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.model.Representante;

import java.util.List;

public class RepresentanteAdapter extends RecyclerView.Adapter<RepresentanteAdapter.ViewHolder> {

    private List<Representante> lista;

    public RepresentanteAdapter(List<Representante> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_representante, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Representante rep = lista.get(position);
        holder.txtNome.setText(rep.getNome());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNome, txtContato;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtContato = itemView.findViewById(R.id.txtContato);
        }
    }
}
