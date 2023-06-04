package com.example.nequifirebase.model;

public class Card {
    private String num_cuenta, num_telefono, saldo, fecha_creacion;

    public Card(String num_cuenta, String num_telefono, String saldo, String fecha_creacion) {
        this.num_cuenta = num_cuenta;
        this.num_telefono = num_telefono;
        this.saldo = saldo;
        this.fecha_creacion = fecha_creacion;
    }

    public String getNum_cuenta() {
        return num_cuenta;
    }

    public String getNum_telefono() {
        return num_telefono;
    }

    public String getSaldo() {
        return saldo;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }
}