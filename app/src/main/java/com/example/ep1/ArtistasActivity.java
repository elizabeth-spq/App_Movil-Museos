package com.example.ep1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ep1.adapter.ArtistAdapter;
import com.example.ep1.databinding.ActivityArtistasBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class ArtistasActivity extends AppCompatActivity {
    private ActivityArtistasBinding binding;
    ArrayList arrayList = new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_artistas);
        binding=ActivityArtistasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        leerServicio();
    }

    private void leerServicio() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://wikimuseo.000webhostapp.com/artistas.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DATOS", response);
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
                String nombreartista = jsonArray.getJSONObject(i).getString("nombreartista");
                String Tiempodevida = jsonArray.getJSONObject(i).getString("Tiempo de vida");
                String categoria = jsonArray.getJSONObject(i).getString("categoria");
                String ultimaobra = jsonArray.getJSONObject(i).getString("ultimaobra");
                String nacionalidad = jsonArray.getJSONObject(i).getString("nacionalidad");

                HashMap<String,String> map = new HashMap<>();
                map.put("idartista",idartista);
                map.put("nombreartista",nombreartista);
                map.put("Tiempo de vida",Tiempodevida);
                map.put("categoria",categoria);
                map.put("ultimaobra",ultimaobra);
                map.put("nacionalidad",nacionalidad);


                arrayList.add(map);//Asi se añade el HashMap al arraylist
            }
            ArtistAdapter artistAdapter = new ArtistAdapter(arrayList);
            binding.rvArtistas.setAdapter(artistAdapter);
            binding.rvArtistas.setLayoutManager(new LinearLayoutManager(this));


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
}