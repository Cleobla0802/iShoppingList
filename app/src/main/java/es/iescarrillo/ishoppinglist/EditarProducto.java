package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity for editing existing product information
 * Allows users to modify product details and save changes
 */
public class EditarProducto extends AppCompatActivity {

    // UI Components
    private EditText productNameEditText;
    private EditText productNoteEditText;
    private EditText productQuantityEditText;
    private CheckBox purchaseStatusCheckBox;
    private Button saveButton;
    private Button cancelButton;

    // Data
    private Producto productToEdit;
    private int productPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        initializeUIComponents();
        retrieveProductData();
        populateFormWithCurrentData();
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
     * Retrieves the product data passed from the previous activity
     */
    private void retrieveProductData() {
        productToEdit = (Producto) getIntent().getSerializableExtra("producto");
        productPosition = getIntent().getIntExtra("position", -1);
    }

    /**
     * Populates the form fields with the current product data
     */
    private void populateFormWithCurrentData() {
        if (productToEdit != null) {
            productNameEditText.setText(productToEdit.getName());
            productNoteEditText.setText(productToEdit.getNote());
            productQuantityEditText.setText(String.valueOf(productToEdit.getQuantity()));
            purchaseStatusCheckBox.setChecked(productToEdit.getPurchaseStatus());
        }
    }

    /**
     * Sets up click listeners for the save and cancel buttons
     */
    private void setupButtonListeners() {
        // Save button - updates product with new values and returns to previous activity
        saveButton.setOnClickListener(v -> {
            updateProductWithNewValues();
        });

        // Cancel button - closes activity without saving changes
        cancelButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    /**
     * Updates the product object with the new values from the form fields
     */
    private void updateProductWithNewValues() {
        if (productToEdit != null) {
            productToEdit.setName(productNameEditText.getText().toString());
            productToEdit.setNote(productNoteEditText.getText().toString());

            // Handle quantity - default to 0 if empty
            String quantityText = productQuantityEditText.getText().toString();
            int quantity = quantityText.isEmpty() ? 0 : Integer.parseInt(quantityText);
            productToEdit.setQuantity(quantity);

            productToEdit.setPurchaseStatus(purchaseStatusCheckBox.isChecked());

            // Return the updated product
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedProduct", productToEdit);
            resultIntent.putExtra("position", productPosition);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}