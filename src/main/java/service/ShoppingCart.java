package sales_tax_application.src.main.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sales_tax_application.src.main.java.model.Item;
import sales_tax_application.src.main.java.model.Receipt;

/**
 * Represents a shopping cart containing items and provides functionality to generate a receipt.
 */
public class ShoppingCart {

    private final List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds an item to the shopping cart.
     *
     * @param item the item to add
     * @throws IllegalArgumentException if the item is null
     */
    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.add(item);
    }

    /**
     * Generates a receipt for all items in the shopping cart.
     *
     * @return a Receipt object containing the receipt details
     */
    public Receipt generateReceipt() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Shopping cart is empty. Add items before generating a receipt.");
        }

        List<String> receiptLines = new ArrayList<>();
        BigDecimal totalTaxes = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Item item : items) {
            BigDecimal tax = TaxCalculator.calculateTax(item);
            BigDecimal finalPrice = item.getPrice().add(tax);

            receiptLines.add(formatReceiptLine(item, finalPrice));
            totalTaxes = totalTaxes.add(tax);
            totalPrice = totalPrice.add(finalPrice);
        }

        return new Receipt(Collections.unmodifiableList(receiptLines), totalTaxes, totalPrice);
    }

    /**
     * Formats a receipt line for a single item.
     *
     * @param item       the item to format
     * @param finalPrice the final price of the item (price + tax)
     * @return a formatted receipt line
     */
    private String formatReceiptLine(Item item, BigDecimal finalPrice) {
        return String.format("1 %s: %.2f", item.getName(), finalPrice);
    }
}

