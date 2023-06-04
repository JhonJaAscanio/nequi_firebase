package com.example.nequifirebase.presenter;

import com.example.nequifirebase.model.DatabaseHelper;
import com.example.nequifirebase.model.User;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private DatabaseHelper databaseHelper;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.databaseHelper = new DatabaseHelper();
    }

    @Override
    public void onLoginButtonClicked(String telefono, String pin) {
        databaseHelper.checkCredentials(telefono, pin, new DatabaseHelper.DataCallback<Boolean>() {
            @Override
            public void onDataLoaded(Boolean isValid) {
                if (isValid) {
                    view.showErrorMessage("Ingreso Exitoso");
                    view.navigateToStartScreen();
                } else {
                    view.showErrorMessage("Credenciales invalidas");
                }
            }

            @Override
            public void onDataError(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

    @Override
    public void onRegisterButtonClicked(String nombre, String email, String telefono, String pin, String saldo) {
        databaseHelper.checkUserExists(email, new DatabaseHelper.DataCallback<Boolean>() {
            @Override
            public void onDataLoaded(Boolean exists) {
                if (exists) {
                    view.showErrorMessage("Usuario existente");
                } else {
                    User newUser = new User(nombre, email,telefono,pin, saldo);
                    databaseHelper.addUser(newUser);
                    view.showErrorMessage("Registro Exitoso");

                    view.navigateToStartScreen();
                }
            }

            @Override
            public void onDataError(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

    @Override
    public void onCheckPhoneClicked(String telefono) {
        databaseHelper.checkPhoneExists(telefono, new DatabaseHelper.DataCallback<Boolean>() {

            @Override
            public void onDataLoaded(Boolean data) {
                if (data) {
                    view.showErrorMessage("Telefono Exitoso");
                    view.navigateToStartScreen();
                } else {
                    view.showErrorMessage("Telefono no Exitoso");
                }
            }

            @Override
            public void onDataError(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

}