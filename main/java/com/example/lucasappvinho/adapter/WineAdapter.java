package com.example.lucasappvinho.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.model.Vinho;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WineAdapter extends RecyclerView.Adapter<WineAdapter.WineViewHolder> {
    private List<Vinho> wineList;

    public WineAdapter(List<Vinho> wineList) {
        this.wineList = wineList;
    }

    public static class WineViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, typeTextView, priceTextView;
        ImageView imageWine;

        public WineViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textWineName);
            typeTextView = itemView.findViewById(R.id.textWineType);
            priceTextView = itemView.findViewById(R.id.textWinePrice);
            imageWine = itemView.findViewById(R.id.imageWine);
        }
    }

    @Override
    public WineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wine, parent, false);
        return new WineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WineViewHolder holder, int position) {
        Vinho wine = wineList.get(position);
        holder.nameTextView.setText(wine.getNome());
        holder.typeTextView.setText(wine.getTipo());
        holder.priceTextView.setText("R$ " + wine.getPrecoUnitario());

        // Carrega a imagem usando Picasso
        Picasso.get()
                .load(wine.getImgUrl())
                .placeholder(R.drawable.placeholder)  // imagem enquanto carrega
                .error(R.drawable.error)              // imagem de erro se falhar
                .into(holder.imageWine);
    }
    @Override
    public int getItemCount() {
        return wineList.size();
    }


}
