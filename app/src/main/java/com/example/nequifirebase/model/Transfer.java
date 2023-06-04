package com.example.nequifirebase.model;

public class Transfer {
    private String num_origen,num_destino,monto,metodo_pago,mensaje,fecha_creacion;

    public Transfer(String num_origen, String num_destino, String monto, String metodo_pago, String mensaje, String fecha_creacion) {
        this.num_origen = num_origen;
        this.num_destino = num_destino;
        this.monto = monto;
        this.metodo_pago = metodo_pago;
        this.mensaje = mensaje;
        this.fecha_creacion = fecha_creacion;
    }

    public String getNum_origen() {
        return num_origen;
    }

    public String getNum_destino() {
        return num_destino;
    }

    public String getMonto() {
        return monto;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }
}
