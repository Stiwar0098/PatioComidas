package com.brasma.patiocomidas.entidades;

import android.net.Uri;

public class empresasCardviewPrincipal {
    private Uri foto;
    private String nombreEmpresa;
    private String tipo;
    private String direccion;

    public empresasCardviewPrincipal() {
    }

    public empresasCardviewPrincipal(Uri foto, String nombreEmpresa, String tipo, String direccion) {
        this.foto = foto;
        this.nombreEmpresa = nombreEmpresa;
        this.tipo = tipo;
        this.direccion = direccion;
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
}
