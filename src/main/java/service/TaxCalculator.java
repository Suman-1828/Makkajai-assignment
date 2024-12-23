package sales_tax_application.src.main.java.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import sales_tax_application.src.main.java.model.Item;

/**
 * Utility class for calculating taxes on items.
 */
public class TaxCalculator {

    // Constants for tax rates
    private static final BigDecimal BASIC_SALES_TAX_RATE = BigDecimal.valueOf(0.10);
    private static final BigDecimal IMPORT_DUTY_RATE = BigDecimal.valueOf(0.05);
    private static final BigDecimal ROUNDING_INCREMENT = BigDecimal.valueOf(0.05);

    private TaxCalculator() {
        // Private constructor to prevent instantiation of utility class
    }

    /**
     * Calculates the total tax for the given item.
     *
     * @param item the item for which the tax is to be calculated
     * @return the calculated tax amount, rounded to the nearest 0.05
     */
    public static BigDecimal calculateTax(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        BigDecimal tax = BigDecimal.ZERO;

        // Apply basic sales tax if the item is not exempt
        if (!item.isExempt()) {
            tax = tax.add(item.getPrice().multiply(BASIC_SALES_TAX_RATE));
        }

        // Apply import duty if the item is imported
        if (item.isImported()) {
            tax = tax.add(item.getPrice().multiply(IMPORT_DUTY_RATE));
        }

        // Round the tax to the nearest 0.05
        return roundToNearestFiveCents(tax);
    }

    /**
     * Rounds the given amount to the nearest 0.05.
     *
     * @param amount the amount to round
     * @return the rounded amount
     */
    private static BigDecimal roundToNearestFiveCents(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        return amount.divide(ROUNDING_INCREMENT, 0, RoundingMode.UP)
                     .multiply(ROUNDING_INCREMENT)
                     .setScale(2, RoundingMode.HALF_EVEN);
    }
}

