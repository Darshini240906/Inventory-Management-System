
import java.io.*;
import java.util.*;

//Creating a Product Class
class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;

    // Constructor to initialize values
    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }



    //gets ID of the product
    public int getId() {
        return id;
    }
    //Calculates value of the stock using quantity and price
    public double getStockValue() {
        return quantity * price;
    }

    public String toFileString() {
        return id + "," + name + "," + quantity + "," + price;
    }



    public void display() {
        System.out.println("--------------------------------");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: " + price);
        System.out.println("Stock Value: " + getStockValue());
    }
}

// Main Class
public class InventoryManagementSystem {

    static ArrayList<Product> products = new ArrayList<>();
    static final String FILE_NAME = "products.txt";

    // Add Products to the array
    public static void addProduct(Scanner sc) {
        try {
            System.out.print("Enter Product ID: ");
            int id = sc.nextInt();
            sc.nextLine();


            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();

            System.out.print("Enter Price: ");
            double price = sc.nextDouble();

            Product p = new Product(id, name, quantity, price);
            products.add(p);

            System.out.println("Product added successfully.");

        } catch (Exception e) {

            System.out.println("Invalid input. Try again.");
            sc.nextLine();
        }
    }

    // Display Products present in the array
    public static void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;

        }

        for (Product p : products) {
            p.display();
        }
    }

    // Search for Product using ProductID
    public static void searchProduct(Scanner sc) {
        
        System.out.print("Enter Product ID to Search: ");
        int id = sc.nextInt();

        for (Product p : products) {
            if (p.getId() == id) {
                System.out.println("Product found:");
                p.display();
                return;
            }
        }

        System.out.println("Product not found in the list.");
    }

    // Calculate Total Stock Value of all the entered items
    public static void calculateTotalValue() {
        double total = 0;

        for (Product p : products) {
            total += p.getStockValue();
        }


        System.out.println("Total Stock Value: " + total);
    }

    // Save to File
    public static void saveToFile() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));

            for (Product p : products) {
                writer.write(p.toFileString());
                writer.newLine();
            }

            writer.close();
            System.out.println("Products saved to file successfully.");

        } catch (IOException e) {
            System.out.println("Error saving file.Try again.");
        }
    }

    // Load from File
    // To get values of previously entered Products from the file products.txt
    public static void loadFromFile() {
        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];

                int quantity = Integer.parseInt(data[2]);
                double price = Double.parseDouble(data[3]);

                Product p = new Product(id, name, quantity, price);
                products.add(p);
            }

            reader.close();
            System.out.println("Products loaded from file successfully.");

        } catch (IOException e) {
            System.out.println("Error loading file.Try again.");
        }
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Load existing products when program starts, only if the file exists
        loadFromFile();

        while (true) {
            System.out.println("\n===== Inventory Management System =====");
            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Search Product");
            System.out.println("4. Calculate Total Stock Value");
            System.out.println("5. Save Products to File");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addProduct(sc);
                    break;


                case 2:
                    displayProducts();
                    break;

                case 3:
                    searchProduct(sc);
                    break;

                case 4:
                    calculateTotalValue();
                    break;


                case 5:
                    saveToFile();
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;


                    
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
