package com.example.nequifirebase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nequifirebase.R;
import com.example.nequifirebase.presenter.LoginContract;
import com.example.nequifirebase.presenter.LoginPresenter;
import com.example.nequifirebase.presenter.SessionManager;

public class RegisterActivity extends AppCompatActivity implements LoginContract.View {

    private EditText etNombre, etEmail, etTelefono, etPin, etRepPin;
    private LoginContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNombre=(EditText) findViewById(R.id.etNombre);
        etEmail=(EditText) findViewById(R.id.etCorreo);
        etTelefono=(EditText) findViewById(R.id.etTelefono);
        etPin=(EditText) findViewById(R.id.etPin);
        etRepPin=(EditText) findViewById(R.id.etRepPin);

        presenter = new LoginPresenter(this);
    }


    public void saveRegister(View v){
        String nombre = etNombre.getText().toString();
        String email = etEmail.getText().toString();
        String telefono = etTelefono.getText().toString();
        String pin = etPin.getText().toString();
        String saldo = "1000000";
        presenter.onRegisterButtonClicked(nombre, email, telefono, pin, saldo);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToStartScreen() {
        String telefono = etTelefono.getText().toString();
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.saveSession(telefono);
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }


    public void back(View v){
        finish();
    }
}