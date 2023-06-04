package com.example.nequifirebase.presenter;

public interface LoginContract {
    interface View {
        void showErrorMessage(String message);
        void navigateToStartScreen();
    }

    interface Presenter {
        void onLoginButtonClicked(String telefono, String pin);
        void onRegisterButtonClicked(String nombre, String email, String telefono, String pin, String saldo);
        void onCheckPhoneClicked(String telefono);
    }
}