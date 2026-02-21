import java.io.*;
import java.util.*;

public class InventoryManager {

    private ArrayList<Product> products = new ArrayList<>();
    private final String filename = "inventory.txt";
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        InventoryManager ims = new InventoryManager();
        ims.loadFromFile();
        ims.menu();
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Search Product");
            System.out.println("4. Total Stock Value");
            System.out.println("5. Save & Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    totalStockValue();
                    break;
                case 5:
                    saveToFile();
                    System.out.println("Data Saved. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    public void addProduct() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Product p : products) {
            if (p.getId() == id) {
                System.out.println("Duplicate ID not allowed!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        products.add(new Product(id, name, qty, price));
        System.out.println("Product Added Successfully!");
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (Product p : products) {
            p.display();
        }
    }

    public void searchProduct() {
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt();

        for (Product p : products) {
            if (p.getId() == id) {
                p.display();
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void totalStockValue() {
        double total = 0;
        for (Product p : products) {
            total += p.getStockValue();
        }
        System.out.println("Total Stock Value: " + total);
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Product p : products) {
                bw.write(p.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public void loadFromFile() {
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                products.add(new Product(
                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2]),
                        Double.parseDouble(data[3])
                ));
            }
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }
}