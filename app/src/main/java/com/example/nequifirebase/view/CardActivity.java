package com.example.nequifirebase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nequifirebase.R;

public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
    }



    public void back(View v){
        Intent intent1=new Intent(this, StartActivity.class);
        startActivity(intent1);
    }
}