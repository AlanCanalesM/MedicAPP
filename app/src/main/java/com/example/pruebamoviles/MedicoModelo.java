package com.example.pruebamoviles;

public class MedicoModelo {

    private String Nombre;
    private String Especialidad;
    private String Cedula;

    public MedicoModelo(String nombre, String especialidad, String cedula){
        Nombre=nombre;
        Especialidad=especialidad;
        Cedula=cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }
}
