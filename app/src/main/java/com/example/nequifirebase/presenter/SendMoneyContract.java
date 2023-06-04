package com.example.nequifirebase.presenter;

public interface SendMoneyContract {
    interface View {
        void showErrorMessage(String message);
        void navigateToStartScreen();
    }

    interface Presenter {
        void onTransferMoneyClicked(String num_origen,String num_destino, String monto,String Mensaje, String formaPago);

    }
}
