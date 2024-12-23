package sales_tax_application.src.main.java.model;

import java.math.BigDecimal;

/**
 * Represents an item in an inventory or shopping cart with its properties.
 */
public class Item {
    // Name of the item
    private final String name;

    // Price of the item
    private final BigDecimal price;

    // Whether the item is exempt from sales tax
    private final boolean exempt;

    // Whether the item is imported
    private final boolean imported;

    /**
     * Constructs an Item with specified details.
     *
     * @param name     the name of the item
     * @param price    the price of the item
     * @param exempt   true if the item is exempt from sales tax, false otherwise
     * @param imported true if the item is imported, false otherwise
     */
    public Item(String name, BigDecimal price, boolean exempt, boolean imported) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be a positive value.");
        }

        this.name = name.trim();
        this.price = price;
        this.exempt = exempt;
        this.imported = imported;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price of the item.
     *
     * @return the price of the item
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Checks if the item is exempt from sales tax.
     *
     * @return true if the item is exempt, false otherwise
     */
    public boolean isExempt() {
        return exempt;
    }

    /**
     * Checks if the item is imported.
     *
     * @return true if the item is imported, false otherwise
     */
    public boolean isImported() {
        return imported;
    }

    @Override
    public String toString() {
        return String.format("Item{name='%s', price=%s, exempt=%b, imported=%b}", 
                              name, price, exempt, imported);
    }
}

