package com.example.itszas_retrofi.model;

public class DatosItszas {
    private String user;
    private String codigo;
    private String carrera;
    private String correo;
    private String bonos;
    private String image;

    public DatosItszas(String user, String codigo, String carrera, String correo, String bonos, String image) {
        this.user = user;
        this.codigo = codigo;
        this.carrera = carrera;
        this.correo = correo;
        this.bonos = bonos;
        this.image = image;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getBonos() {
        return bonos;
    }

    public void setBonos(String bonos) {
        this.bonos = bonos;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
