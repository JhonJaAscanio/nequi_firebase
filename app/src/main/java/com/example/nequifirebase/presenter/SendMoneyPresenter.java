package com.example.nequifirebase.presenter;

import com.example.nequifirebase.model.DatabaseHelper;
import com.example.nequifirebase.model.Transfer;
import com.example.nequifirebase.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMoneyPresenter implements SendMoneyContract.Presenter {
    private SendMoneyContract.View view;
    private DatabaseHelper databaseHelper;

    public SendMoneyPresenter(SendMoneyContract.View view) {
        this.view = view;
        this.databaseHelper = new DatabaseHelper();
    }


    @Override
    public void onTransferMoneyClicked(String num_origen, String num_destino, String monto, String mensaje, String formaPago) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = dateFormat.format(new Date());
        Transfer transfer = new Transfer(num_origen,num_destino,monto,mensaje,formaPago,fecha);

       // User editUsers = new User(num_origen,new_saldo_origen,num_destino,new_saldo_destino);
        databaseHelper.addTrans(transfer, new DatabaseHelper.DataCallback<Boolean>() {
            @Override
            public void onDataLoaded(Boolean result) {
                if (result) {

                    view.showErrorMessage("Transacción Exitosa");
                    view.navigateToStartScreen();
                } else {
                    // Error al agregar el registro
                    view.showErrorMessage("Ocurrio un problema, vuelve a intentarlo!!");
                }
            }

            @Override
            public void onDataError(String errorMessage) {
                // Manejo de errores en caso de que ocurra un error al agregar el registro
                view.showErrorMessage("Ocurrio un problema, vuelve a intentarlo!!");
            }
        });
    }

}
