package com.example.ep1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageButton imgbtnhome = findViewById(R.id.img_btn_home);
        ImageButton imgbtnubicacion = findViewById(R.id.img_btn_ubicacion);
        ImageButton imgbtncuriosidades = findViewById(R.id.img_btn_curiosidad);
        ImageView imgNoticias = findViewById(R.id.imgNot1);
        ImageView imgbtnvideo = findViewById(R.id.img_btn_youtube);
        Button mbtnMostrar = findViewById(R.id.btnModelo);
        imgbtnhome.setOnClickListener(this);
        imgbtnubicacion.setOnClickListener(this);
        imgbtncuriosidades.setOnClickListener(this);
        imgNoticias.setOnClickListener(this);
        imgbtnvideo.setOnClickListener(this);
        mbtnMostrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnModelo:
                startActivity(new Intent(this, ModeloActivity.class));
                break;
            case R.id.img_btn_home:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.img_btn_youtube:
                startActivity(new Intent(this, VideoActivity.class));
                break;
            case R.id.imgNot1:
                startActivity(new Intent(this, NoticiasActivity.class));
                break;
            /*case R.id.cdvInicio:
                 startActivity(new Intent(this, MainActivity.class));
                break;*/
            case R.id.img_btn_curiosidad:
                startActivity(new Intent(this, CuriosidadesActivity.class));
                break;
            case R.id.img_btn_ubicacion:
                startActivity(new Intent(this, UbicacionActivity.class));
                break;
            case R.id.btnLouvre:
                startActivity(new Intent(this, LuvreActivity.class));
                break;
            case R.id.imgvMac:
                startActivity(new Intent(this, MacActivity.class));
                break;
            case R.id.cardAntiguedad:
                startActivity(new Intent(this, HistoriaeActivity.class));
                break;
            case R.id.imageTipo:
                startActivity(new Intent(this,TipoActivity.class));
                break;
            case R.id.btnPoliticas2:
                startActivity(new Intent(this,PoliticaActivity.class));
                break;
            case R.id.btnPreguntas2:
                startActivity(new Intent(this,PreguntasActivity.class));
                break;
            case R.id.btnHorario2:
                startActivity(new Intent(this,HorarioActivity.class));
                break;
            case R.id.btnProtocolo2:
                startActivity(new Intent(this,ProtocoloActivity.class));
                break;
            case R.id.btnListaArtistas:
                startActivity(new Intent(this,ArtistasActivity.class));
                break;
            case R.id.btnInformacionTours:
                startActivity(new Intent(this,ToursActivity.class));
                break;
            case R.id.btnAdministrar:
                startActivity(new Intent(this,AdministrarActivity.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_politica:
                mostrarPolitica();
                return true;
            case R.id.nav_tours:
                mostrarTours();
                return true;
            case R.id.nav_artistas:
                mostrarArtistas();
                return true;
            case R.id.nav_preguntas:
                mostrarpreguntas();
                return true;
            case R.id.nav_horario:
                mostrarhorario();
                return true;
            case R.id.nav_protocolo:
                mostrarprotocolo();
                return true;
            case R.id.nav_cerrar_sesion:
                mostrarcerrarsesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void mostrarcerrarsesion() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    private void mostrarTours() {startActivity(new Intent(this, ToursActivity.class));}

    private void mostrarArtistas() {
        startActivity(new Intent(this, ArtistasActivity.class));
    }

    private void mostrarpreguntas() {
        startActivity(new Intent(this, PreguntasActivity.class));
    }

    private void mostrarhorario() {
        startActivity(new Intent(this, HorarioActivity.class));
    }

    private void mostrarprotocolo() {
        startActivity(new Intent(this, ProtocoloActivity.class));
    }

    private void mostrarPolitica() {
        startActivity(new Intent(this, PoliticaActivity.class));
    }

}
