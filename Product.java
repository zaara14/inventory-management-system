public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;

    // Constructor
    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getStockValue() {
        return quantity * price;
    }

    public String toFileString() {
        return id + "," + name + "," + quantity + "," + price;
    }

    public void display() {
        System.out.println("ID: " + id +
                " | Name: " + name +
                " | Quantity: " + quantity +
                " | Price: " + price);
    }
}
