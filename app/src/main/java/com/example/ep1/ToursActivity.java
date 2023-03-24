package com.example.ep1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ep1.databinding.ActivityArtistasBinding;
import com.example.ep1.databinding.ActivityToursBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class ToursActivity extends AppCompatActivity {
    private ActivityToursBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityToursBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        leerServicio();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void leerServicio() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://wikimuseo.000webhostapp.com/tours.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DATOS",response);
                        llenarLista(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DATOS",error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    private void llenarLista(String response) {
        ArrayList arrayList = new ArrayList<HashMap<String,String>>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i=0; i<jsonArray.length(); i++){
                String nombretour = jsonArray.getJSONObject(i).getString("nombretour");
                String guia = jsonArray.getJSONObject(i).getString("guia");
                String precio = jsonArray.getJSONObject(i).getString("precio");
                String tiemporecorrido = jsonArray.getJSONObject(i).getString("tiemporecorrido");
                String horario = jsonArray.getJSONObject(i).getString("horario");
                HashMap<String,String> map = new HashMap<>();
                map.put("nombretour",nombretour);
                map.put("guia",guia);
                map.put("precio",precio);
                map.put("tiemporecorrido",tiemporecorrido);
                map.put("horario",horario);

                arrayList.add(map);
            }

            String[] origen = {"nombretour","guia","precio","tiemporecorrido","horario"};
            int[] destino = {R.id.tvNombreTour,R.id.tvGuia,R.id.tvPrecio,R.id.tvRecorrido,R.id.tvHorario};

            ListAdapter listAdapter = new SimpleAdapter(
                    this,
                    arrayList,
                    R.layout.item_tour,
                    origen,
                    destino
            );
            binding.lvTours.setAdapter(listAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}