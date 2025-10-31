package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity for displaying detailed information about a selected product
 * Shows all product attributes and provides options to edit or return
 */
public class DetallesProductos extends AppCompatActivity {

    // UI Components
    private TextView productIdTextView;
    private TextView productNameTextView;
    private TextView productNoteTextView;
    private TextView productQuantityTextView;
    private TextView productStatusTextView;
    private Button editProductButton;
    private Button returnButton;

    // Data
    private Producto selectedProduct;
    private int productPosition;

    // ActivityResultLauncher to replace deprecated startActivityForResult
    private final ActivityResultLauncher<Intent> editProductLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Producto updatedProduct = (Producto) result.getData().getSerializableExtra("updatedProduct");
                    if (updatedProduct != null) {
                        selectedProduct = updatedProduct;
                        displayAllProductDetails();

                        // Return updated product to MainActivity
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("updatedProduct", updatedProduct);
                        resultIntent.putExtra("position", productPosition);
                        setResult(RESULT_OK, resultIntent);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        initializeUIComponents();
        retrieveProductData();
        displayAllProductDetails();
        setupButtonListeners();
    }

    /**
     * Initializes all UI components by finding their views from the layout
     */
    private void initializeUIComponents() {
        productIdTextView = findViewById(R.id.tvId);
        productNameTextView = findViewById(R.id.tvNombre);
        productNoteTextView = findViewById(R.id.tvNota);
        productQuantityTextView = findViewById(R.id.tvCantidad);
        productStatusTextView = findViewById(R.id.tvEstado);
        editProductButton = findViewById(R.id.btnEditar);
        returnButton = findViewById(R.id.btnVolver);
    }

    /**
     * Retrieves product data passed from the previous activity via Intent
     */
    private void retrieveProductData() {
        selectedProduct = (Producto) getIntent().getSerializableExtra("producto");
        productPosition = getIntent().getIntExtra("position", -1);

        // Create a new instance to avoid reference issues
        if (selectedProduct != null) {
            selectedProduct = new Producto(
                    selectedProduct.getId(),
                    selectedProduct.getName(),
                    selectedProduct.getNote(),
                    selectedProduct.getPurchaseStatus()
            );
            selectedProduct.setQuantity(selectedProduct.getQuantity());
        }
    }

    /**
     * Displays ALL product details in the appropriate TextViews
     */
    private void displayAllProductDetails() {
        if (selectedProduct != null) {
            productIdTextView.setText("ID: " + selectedProduct.getId());
            productNameTextView.setText("Name: " + selectedProduct.getName());
            productNoteTextView.setText("Note: " + selectedProduct.getNote());
            productQuantityTextView.setText("Quantity: " + selectedProduct.getQuantity());

            String statusText = selectedProduct.getPurchaseStatus() ? "TO BUY" : "PURCHASED";
            productStatusTextView.setText("Status: " + statusText);
        }
    }

    /**
     * Sets up click listeners for the edit and return buttons
     */
    private void setupButtonListeners() {
        // Edit button - opens the product editing screen
        editProductButton.setOnClickListener(v -> {
            openProductEditScreen();
        });

        // Return button - closes this activity and returns to the main screen
        returnButton.setOnClickListener(v -> {
            finish();
        });
    }

    /**
     * Opens the product editing screen and passes the current product data
     */
    private void openProductEditScreen() {
        Intent editIntent = new Intent(DetallesProductos.this, EditarProducto.class);
        editIntent.putExtra("producto", selectedProduct);
        editIntent.putExtra("position", productPosition);
        editProductLauncher.launch(editIntent);
    }
}