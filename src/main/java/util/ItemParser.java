package sales_tax_application.src.main.java.util;

import java.math.BigDecimal;

import sales_tax_application.src.main.java.model.Item;

/**
 * Utility class for parsing an item description into an Item object.
 */
public class ItemParser {

    private static final String DELIMITER = " at ";

    private ItemParser() {
        // Private constructor to prevent instantiation of utility class
    }

    /**
     * Parses an input string describing an item and returns an Item object.
     *
     * @param input the input string, expected to be in the format "<name> at <price>"
     * @return an Item object with the parsed details
     * @throws IllegalArgumentException if the input format is invalid
     */
    public static Item parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        String[] parts = input.split(DELIMITER);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid input format. Expected format: '<name> at <price>'");
        }

        String namePart = parts[0].trim();
        BigDecimal price;
        try {
            price = new BigDecimal(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format: " + parts[1].trim(), e);
        }

        boolean isExempt = isExemptItem(namePart);
        boolean isImported = namePart.toLowerCase().contains("imported");

        return new Item(namePart, price, isExempt, isImported);
    }

    /**
     * Checks if the item is exempt from sales tax based on its description.
     *
     * @param name the name of the item
     * @return true if the item is exempt, false otherwise
     */
    private static boolean isExemptItem(String name) {
        if (name == null) {
            return false;
        }
        String lowerCaseName = name.toLowerCase();
        return lowerCaseName.contains("book") || lowerCaseName.contains("chocolate") || lowerCaseName.contains("pill");
    }
}

