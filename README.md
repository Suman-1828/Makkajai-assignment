# Sales Tax Receipt Application

## Overview
This is a Java console-based application to calculate and display a receipt for items purchased. The receipt includes the price (including tax) of each item, total sales taxes, and the total cost. The application supports:
- Basic sales tax of 10% (applied to non-exempt items).
- Import duty of 5% (applied to all imported items).
- Exemptions for books, food, and medical products.

## Features
- **Tax Calculation**: Automatically calculates applicable sales taxes and rounds them to the nearest 0.05.
- **Modular Design**: Built using object-oriented principles for maintainability and extensibility.
- **Console Interface**: Accepts user input for items and outputs the formatted receipt.

## Rounding Rules
For a tax rate of "n%" and a shelf price of "p", the sales tax is "(n * p / 100)" rounded up to the nearest 0.05.

## Input Format
Enter items in the format:  

<quantity> <item name> at <price>

Type "done" to finish entering items.

### Examples:
1. 1 book at 12.49
2. 1 imported box of chocolates at 10.00
3. 1 music CD at 14.99

## Sample Usage
### Input:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85
done

### Output:
1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

## Classes
### 1. "Item"
Represents an individual product with properties:
- name
- price
- isExempt
- isImported
Includes methods to calculate taxes and final prices.

### 2. "ItemParser"
Parses user input strings into Item objects.

### 3. "ShoppingCart"
Manages a list of "Item" objects and generates a "Receipt".

### 4. "Receipt"
Formats and holds the receipt details, including total taxes and price.

## How to Run
1. Compile the application:
   
   javac SalesTaxApplication.java
   
2. Run the application:
   
   java SalesTaxApplication
   
3. Enter items following the format above and type "done" to view the receipt.

## Sample Inputs and Outputs
Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85
done

Output:
1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

Input 2:
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50
done

Output:
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15

Input 3:
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25
done

Output:
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68


## Future Enhancements
- Add support for multiple quantities of the same item.
- Allow file-based input and output.
- Add support for configurable tax rates and exemption categories.

## License
This project is open-source and available under the [MIT License](LICENSE).
