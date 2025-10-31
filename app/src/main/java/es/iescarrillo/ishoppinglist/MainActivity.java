package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner productSpinner;
    private Button addProductButton;
    private Button addToPendingButton;
    private Button viewDetailsButton;

    private ArrayList<Producto> productList;
    private int productIdCounter;

    // ActivityResultLaunchers
    private ActivityResultLauncher<Intent> editProductLauncher;
    private ActivityResultLauncher<Intent> addProductLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUIComponents();
        initializeSampleData();
        setupActivityLaunchers();
        setupEventListeners();
    }

    private void initializeUIComponents() {
        productSpinner = findViewById(R.id.spinner);
        addProductButton = findViewById(R.id.btnAdd);
        addToPendingButton = findViewById(R.id.btnPendiente);
        viewDetailsButton = findViewById(R.id.btnVerDetalles);
    }

    private void initializeSampleData() {
        productList = new ArrayList<>();
        productIdCounter = 1;

        productList.add(new Producto(productIdCounter++, "Milk", "Whole milk", true));
        productList.add(new Producto(productIdCounter++, "Bread", "Whole wheat", true));
        productList.add(new Producto(productIdCounter++, "Eggs", "Dozen", true));

        updateProductSpinner();
    }

    private void setupActivityLaunchers() {
        // Launcher for editing products
        editProductLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Serializable serializable = result.getData().getSerializableExtra("updatedProduct");
                        if (serializable instanceof Producto) {
                            Producto updatedProduct = (Producto) serializable;
                            int position = result.getData().getIntExtra("position", -1);
                            if (position != -1) {
                                productList.set(position, updatedProduct);
                                updateProductSpinner();
                                Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        // Launcher for adding new products
        addProductLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Producto newProduct = (Producto) result.getData().getSerializableExtra("newProduct");
                        if (newProduct != null) {
                            addProduct(newProduct);
                        }
                    }
                });
    }

    private void setupEventListeners() {
        viewDetailsButton.setOnClickListener(v -> {
            openProductDetailsScreen();
        });

        addProductButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AnadirProducto.class);
            addProductLauncher.launch(intent);
        });

        addToPendingButton.setOnClickListener(v -> {
            openAddToPendingScreen();
        });
    }

    private void openProductDetailsScreen() {
        int selectedPosition = productSpinner.getSelectedItemPosition();
        if (selectedPosition != Spinner.INVALID_POSITION && selectedPosition < productList.size()) {
            Producto selectedProduct = productList.get(selectedPosition);
            Intent intent = new Intent(MainActivity.this, DetallesProductos.class);
            intent.putExtra("producto", selectedProduct);
            intent.putExtra("position", selectedPosition);
            editProductLauncher.launch(intent);
        }
    }

    private void openAddToPendingScreen() {
        Intent intent = new Intent(MainActivity.this, AnadirPendientes.class);
        intent.putExtra("nonPendingProducts", getNonPendingProducts());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateProductSpinner();
    }

    private void updateProductSpinner() {
        ArrayList<String> productNames = new ArrayList<>();
        for (Producto product : productList) {
            productNames.add(product.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSpinner.setAdapter(adapter);
    }

    public ArrayList<Producto> getNonPendingProducts() {
        ArrayList<Producto> nonPendingProducts = new ArrayList<>();
        for (Producto product : productList) {
            if (product.getPurchaseStatus()) {
                nonPendingProducts.add(product);
            }
        }
        return nonPendingProducts;
    }
}