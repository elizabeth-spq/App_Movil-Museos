package com.example.ep1.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ep1.AgregarActivity;
import com.example.ep1.EditarActivity;
import com.example.ep1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaArtistaAdapter extends RecyclerView.Adapter<ListaArtistaAdapter.ViewHolder>  {
    ArrayList<HashMap<String,String>> arrayList;
//métodos Clik
    public static OnButtonClickListener onButtonClickListener;
    public static OnEliminarClickListener onEliminarClickListener;
    public static OnVerClickListener onVerClickListener;

    public ListaArtistaAdapter(ArrayList<HashMap<String,String>> arrayList) {
        this.arrayList = arrayList;

    }

     @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_listarartista,parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String,String> map = arrayList.get(position);
        holder.mtvNombreArtista.setText(map.get("nombre") + " " + map.get("apellido"));
        holder.mtvPaisArtista.setText(map.get("pais"));
        holder.mtvCategoriaArtista.setText(map.get("categoria"));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mtvNombreArtista, mtvPaisArtista, mtvCategoriaArtista;
        Button mbtBotonEditar,mbtBotonVerDetalle;
        CardView mcdEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtvNombreArtista = itemView.findViewById(R.id.tvNombrecompleto);
            mtvPaisArtista = itemView.findViewById(R.id.tvPais);
            mtvCategoriaArtista = itemView.findViewById(R.id.tvCate);
            mbtBotonEditar = itemView.findViewById(R.id.btnEditar);
            mbtBotonVerDetalle = itemView.findViewById(R.id.btnVerDetalle);
            mcdEliminar = itemView.findViewById(R.id.cdEliminar);
            mbtBotonEditar.setOnClickListener(this);
            mcdEliminar.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.d("POSICION",String.valueOf(getLayoutPosition()));
                    onEliminarClickListener.onEliminarClick(getLayoutPosition());
                }
            });
            mbtBotonVerDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("POSICION",String.valueOf(getLayoutPosition()));
                    onVerClickListener.onVerClick(getLayoutPosition());
                }
            });

        }
//Metodo editar
        @Override
        public void onClick(View view) {
            //Log.d("POSICION",String.valueOf(getLayoutPosition()));
            onButtonClickListener.onButtonClick(getLayoutPosition());
        }
    }
    //Método editar
    public interface OnButtonClickListener{
        void onButtonClick(int position);
    }
    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener){
        ListaArtistaAdapter.onButtonClickListener = onButtonClickListener;
    }
    //Método eliminar
    public interface OnEliminarClickListener{
        void onEliminarClick(int position);
    }
    public void setOnEliminarClickListener(OnEliminarClickListener onEliminarClickListener){
        ListaArtistaAdapter.onEliminarClickListener = onEliminarClickListener;
    }
    //Método ver
    public interface OnVerClickListener{
        void onVerClick(int position);
    }
    public void setOnVerClickListener(OnVerClickListener onVerClickListener){
        ListaArtistaAdapter.onVerClickListener = onVerClickListener;
    }
}
