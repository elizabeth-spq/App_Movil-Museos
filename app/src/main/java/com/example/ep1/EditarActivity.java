package com.example.ep1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ep1.databinding.ActivityAdministrarBinding;
import com.example.ep1.databinding.ActivityEditarBinding;

import java.util.HashMap;
import java.util.Map;

public class EditarActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityEditarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        String idartista =bundle.getString("idartista");
        String nombre =bundle.getString("nombre");
        String apellido =bundle.getString("apellido");
        String categoria =bundle.getString("categoria");
        String pais =bundle.getString("pais");
        String descripcion =bundle.getString("descripcion");


        binding.edtNombreEdit.setText(nombre);
        binding.edtApeEdit.setText(apellido);
        binding.edtCategoriaEdit.setText(categoria);
        binding.edtPaisEdit.setText(pais);
        binding.etDescripcion.setText(descripcion);

        binding.btnEditarArtista.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditarArtista();
    }

    private void EditarArtista() {

        Bundle bundle = getIntent().getExtras();
        final String idartista=bundle.getString("idartista");
        final String nombre = binding.edtNombreEdit.getText().toString().trim();
        final String apellido = binding.edtApeEdit.getText().toString().trim();
        final String categoria = binding.edtCategoriaEdit.getText().toString().trim();
        final String pais = binding.edtPaisEdit.getText().toString().trim();
        final String descripcion = binding.etDescripcion.getText().toString().trim();

        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Cargando");
        if (nombre.isEmpty()){
            Toast.makeText(this,"Ingrese Nombre", Toast.LENGTH_LONG).show();
            return;
        }
        else if (apellido.isEmpty()){
            Toast.makeText(this,"Ingrese Apeliido", Toast.LENGTH_LONG).show();
            return;
        }else if (categoria.isEmpty()){
            Toast.makeText(this,"Ingrese Categoría", Toast.LENGTH_LONG).show();
            return;
        }else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://159.203.182.131/servicios/apps/artista_actualizar.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Editados")) {
                        Toast.makeText(EditarActivity.this, "Datos Editados", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        startActivity(new Intent(getApplicationContext(), AdministrarActivity.class));
                        finish();
                    } else {
                        Toast.makeText(EditarActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(EditarActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }){
                @NonNull
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params =new HashMap<>();
                    params.put("idartista",idartista);
                    params.put("nombre",nombre);
                    params.put("apellido",apellido);
                    params.put("categoria",categoria);
                    params.put("pais",pais);
                    params.put("descripcion",descripcion);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(EditarActivity.this);
            requestQueue.add(request);
        }


    }
}