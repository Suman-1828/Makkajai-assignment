package sales_tax_application.src.main.java;

import java.util.Scanner;

import sales_tax_application.src.main.java.model.Receipt;
import sales_tax_application.src.main.java.service.ShoppingCart;
import sales_tax_application.src.main.java.util.ItemParser;

/**
 * SalesTaxApplication is the main entry point of the application that calculates 
 * sales tax for items added to a shopping cart.
 */
public class SalesTaxApplication {

    /**
     * Main method that handles the interaction with the user and generates the receipt.
     * 
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        try (Scanner scanner = new Scanner(System.in)) {
            promptForItems(scanner, cart);
            printReceipt(cart.generateReceipt());
        }
    }

    /**
     * Prompts the user to input items, and adds them to the shopping cart. 
     * User can type 'done' to finish entering items.
     *
     * @param scanner the scanner used to read user input
     * @param cart the shopping cart to add items to
     */
    private static void promptForItems(Scanner scanner, ShoppingCart cart) {
        System.out.println("Enter items (or type 'done' to finish):");

        while (true) {
            String input = scanner.nextLine().trim();

            if ("done".equalsIgnoreCase(input)) {
                break;
            }

            if (!input.isEmpty()) {
                cart.addItem(ItemParser.parse(input));
            }
        }
    }

    /**
     * Prints the generated receipt to the console.
     *
     * @param receipt the receipt object containing the details to print
     */
    private static void printReceipt(Receipt receipt) {
        System.out.println(receipt);
    }
}

