package com.example.nequifirebase.presenter;

import com.example.nequifirebase.model.DatabaseHelper;
import com.example.nequifirebase.model.User;

public class StartPresenter implements StartContract.Presenter{
    private StartContract.View view;
    private DatabaseHelper databaseHelper;

    public StartPresenter(StartContract.View view, DatabaseHelper databaseHelper) {
        this.view = view;
        this.databaseHelper = databaseHelper;
    }


    @Override
    public void getUserInfo(String telefono) {
        databaseHelper.getUserData(telefono,new DatabaseHelper.DataCallback<User>() {
            @Override
            public void onDataLoaded(User user) {
                if (user != null) {
                    String name = user.getNombre();
                    String saldo = user.getSaldo();

                    // Actualiza la Vista con los datos del usuario
                    view.showUserInfo(name, saldo);

                    
                } else {
                    // Manejo de errores o acciones en caso de que no se encuentren los datos del usuario
                }
            }

            @Override
            public void onDataError(String errorMessage) {
                // Manejo de errores en caso de que ocurra un error al cargar los datos del usuario
            }
        });
    }


}