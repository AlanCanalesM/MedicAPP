package com.example.pruebamoviles;

public class FarmaciaModelo {
    
    private String nombre;
    private String imagen;
    private String telefono;
    private String ubicacion;

    public FarmaciaModelo(String nombre, String imagen, String telefono, String ubicacion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
