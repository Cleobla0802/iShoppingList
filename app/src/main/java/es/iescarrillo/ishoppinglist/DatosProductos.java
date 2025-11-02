package es.iescarrillo.ishoppinglist;

import java.util.ArrayList;

public class DatosProductos {
    public static ArrayList<Producto> lista = new ArrayList<>();

    public static void cargarDatos() {
        if (lista.size() == 0) {
            lista.add(new Producto(1, "Leche", "Desnatada", true));
            lista.add(new Producto(2, "Pan", "Integral", false));
            lista.add(new Producto(3, "Huevos", "Talla L", false));
        }
    }
}
