package es.iescarrillo.ishoppinglist;

import java.io.Serializable;

public class Producto implements Serializable {

    private int id;
    private String name;
    private String note;
    private int quantity;
    private boolean purchaseStatus;

    public Producto() {
    }

    public Producto(int id, String name, String note, boolean purchaseStatus) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.purchaseStatus = purchaseStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(boolean purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public boolean needsPurchase() {
        return purchaseStatus;
    }

    @Override
    public String toString() {
        return name;
    }
}