package com.example.ep1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button mbtnSignIn2 = findViewById(R.id.btnSignIn2);
        mbtnSignIn2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}