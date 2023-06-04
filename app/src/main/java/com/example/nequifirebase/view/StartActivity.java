package com.example.nequifirebase.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nequifirebase.R;
import com.example.nequifirebase.model.DatabaseHelper;
import com.example.nequifirebase.presenter.SessionManager;
import com.example.nequifirebase.presenter.StartContract;
import com.example.nequifirebase.presenter.StartPresenter;

public class StartActivity extends AppCompatActivity implements StartContract.View {

    private TextView tvUsuario,tvTotal;
    private StartPresenter presenter;
    private String telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        tvUsuario = findViewById(R.id.tvUsuario);
        tvTotal = findViewById(R.id.tvTotal);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        telefono = sessionManager.getPhone();

        // Crea una instancia del presenter y pásale la referencia de DatabaseHelper
        presenter = new StartPresenter(this, new DatabaseHelper());

        // Llama al método para obtener los datos del usuario
        presenter.getUserInfo(telefono);
    }

    @Override
    public void showUserInfo(String name, String saldo) {
        tvUsuario.setText(name);
        tvTotal.setText(saldo);
    }

    public void sendMoney(View v){
        Intent i= new Intent(this, SendMoneyActivity.class);
        startActivity(i);
    }

    public void card(View v){
        Intent i= new Intent(this, CardActivity.class);
        startActivity(i);
    }

    public void history(View v){
        Intent i= new Intent(this, HistoryActivity.class);
        startActivity(i);
    }



    public void endSesion(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Estás seguro de que deseas cerrar sesión?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cerrarSesion();
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Estás seguro que quieres salir de Nequi?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cerrarSesion();
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public  void cerrarSesion(){
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.clearSession();
        Intent intent1=new Intent(this, MainActivity.class);
        startActivity(intent1);
    }


}