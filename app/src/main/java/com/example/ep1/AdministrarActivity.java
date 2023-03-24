package com.example.ep1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ep1.adapter.ArtistAdapter;
import com.example.ep1.adapter.ListaArtistaAdapter;
import com.example.ep1.databinding.ActivityAdministrarBinding;
import com.example.ep1.utils.Total;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdministrarActivity extends AppCompatActivity implements View.OnClickListener, ListaArtistaAdapter.OnButtonClickListener, ListaArtistaAdapter.OnEliminarClickListener, ListaArtistaAdapter.OnVerClickListener {
    private ActivityAdministrarBinding binding;

    ArrayList arrayList = new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdministrarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.btnAgregar.setOnClickListener(this);

        leerServicio();
    }

    private void leerServicio() {
        //Log.d("DATOS", "leerServicio");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Total.rutaServicio + "artistas.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Log.d("DATOS", response);
                        llenarLista(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DATOS", error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    private void llenarLista(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i= 0; i< jsonArray.length(); i++){
                //getJSONObject permite obtener la fila de un JSONArray
                String idartista = jsonArray.getJSONObject(i).getString("idartista");
                String nombre = jsonArray.getJSONObject(i).getString("nombre");
                String apellido = jsonArray.getJSONObject(i).getString("apellido");
                String categoria = jsonArray.getJSONObject(i).getString("categoria");
                String pais = jsonArray.getJSONObject(i).getString("pais");
                String descripcion = jsonArray.getJSONObject(i).getString("descripcion");

                HashMap<String,String> map = new HashMap<>();
                map.put("idartista",idartista);
                map.put("nombre",nombre);
                map.put("apellido",apellido);
                map.put("categoria",categoria);
                map.put("pais",pais);
                map.put("descripcion",descripcion);

                arrayList.add(map);//Asi se añade el HashMap al arraylist
            }
            ListaArtistaAdapter listaArtistaAdapter = new ListaArtistaAdapter(arrayList);
            binding.rvListar.setAdapter(listaArtistaAdapter);
            binding.rvListar.setLayoutManager(new LinearLayoutManager(this));
            listaArtistaAdapter.setOnButtonClickListener(this);
            listaArtistaAdapter.setOnEliminarClickListener(this);
            listaArtistaAdapter.setOnVerClickListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAgregar:
                mostrarAgregar();
                break;
        }
    }

    private void mostrarAgregar() {

        startActivity(new Intent(this, AgregarActivity.class));
        finish();
    }

    @Override
    public void onButtonClick(int position) {

        HashMap<String,String> map = (HashMap<String, String>) arrayList.get(position);
        String idartista = map.get("idartista");
        String nombre = map.get("nombre");
        String apellido = map.get("apellido");
        String categoria = map.get("categoria");
        String pais = map.get("pais");
        String descripcion = map.get("descripcion");
        //Toast.makeText(this,nombre,Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();

        bundle.putString("idartista",idartista);
        bundle.putString("nombre",nombre);
        bundle.putString("apellido",apellido);
        bundle.putString("categoria",categoria);
        bundle.putString("pais",pais);
        bundle.putString("descripcion",descripcion);

        Intent intent = new Intent(this,EditarActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


    @Override
    public void onEliminarClick(int position) {

        HashMap<String,String> map = (HashMap<String, String>) arrayList.get(position);
        String idartista = map.get("idartista");
        mostrarEliminar(idartista);
    }
    private void mostrarEliminar(final String idartista) {
        StringRequest request = new StringRequest(Request.Method.POST, "http://159.203.182.131/servicios/apps/artista-eliminar.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Log.d("DATOS", response.toString());
                if (response.equalsIgnoreCase("Eliminado")) {
                    Toast.makeText(AdministrarActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), AdministrarActivity.class));
                    finish();
                } else {
                    Toast.makeText(AdministrarActivity.this, "Error al Eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdministrarActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idartista", idartista);
                return params;

            }
        };
        RequestQueue requestQueue =Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public void onVerClick(int position) {
        HashMap<String,String> map = (HashMap<String, String>) arrayList.get(position);
        String idartista = map.get("idartista");
        String nombre = map.get("nombre");
        String apellido = map.get("apellido");
        String categoria = map.get("categoria");
        String pais = map.get("pais");
        String descripcion = map.get("descripcion");
       // Toast.makeText(this,nombre,Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();

        bundle.putString("idartista",idartista);
        bundle.putString("nombre",nombre);
        bundle.putString("apellido",apellido);
        bundle.putString("categoria",categoria);
        bundle.putString("pais",pais);
        bundle.putString("descripcion",descripcion);

        Intent intent = new Intent(this,DetalleActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}