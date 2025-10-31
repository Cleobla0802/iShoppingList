package es.iescarrillo.ishoppinglist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

/**
 * Activity for adding products to the pending purchase list
 * Displays non-pending products in a spinner for user selection
 */
public class AnadirPendientes extends AppCompatActivity {

    // UI Components
    private Spinner productSpinner;
    private Button saveButton;
    private Button cancelButton;

    // Data
    private ArrayList<Producto> nonPendingProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_pendientes);

        initializeUIComponents();
        loadNonPendingProductsFromIntent();
        setupButtonListeners();
    }

    /**
     * Initializes all UI components by finding their views from the layout
     */
    private void initializeUIComponents() {
        productSpinner = findViewById(R.id.spinnerProductos);
        saveButton = findViewById(R.id.btnGuardar);
        cancelButton = findViewById(R.id.btnCancelar);
    }

    /**
     * Loads non-pending products from the Intent extras with type safety
     */
    @SuppressWarnings("unchecked")
    private void loadNonPendingProductsFromIntent() {
        try {
            nonPendingProducts = (ArrayList<Producto>) getIntent().getSerializableExtra("nonPendingProducts");
        } catch (ClassCastException e) {
            nonPendingProducts = new ArrayList<>();
        }

        if (nonPendingProducts == null || nonPendingProducts.isEmpty()) {
            showNoProductsMessage();
            return;
        }

        setupProductSpinner();
    }

    /**
     * Sets up the product spinner with the non-pending products
     */
    private void setupProductSpinner() {
        ArrayAdapter<Producto> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                nonPendingProducts
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSpinner.setAdapter(spinnerAdapter);
    }

    /**
     * Sets up click listeners for the save and cancel buttons
     */
    private void setupButtonListeners() {
        // Save button - marks selected product as pending and closes activity
        saveButton.setOnClickListener(v -> {
            markSelectedProductAsPending();
        });

        // Cancel button - closes activity without saving changes
        cancelButton.setOnClickListener(v -> {
            finish();
        });
    }

    /**
     * Marks the currently selected product as pending for purchase
     * Shows a confirmation message and closes the activity
     */
    private void markSelectedProductAsPending() {
        int selectedPosition = productSpinner.getSelectedItemPosition();

        // Validate selection
        if (selectedPosition == Spinner.INVALID_POSITION || selectedPosition >= nonPendingProducts.size()) {
            showSelectionError();
            return;
        }

        Producto selectedProduct = nonPendingProducts.get(selectedPosition);
        selectedProduct.setPurchaseStatus(true);

        showSuccessMessage(selectedProduct.getName());
        finish();
    }

    /**
     * Shows a toast message when there are no non-pending products available
     */
    private void showNoProductsMessage() {
        Toast.makeText(
                this,
                "No products available to mark as pending",
                Toast.LENGTH_LONG
        ).show();

        // Disable save button if no products available
        saveButton.setEnabled(false);
    }

    /**
     * Shows a toast message when no product is selected
     */
    private void showSelectionError() {
        Toast.makeText(
                this,
                "Please select a product",
                Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Shows a success message after marking a product as pending
     * @param productName The name of the product that was marked as pending
     */
    private void showSuccessMessage(String productName) {
        Toast.makeText(
                this,
                productName + " marked as pending",
                Toast.LENGTH_SHORT
        ).show();
    }
}