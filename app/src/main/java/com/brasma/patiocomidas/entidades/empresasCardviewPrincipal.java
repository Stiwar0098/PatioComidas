package com.brasma.patiocomidas.entidades;

import android.net.Uri;

public class empresasCardviewPrincipal {
    private Uri foto;
    private String nombreEmpresa;
    private String tipo;
    private String direccion;
    private double latitud;
    private double longitud;
    private double distancia;
    private boolean tieneBano;
    private boolean aceptaTarjetaCredito;
    private boolean deliveryPropio;
    private boolean autoServicio;
    private String tipoLocal;
    private boolean paraServirse;
    private boolean paraLlevar;
    private boolean aireAcondicionado;
    private boolean estacionamiento;
    private boolean camaras;
    private boolean cervezas;
    private boolean fumar;
    private boolean musica;
    private boolean guardia;
    private boolean ventilador;
    private boolean wifi;



    public empresasCardviewPrincipal() {
    }

    public empresasCardviewPrincipal(Uri foto, String nombreEmpresa, String tipo, String direccion, double latitud, double longitud, double distancia, boolean tieneBano, boolean aceptaTarjetaCredito, boolean deliveryPropio, boolean autoServicio, String tipoLocal, boolean paraServirse, boolean paraLlevar, boolean aireAcondicionado, boolean estacionamiento, boolean camaras, boolean cervezas, boolean fumar, boolean musica, boolean guardia, boolean ventilador, boolean wifi) {
        this.foto = foto;
        this.nombreEmpresa = nombreEmpresa;
        this.tipo = tipo;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.distancia = distancia;
        this.tieneBano = tieneBano;
        this.aceptaTarjetaCredito = aceptaTarjetaCredito;
        this.deliveryPropio = deliveryPropio;
        this.autoServicio = autoServicio;
        this.tipoLocal = tipoLocal;
        this.paraServirse = paraServirse;
        this.paraLlevar = paraLlevar;
        this.aireAcondicionado = aireAcondicionado;
        this.estacionamiento = estacionamiento;
        this.camaras = camaras;
        this.cervezas = cervezas;
        this.fumar = fumar;
        this.musica = musica;
        this.guardia = guardia;
        this.ventilador = ventilador;
        this.wifi = wifi;
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

    public boolean isTieneBano() {
        return tieneBano;
    }

    public void setTieneBano(boolean tieneBano) {
        this.tieneBano = tieneBano;
    }

    public boolean isAceptaTarjetaCredito() {
        return aceptaTarjetaCredito;
    }

    public void setAceptaTarjetaCredito(boolean aceptaTarjetaCredito) {
        this.aceptaTarjetaCredito = aceptaTarjetaCredito;
    }

    public boolean isDeliveryPropio() {
        return deliveryPropio;
    }

    public void setDeliveryPropio(boolean deliveryPropio) {
        this.deliveryPropio = deliveryPropio;
    }

    public boolean isAutoServicio() {
        return autoServicio;
    }

    public void setAutoServicio(boolean autoServicio) {
        this.autoServicio = autoServicio;
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

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(boolean estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public boolean isCamaras() {
        return camaras;
    }

    public void setCamaras(boolean camaras) {
        this.camaras = camaras;
    }

    public boolean isCervezas() {
        return cervezas;
    }

    public void setCervezas(boolean cervezas) {
        this.cervezas = cervezas;
    }

    public boolean isFumar() {
        return fumar;
    }

    public void setFumar(boolean fumar) {
        this.fumar = fumar;
    }

    public boolean isMusica() {
        return musica;
    }

    public void setMusica(boolean musica) {
        this.musica = musica;
    }

    public boolean isGuardia() {
        return guardia;
    }

    public void setGuardia(boolean guardia) {
        this.guardia = guardia;
    }

    public boolean isVentilador() {
        return ventilador;
    }

    public void setVentilador(boolean ventilador) {
        this.ventilador = ventilador;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }
}
