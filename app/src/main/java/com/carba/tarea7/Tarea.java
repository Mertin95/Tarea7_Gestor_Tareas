package com.carba.tarea7;

public class Tarea {
    private String nombre;
    private String descripcion;
    private String fechaEntrega;
    private String horaEntrega;
    private String nombreAsignatura;
    private boolean estado;


    public Tarea(String nombre, String descripcion, String fechaEntrega, String horaEntrega, String nombreAsignatura, boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
        this.nombreAsignatura = nombreAsignatura;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaEntrega='" + fechaEntrega + '\'' +
                ", horaEntrega='" + horaEntrega + '\'' +
                ", nombreAsignatura='" + nombreAsignatura + '\'' +
                ", estado=" + estado +
                '}';
    }
}
