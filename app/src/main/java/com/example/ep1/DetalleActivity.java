package com.example.ep1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ep1.databinding.ActivityAdministrarBinding;
import com.example.ep1.databinding.ActivityDetalleBinding;

public class DetalleActivity extends AppCompatActivity {
    private ActivityDetalleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String idartista =bundle.getString("idartista");
        String nombre =bundle.getString("nombre");
        String apellido =bundle.getString("apellido");
        String categoria =bundle.getString("categoria");
        String pais =bundle.getString("pais");
        String descripcion =bundle.getString("descripcion");


        binding.tvNombre.setText(nombre+" "+apellido);
        binding.tvPaisCat.setText(pais+" / "+ categoria);
        binding.tvDescripcion.setText(descripcion);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}