package com.example.nequifirebase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nequifirebase.R;
import com.example.nequifirebase.presenter.SendMoneyContract;
import com.example.nequifirebase.presenter.SendMoneyPresenter;
import com.example.nequifirebase.presenter.SessionManager;

public class SendMoneyActivity extends AppCompatActivity implements SendMoneyContract.View {

    private EditText etCelDestino,etMonto,etMensaje;
    private TextView tvDispo2;
    private SendMoneyContract.Presenter presenter,presenterSaldo;
    private String datoTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        etCelDestino=findViewById(R.id.etCel);
        etMonto=findViewById(R.id.etMonto);
        etMensaje=findViewById(R.id.etMensaje);
        tvDispo2=findViewById(R.id.tvDispo2);
        //Recuperar datos del SharedPreference
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        datoTelefono = sessionManager.getPhone();

        presenter = new SendMoneyPresenter(this);
        presenter.getUserInfo(datoTelefono);



    }

    @Override
    public void showUserInfo(String name, String saldo) {
        tvDispo2.setText(saldo);
    }


    public void Transferir(View v){

        String num_destino=etCelDestino.getText().toString();

        presenterSaldo = new SendMoneyPresenter(this);
        presenterSaldo.getUserInfo(num_destino);


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