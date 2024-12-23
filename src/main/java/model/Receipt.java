package sales_tax_application.src.main.java.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Represents a receipt containing itemized details, total taxes, and total price.
 */
public class Receipt {

    private final List<String> lines;
    private final BigDecimal totalTaxes;
    private final BigDecimal totalPrice;

    /**
     * Constructs a Receipt object with itemized lines, total taxes, and total price.
     *
     * @param lines      the itemized lines of the receipt
     * @param totalTaxes the total tax amount
     * @param totalPrice the total price including taxes
     * @throws IllegalArgumentException if any argument is null
     */
    public Receipt(List<String> lines, BigDecimal totalTaxes, BigDecimal totalPrice) {
        if (lines == null || totalTaxes == null || totalPrice == null) {
            throw new IllegalArgumentException("Receipt fields cannot be null");
        }
        this.lines = Collections.unmodifiableList(lines);
        this.totalTaxes = totalTaxes;
        this.totalPrice = totalPrice;
    }

    /**
     * Returns an unmodifiable view of the receipt lines.
     *
     * @return the itemized receipt lines
     */
    public List<String> getLines() {
        return lines;
    }

    /**
     * Returns the total tax amount.
     *
     * @return the total taxes
     */
    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    /**
     * Returns the total price including taxes.
     *
     * @return the total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Formats the receipt as a string with itemized details, total taxes, and total price.
     *
     * @return a string representation of the receipt
     */
    @Override
    public String toString() {
        StringBuilder receiptBuilder = new StringBuilder();
        for (String line : lines) {
            receiptBuilder.append(line).append(System.lineSeparator());
        }
        receiptBuilder.append(String.format("Sales Taxes: %.2f%n", totalTaxes));
        receiptBuilder.append(String.format("Total: %.2f", totalPrice));
        return receiptBuilder.toString();
    }
}

