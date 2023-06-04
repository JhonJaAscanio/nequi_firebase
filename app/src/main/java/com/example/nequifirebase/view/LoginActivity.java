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

public class LoginActivity extends AppCompatActivity  implements LoginContract.View {

    private EditText etTelefono;
    private LoginContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etTelefono=findViewById(R.id.etTelefono);
        presenter = new LoginPresenter(this);

    }


    public void checkPhone(View v){
        String telefono = etTelefono.getText().toString();
        presenter.onCheckPhoneClicked(telefono);
    }


    public void back(View v){
        Intent intent1= new Intent(this,MainActivity.class);
        startActivity(intent1);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToStartScreen() {
        Intent intent = new Intent(this, PinActivity.class);
        intent.putExtra("telefono",etTelefono.getText().toString());
        startActivity(intent);
        finish();
    }
}