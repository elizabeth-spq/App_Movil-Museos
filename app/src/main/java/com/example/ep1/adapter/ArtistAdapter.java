package com.example.ep1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ep1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder>  {
    ArrayList<HashMap<String,String>> arrayList;
    public ArtistAdapter(ArrayList<HashMap<String,String>> arrayList) {
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artista,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String,String> map = arrayList.get(position);
        holder.mtvArtistaNombre.setText(map.get("nombreartista"));
        holder.mtvArtistaCategoria.setText(map.get("categoria"));
        holder.mtvArtistaNacionalidad.setText(map.get("nacionalidad"));
        holder.mtvArtistaObra.setText(map.get("ultimaobra"));
        holder.mtvArtistaVida.setText(map.get("Tiempo de vida"));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mtvArtistaNombre,mtvArtistaCategoria,mtvArtistaNacionalidad,mtvArtistaObra,mtvArtistaVida;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtvArtistaNombre = itemView.findViewById(R.id.tvNombreArtista);
            mtvArtistaCategoria = itemView.findViewById(R.id.tvCategoria);
            mtvArtistaNacionalidad=itemView.findViewById(R.id.tvNacionalilad);
            mtvArtistaObra=itemView.findViewById(R.id.tvUltinaObra);
            mtvArtistaVida =itemView.findViewById(R.id.tvVida);
        }
    }
}
