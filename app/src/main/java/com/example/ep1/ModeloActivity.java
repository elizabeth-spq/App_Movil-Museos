package com.example.ep1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.ep1.databinding.ActivityModeloBinding;
import com.example.ep1.fragments.ActVisitanteFragment;
import com.example.ep1.fragments.ContextualFragment;
import com.example.ep1.fragments.DifusorFragment;
import com.example.ep1.fragments.ExhibicionFragment;

public class ModeloActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityModeloBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityModeloBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rbExhibicion.setOnClickListener(this);
        binding.rbContextual.setOnClickListener(this);
        binding.rbDifusor.setOnClickListener(this);
        binding.rbActividadV.setOnClickListener(this);

        binding.rbExhibicion.setChecked(true);
        mostrarExhibicion();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rbExhibicion:
                mostrarExhibicion();
                break;
            case R.id.rbContextual:
                mostrarContextual();
                break;
            case R.id.rbDifusor:
                mostrarDifusor();
                break;
            case R.id.rbActividadV:
                mostrarActividadV();
                break;
        }
    }

    private void mostrarActividadV() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new ActVisitanteFragment()).commit();
    }

    private void mostrarDifusor() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new DifusorFragment()).commit();
    }

    private void mostrarContextual() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new ContextualFragment()).commit();
    }

    private void mostrarExhibicion() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new ExhibicionFragment()).commit();
    }
}

