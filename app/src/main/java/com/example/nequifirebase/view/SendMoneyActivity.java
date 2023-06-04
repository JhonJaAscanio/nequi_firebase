package com.example.nequifirebase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nequifirebase.R;
import com.example.nequifirebase.model.DatabaseHelper;
import com.example.nequifirebase.presenter.LoginContract;
import com.example.nequifirebase.presenter.LoginPresenter;
import com.example.nequifirebase.presenter.SendMoneyContract;
import com.example.nequifirebase.presenter.SendMoneyPresenter;
import com.example.nequifirebase.presenter.SessionManager;
import com.example.nequifirebase.presenter.StartPresenter;

public class SendMoneyActivity extends AppCompatActivity implements SendMoneyContract.View {

    private EditText etCelDestino,etMonto,etMensaje;
    private SendMoneyContract.Presenter presenter;
    private StartPresenter presenterSaldo;
    private String datoTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        etCelDestino=findViewById(R.id.etCel);
        etMonto=findViewById(R.id.etMonto);
        etMensaje=findViewById(R.id.etMensaje);
        //Recuperar datos del SharedPreference
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        datoTelefono = sessionManager.getPhone();

        presenter = new SendMoneyPresenter(this);

     /*   // Crea una instancia del presenter y pásale la referencia de DatabaseHelper
        presenterSaldo = new StartPresenter(this, new DatabaseHelper());

        // Llama al método para obtener los datos del usuario
        presenterSaldo.getUserInfo(datoTelefono);*/
    }


    public void Transferir(View v){
        String num_destino=etCelDestino.getText().toString();
        String monto=etMonto.getText().toString();
        String mensaje=etMensaje.getText().toString();
        String forma_pago="Disponible";
        presenter.onTransferMoneyClicked(datoTelefono,num_destino,monto,mensaje,forma_pago);
    }

    public void back(View v) {
        Intent intent1 = new Intent(this, StartActivity.class);
        startActivity(intent1);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToStartScreen() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}