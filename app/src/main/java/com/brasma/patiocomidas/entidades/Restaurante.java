package com.brasma.patiocomidas.entidades;

import android.net.Uri;

public class Restaurante {
    private Uri foto;
    private String nombreEmpresa;
    private String tipo;
    private String direccion;
    private double latitud;
    private double longitud;
    private double distancia;
    private String tipoLocal;
    private boolean paraServirse;
    private boolean paraLlevar;
    private boolean deliveryPropio;
    private boolean wifi;
    private boolean aceptaTarjetaCredito;
    private boolean abierCerrad;
    private String ciudad;



    public Restaurante() {
    }

    public Restaurante(Uri foto, String nombreEmpresa, String tipo, String direccion, double latitud, double longitud, double distancia, String tipoLocal, boolean paraServirse, boolean paraLlevar, boolean deliveryPropio, boolean wifi, boolean aceptaTarjetaCredito, boolean abierCerrad, String ciudad) {
        this.foto = foto;
        this.nombreEmpresa = nombreEmpresa;
        this.tipo = tipo;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.distancia = distancia;
        this.tipoLocal = tipoLocal;
        this.paraServirse = paraServirse;
        this.paraLlevar = paraLlevar;
        this.deliveryPropio = deliveryPropio;
        this.wifi = wifi;
        this.aceptaTarjetaCredito = aceptaTarjetaCredito;
        this.abierCerrad = abierCerrad;
        this.ciudad = ciudad;
    }

    public Uri getFoto() {
        return foto;
    }

    public void setFoto(Uri foto) {
        this.foto = foto;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(String tipoLocal) {
        this.tipoLocal = tipoLocal;
    }

    public boolean isParaServirse() {
        return paraServirse;
    }

    public void setParaServirse(boolean paraServirse) {
        this.paraServirse = paraServirse;
    }

    public boolean isParaLlevar() {
        return paraLlevar;
    }

    public void setParaLlevar(boolean paraLlevar) {
        this.paraLlevar = paraLlevar;
    }

    public boolean isDeliveryPropio() {
        return deliveryPropio;
    }

    public void setDeliveryPropio(boolean deliveryPropio) {
        this.deliveryPropio = deliveryPropio;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isAceptaTarjetaCredito() {
        return aceptaTarjetaCredito;
    }

    public void setAceptaTarjetaCredito(boolean aceptaTarjetaCredito) {
        this.aceptaTarjetaCredito = aceptaTarjetaCredito;
    }

    public boolean isAbierCerrad() {
        return abierCerrad;
    }

    public void setAbierCerrad(boolean abierCerrad) {
        this.abierCerrad = abierCerrad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
