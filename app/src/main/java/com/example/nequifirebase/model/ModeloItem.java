package com.example.nequifirebase.model;

public class ModeloItem {
    private String celOrigen,celDestino,monto,metodo_envio,mensaje,fecha_creacion,celLogeado,nomOri,nomDes;

    public  ModeloItem(String celOrigen, String celDestino, String monto, String metodo_envio, String mensaje, String fecha_creacion,String celLogeado,String nomOri,String nomDes) {
        this.celOrigen= celOrigen;
        this.celDestino = celDestino;
        this.monto = monto;
        this.metodo_envio = metodo_envio;
        this.mensaje = mensaje;
        this.fecha_creacion= fecha_creacion;
        this.celLogeado=celLogeado;
        this.nomOri=nomOri;
        this.nomDes=nomDes;
    }

    public String getCelOrigen() {
        return celOrigen;
    }

    public String getCelDestino() {
        return celDestino;
    }

    public String getMonto() {
        return monto;
    }

    public String getMetodo_envio() {
        return metodo_envio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public String getCelLogeado() {
        return celLogeado;
    }

    public String getNomOri() {
        return nomOri;
    }

    public String getNomDes() {
        return nomDes;
    }
}