package es.iescarrillo.ishoppinglist;

import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String nombre;
    private String nota;
    private boolean pendiente;

    public Producto(int id, String nombre, String nota, boolean pendiente) {
        this.id = id;
        this.nombre = nombre;
        this.nota = nota;
        this.pendiente = pendiente;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getNota() { return nota; }
    public boolean isPendiente() { return pendiente; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNota(String nota) { this.nota = nota; }
    public void setPendiente(boolean pendiente) { this.pendiente = pendiente; }

    public String toString() { return nombre; }
}
