package es.iescarrillo.ishoppinglist;

import java.io.Serializable;

public class Producto implements Serializable {

    private int id;
    private String nombre;
    private String nota;
    private int cantidad;
    private boolean estado;

    // Constructor vacío
    public Producto() {

    }

    // Constructor con todos los datos
    public Producto(int id, String nombre, String nota, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.nota = nota;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nota='" + nota + '\'' +
                ", cantidad=" + cantidad +
                ", estado=" + estado +
                '}';
    }
}