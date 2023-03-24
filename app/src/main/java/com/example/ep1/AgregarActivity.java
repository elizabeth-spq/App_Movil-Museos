package com.example.ep1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.example.ep1.databinding.ActivityAgregarBinding;
import com.example.ep1.utils.Total;
import com.google.android.material.textfield.TextInputEditText;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AgregarActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityAgregarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAgregarArtista.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            AgregarDatos();
    }


    private void AgregarDatos() {
        final String nombre = binding.edtNombre.getText().toString().trim();
        final String apellido = binding.edtApellido.getText().toString().trim();
        final String categoria = binding.edtCategoria.getText().toString().trim();
        final String pais = binding.edtPais.getText().toString().trim();
        final String descripcion = binding.edtDescripcion.getText().toString().trim();

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
            StringRequest request = new StringRequest(Request.Method.POST, "http://159.203.182.131/servicios/apps/artista-insertar.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(AgregarActivity.this, "Datos Insertados", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        startActivity(new Intent(getApplicationContext(), AdministrarActivity.class));
                        finish();
                    } else {
                        Toast.makeText(AgregarActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AgregarActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }){
                @NonNull
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params =new HashMap<>();
                    params.put("nombre",nombre);
                    params.put("apellido",apellido);
                    params.put("categoria",categoria);
                    params.put("pais",pais);
                    params.put("descripcion",descripcion);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(AgregarActivity.this);
            requestQueue.add(request);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
