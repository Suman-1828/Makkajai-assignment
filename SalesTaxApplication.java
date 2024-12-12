import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main application class
public class SalesTaxApplication {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter items (or type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            cart.addItem(ItemParser.parse(input));
        }

        Receipt receipt = cart.generateReceipt();
        System.out.println(receipt);
    }
}

// Class to represent an item
class Item {
    private final String name;
    private final BigDecimal price;
    private final boolean isExempt;
    private final boolean isImported;

    public Item(String name, BigDecimal price, boolean isExempt, boolean isImported) {
        this.name = name;
        this.price = price;
        this.isExempt = isExempt;
        this.isImported = isImported;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isExempt() {
        return isExempt;
    }

    public boolean isImported() {
        return isImported;
    }

    public BigDecimal calculateTax() {
        BigDecimal tax = BigDecimal.ZERO;
        if (!isExempt) {
            tax = tax.add(price.multiply(BigDecimal.valueOf(0.10)));
        }
        if (isImported) {
            tax = tax.add(price.multiply(BigDecimal.valueOf(0.05)));
        }
        return roundToNearestFiveCents(tax);
    }

    private BigDecimal roundToNearestFiveCents(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.UP).multiply(BigDecimal.valueOf(20)).setScale(0, RoundingMode.UP).divide(BigDecimal.valueOf(20), 2, RoundingMode.UP);
    }
}

// Class to parse input into Item objects
class ItemParser {
    public static Item parse(String input) {
        String[] parts = input.split(" at ");
        String namePart = parts[0];
        BigDecimal price = new BigDecimal(parts[1]);

        String[] nameParts = namePart.split(" ", 2);
        String name = nameParts[1];

        boolean isExempt = name.contains("book") || name.contains("chocolate") || name.contains("pill");
        boolean isImported = name.contains("imported");

        return new Item(name.trim(), price, isExempt, isImported);
    }
}

// Class to represent the shopping cart
class ShoppingCart {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public Receipt generateReceipt() {
        List<String> receiptLines = new ArrayList<>();
        BigDecimal totalTaxes = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Item item : items) {
            BigDecimal tax = item.calculateTax();
            BigDecimal finalPrice = item.getPrice().add(tax);

            receiptLines.add(String.format("1 %s: %.2f", item.getName(), finalPrice));
            totalTaxes = totalTaxes.add(tax);
            totalPrice = totalPrice.add(finalPrice);
        }

        return new Receipt(receiptLines, totalTaxes, totalPrice);
    }
}

// Class to represent the receipt
class Receipt {
    private final List<String> lines;
    private final BigDecimal totalTaxes;
    private final BigDecimal totalPrice;

    public Receipt(List<String> lines, BigDecimal totalTaxes, BigDecimal totalPrice) {
        this.lines = lines;
        this.totalTaxes = totalTaxes;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line).append("\n");
        }
        sb.append(String.format("Sales Taxes: %.2f\n", totalTaxes));
        sb.append(String.format("Total: %.2f", totalPrice));
        return sb.toString();
    }
}
