package com.example.nequifirebase.model;

public class User {
    private String nombre;
    private String email;
    private String telefono;
    private String pin;
    private String saldo;

    public User(){

    }
    public User(String nombre, String email, String telefono, String pin, String saldo) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.pin = pin;
        this.saldo = saldo;
    }
    public User(Double saldo){
        this.saldo = Double.toString(saldo);
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getPin() {
        return pin;
    }

    public String getSaldo() {
        return saldo;
    }
}
