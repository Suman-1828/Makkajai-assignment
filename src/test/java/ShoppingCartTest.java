package sales_tax_application.src.test.java;


import sales_tax_application.src.main.java.model.Item;
import sales_tax_application.src.main.java.model.Receipt;
import sales_tax_application.src.main.java.service.ShoppingCart;

import java.math.BigDecimal;

/**
 * Unit tests for the ShoppingCart class.
 */
public class ShoppingCartTest {

    @Test
    public void shouldGenerateReceiptForBasicItems() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("book", new BigDecimal("12.49"), true, false));
        cart.addItem(new Item("music CD", new BigDecimal("14.99"), false, false));
        cart.addItem(new Item("chocolate bar", new BigDecimal("0.85"), true, false));

        // Act
        Receipt receipt = cart.generateReceipt();

        // Assert
        String expectedReceipt = """
            1 book: 12.49
            1 music CD: 16.49
            1 chocolate bar: 0.85
            Sales Taxes: 1.50
            Total: 29.83
            """;
        assertEquals(expectedReceipt.trim(), receipt.toString().trim());
    }

    @Test
    public void shouldGenerateReceiptForImportedItems() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("imported box of chocolates", new BigDecimal("10.00"), true, true));
        cart.addItem(new Item("imported bottle of perfume", new BigDecimal("47.50"), false, true));

        // Act
        Receipt receipt = cart.generateReceipt();

        // Assert
        String expectedReceipt = """
            1 imported box of chocolates: 10.50
            1 imported bottle of perfume: 54.65
            Sales Taxes: 7.65
            Total: 65.15
            """;
        assertEquals(expectedReceipt.trim(), receipt.toString().trim());
    }

    @Test
    public void shouldGenerateReceiptForMixedItems() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("imported bottle of perfume", new BigDecimal("27.99"), false, true));
        cart.addItem(new Item("bottle of perfume", new BigDecimal("18.99"), false, false));
        cart.addItem(new Item("packet of headache pills", new BigDecimal("9.75"), true, false));
        cart.addItem(new Item("imported box of chocolates", new BigDecimal("11.25"), true, true));

        // Act
        Receipt receipt = cart.generateReceipt();

        // Assert
        String expectedReceipt = """
            1 imported bottle of perfume: 32.19
            1 bottle of perfume: 20.89
            1 packet of headache pills: 9.75
            1 imported box of chocolates: 11.85
            Sales Taxes: 6.70
            Total: 74.68
            """;
        assertEquals(expectedReceipt.trim(), receipt.toString().trim());
    }
}

