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

        Double montod = Double.parseDouble(monto);

        // Obtén los saldos de origen y destino desde la base de datos
        databaseHelper.getBalance(num_origen, new DatabaseHelper.DataCallback<Double>() {
            @Override
            public void onDataLoaded(Double saldo_origend) {
                // Aquí tienes el saldo de origen, puedes utilizarlo en tu lógica
                Double saldo_origen_new = saldo_origend - montod;
                String new_saldo_origen = saldo_origen_new.toString();

                // Obtén el saldo del destino desde la base de datos
                databaseHelper.getBalance(num_destino, new DatabaseHelper.DataCallback<Double>() {
                    @Override
                    public void onDataLoaded(Double saldo_destinod) {
                        // Aquí tienes el saldo de destino, puedes utilizarlo en tu lógica
                        Double saldo_destino_new = saldo_destinod + montod;
                        String new_saldo_destino = saldo_destino_new.toString();
                        // Codigo para crear registro en transferencia y editar saldos

                        DatabaseHelper.DataCallback<Boolean> callback = new DatabaseHelper.DataCallback<Boolean>() {
                            @Override
                            public void onDataLoaded(Boolean result) {
                                if (result) {
                                    databaseHelper.updateBalance(num_destino, new_saldo_destino, new DatabaseHelper.DataCallback<Boolean>() {
                                        @Override
                                        public void onDataLoaded(Boolean result) {
                                            if (result) {
                                                databaseHelper.addTrans(transfer, new DatabaseHelper.DataCallback<Boolean>() {
                                                    @Override
                                                    public void onDataLoaded(Boolean result) {
                                                        if (result) {
                                                            view.showErrorMessage("Transacción Exitosa");
                                                            view.navigateToStartScreen();
                                                        } else {
                                                            view.showErrorMessage("10.Ocurrió un problema, vuelve a intentarlo!!");
                                                        }
                                                    }

                                                    @Override
                                                    public void onDataError(String errorMessage) {
                                                        view.showErrorMessage("7.Ocurrió un problema, vuelve a intentarlo!!");
                                                    }
                                                });
                                            } else {
                                                view.showErrorMessage("6.Ocurrió un problema, vuelve a intentarlo!!");
                                            }
                                        }

                                        @Override
                                        public void onDataError(String errorMessage) {
                                            view.showErrorMessage("5.Ocurrió un problema, vuelve a intentarlo!!");
                                        }
                                    });
                                } else {
                                    view.showErrorMessage("4.Ocurrió un problema, vuelve a intentarlo!!");
                                }
                            }

                            @Override
                            public void onDataError(String errorMessage) {
                                view.showErrorMessage("3.Ocurrió un problema, vuelve a intentarlo!!");
                            }
                        };

                        databaseHelper.updateBalance(num_origen, new_saldo_origen, callback);
                    }

                    @Override
                    public void onDataError(String errorMessage) {
                        view.showErrorMessage("2.Ocurrió un problema, vuelve a intentarlo!!");
                    }
                });
            }

            @Override
            public void onDataError(String errorMessage) {
                view.showErrorMessage("1.Ocurrió un problema, vuelve a intentarlo!!");
            }
        });
    }



    //Traer ala tarjeta de Disponible:
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


