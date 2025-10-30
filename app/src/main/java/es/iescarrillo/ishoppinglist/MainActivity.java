package es.iescarrillo.ishoppinglist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerProductos;
    ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectar el spinner
        spinnerProductos = findViewById(R.id.spinner);

        // Crear la lista de productos
        listaProductos = new ArrayList<>();
        listaProductos.add(new Producto(1, "Leche", "Comprar 2 litros", true));
        listaProductos.add(new Producto(2, "Pan", "Integral", true));
        listaProductos.add(new Producto(3, "Huevos", "Una docena", true));
        listaProductos.add(new Producto(4, "Arroz", "", true));
        listaProductos.add(new Producto(5, "Aceite", "De oliva", false));

        // Sacar solo los nombres para el spinner
        ArrayList<String> nombres = new ArrayList<>();

        for(int i = 0; i < listaProductos.size(); i++)
        {
            nombres.add(listaProductos.get(i).getNombre());
        }

        // Crear el adapter y ponerlo en el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                nombres
        );
        spinnerProductos.setAdapter(adapter);
    }
}