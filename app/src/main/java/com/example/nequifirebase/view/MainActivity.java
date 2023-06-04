package com.example.nequifirebase.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nequifirebase.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v){
        Intent i= new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    public void register(View v){
        Intent i= new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    public void onBackPressed() {

    }
}