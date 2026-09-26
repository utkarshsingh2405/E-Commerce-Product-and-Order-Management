import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Entry point of the application.
 * Provides a simple console menu to manage products, customers, and orders.
 */
public class ECommerceApp {

    private static Scanner scanner = new Scanner(System.in);
    private static Inventory inventory = new Inventory();
    private static OrderManager orderManager = new OrderManager(inventory);
    private static List<Customer> customers = new ArrayList<>();
    private static int nextCustomerId = 1;

    public static void main(String[] args) {
        loadSampleProducts();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    inventory.displayAllProducts();
                    break;
                case 3:
                    registerCustomer();
                    break;
                case 4:
                    placeOrder();
                    break;
                case 5:
                    orderManager.viewAllOrders();
                    break;
                case 6:
                    cancelOrder();
                    break;
                case 7:
                    running = false;
                    System.out.println("Thank you for using the E-Commerce Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n============= E-COMMERCE MANAGEMENT SYSTEM =============");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Register Customer");
        System.out.println("4. Place Order");
        System.out.println("5. View All Orders");
        System.out.println("6. Cancel Order");
        System.out.println("7. Exit");
        System.out.println("==========================================================");
    }

    private static void addProduct() {
        int id = readInt("Enter Product ID: ");
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        double price = readDouble("Enter Price: ");
        int qty = readInt("Enter Quantity: ");

        inventory.addProduct(new Product(id, name, category, price, qty));
    }

    private static void registerCustomer() {
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(nextCustomerId++, name, email, address);
        customers.add(customer);
        System.out.println("Customer registered successfully: " + customer);
    }

    private static void placeOrder() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet. Please register a customer first.");
            return;
        }
        if (inventory.isEmpty()) {
            System.out.println("No products available. Please add products first.");
            return;
        }

        System.out.println("Select customer by ID:");
        for (Customer c : customers) {
            System.out.println(" " + c);
        }
        int customerId = readInt("Customer ID: ");
        Customer selectedCustomer = null;
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                selectedCustomer = c;
                break;
            }
        }
        if (selectedCustomer == null) {
            System.out.println("Invalid customer ID.");
            return;
        }

        inventory.displayAllProducts();
        Map<Integer, Integer> cart = new HashMap<>();
        boolean addingItems = true;
        while (addingItems) {
            int productId = readInt("Enter Product ID to add to order: ");
            int quantity = readInt("Enter Quantity: ");
            cart.put(productId, cart.getOrDefault(productId, 0) + quantity);

            System.out.print("Add another product? (y/n): ");
            String more = scanner.nextLine();
            if (!more.equalsIgnoreCase("y")) {
                addingItems = false;
            }
        }

        try {
            Order order = orderManager.placeOrder(selectedCustomer, cart);
            order.printInvoice();
        } catch (ProductNotFoundException | InsufficientStockException e) {
            System.out.println("Order failed: " + e.getMessage());
        }
    }

    private static void cancelOrder() {
        int orderId = readInt("Enter Order ID to cancel: ");
        try {
            orderManager.cancelOrder(orderId);
        } catch (OrderNotFoundException e) {
            System.out.println("Cancel failed: " + e.getMessage());
        }
    }

    /**
     * Preloads a few sample products so the app is usable immediately.
     */
    private static void loadSampleProducts() {
        inventory.addProduct(new Product(101, "Wireless Mouse", "Electronics", 499.00, 50));
        inventory.addProduct(new Product(102, "Bluetooth Headphones", "Electronics", 1499.00, 30));
        inventory.addProduct(new Product(103, "Cotton T-Shirt", "Clothing", 399.00, 100));
        inventory.addProduct(new Product(104, "Running Shoes", "Footwear", 2499.00, 20));
    }

    // ---------- Input helper methods with basic validation ----------

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid whole number.");
            System.out.print(prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Please enter a valid number.");
            System.out.print(prompt);
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume leftover newline
        return value;
    }
}
