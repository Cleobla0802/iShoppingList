package es.iescarrillo.ishoppinglist;

import java.util.ArrayList;

public class ListaProductosManager {

    // Lista estática que se comparte en toda la app
    public static ArrayList<Producto> listaProductos = new ArrayList<>();
    private static int contadorId = 1;

    // Obtener toda la lista
    public static ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    // Obtener solo productos NO comprados (los que necesitamos comprar)
    public static ArrayList<Producto> getProductosNoComprados() {
        ArrayList<Producto> noComprados = new ArrayList<>();
        for(int i = 0; i < listaProductos.size(); i++) {
            if(!listaProductos.get(i).isComprado()) {
                noComprados.add(listaProductos.get(i));
            }
        }
        return noComprados;
    }

    // Agregar un producto nuevo
    public static void agregarProducto(Producto producto) {
        producto.setId(contadorId);
        contadorId++;
        listaProductos.add(producto);
    }

    // Eliminar un producto
    public static void eliminarProducto(int id) {
        for(int i = 0; i < listaProductos.size(); i++) {
            if(listaProductos.get(i).getId() == id) {
                listaProductos.remove(i);
                break;
            }
        }
    }

    // Buscar un producto por ID
    public static Producto buscarProducto(int id) {
        for(int i = 0; i < listaProductos.size(); i++) {
            if(listaProductos.get(i).getId() == id) {
                return listaProductos.get(i);
            }
        }
        return null;
    }

    // Editar un producto
    public static void editarProducto(int id, String nombre, String nota, int cantidad) {
        Producto producto = buscarProducto(id);
        if(producto != null) {
            producto.setNombre(nombre);
            producto.setNota(nota);
            producto.setCantidad(cantidad);
        }
    }

    // Marcar como comprado o no comprado
    public static void cambiarEstadoCompra(int id, boolean comprado) {
        Producto producto = buscarProducto(id);
        if(producto != null) {
            producto.setComprado(comprado);
        }
    }

    // Inicializar con datos de ejemplo (opcional)
    public static void inicializarDatos() {
        if(listaProductos.isEmpty()) {
            agregarProducto(new Producto(0, "Leche", "2 litros", 2, false));
            agregarProducto(new Producto(0, "Pan", "Integral", 3, false));
            agregarProducto(new Producto(0, "Huevos", "Docena", 1, false));
            agregarProducto(new Producto(0, "Arroz", "", 1, false));
            agregarProducto(new Producto(0, "Aceite", "De oliva", 1, true));
        }
    }

    // Limpiar toda la lista
    public static void limpiarLista() {
        listaProductos.clear();
        contadorId = 1;
    }
}