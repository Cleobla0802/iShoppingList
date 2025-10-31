package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity for adding new products to the shopping list
 * Provides form fields for product details and save/cancel functionality
 */
public class AnadirProducto extends AppCompatActivity {

    // UI Components
    private EditText productNameEditText;
    private EditText productNoteEditText;
    private EditText productQuantityEditText;
    private CheckBox purchaseStatusCheckBox;
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_producto);

        initializeUIComponents();
        setupButtonListeners();
    }

    /**
     * Initializes all UI components by finding their views from the layout
     */
    private void initializeUIComponents() {
        productNameEditText = findViewById(R.id.etNombre);
        productNoteEditText = findViewById(R.id.etNota);
        productQuantityEditText = findViewById(R.id.etCantidad);
        purchaseStatusCheckBox = findViewById(R.id.cbEstado);
        saveButton = findViewById(R.id.btnGuardar);
        cancelButton = findViewById(R.id.btnCancelar);
    }

    /**
     * Sets up click listeners for the save and cancel buttons
     */
    private void setupButtonListeners() {
        // Save button - validates input and adds new product
        saveButton.setOnClickListener(v -> {
            saveNewProduct();
        });

        // Cancel button - closes the activity without saving
        cancelButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    /**
     * Validates input fields and saves the new product to the main list
     * Shows toast message if product name is empty
     */
    private void saveNewProduct() {
        String productName = productNameEditText.getText().toString().trim();
        String productNote = productNoteEditText.getText().toString().trim();
        String quantityText = productQuantityEditText.getText().toString().trim();
        boolean needsPurchase = purchaseStatusCheckBox.isChecked();

        // Validate that product name is not empty
        if (productName.isEmpty()) {
            showEmptyNameWarning();
            return;
        }

        // Handle quantity - default to 0 if empty
        int quantity = quantityText.isEmpty() ? 0 : Integer.parseInt(quantityText);

        addProductToMainList(productName, productNote, quantity, needsPurchase);
    }

    /**
     * Creates a new product and adds it to the main activity's product list
     * @param productName The name of the product
     * @param productNote Additional notes about the product
     * @param quantity The quantity of the product
     * @param needsPurchase Whether the product needs to be purchased
     */
    private void addProductToMainList(String productName, String productNote, int quantity, boolean needsPurchase) {
        Producto newProduct = new Producto(0, productName, productNote, needsPurchase);
        newProduct.setQuantity(quantity);

        // Return to MainActivity with the new product
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newProduct", newProduct);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    /**
     * Shows a warning toast message when product name is empty
     */
    private void showEmptyNameWarning() {
        Toast.makeText(
                this,
                "Product name cannot be empty",
                Toast.LENGTH_SHORT
        ).show();
    }
}