import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Manages the lifecycle of orders: placing, cancelling, and viewing.
 * Also demonstrates basic file I/O by logging each order to a text file.
 */
public class OrderManager {

    private List<Order> orders = new ArrayList<>();
    private Inventory inventory;
    private static final String LOG_FILE = "orders_log.txt";

    public OrderManager(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Places a new order for a customer.
     * productQuantities maps productId -> quantity requested.
     */
    public Order placeOrder(Customer customer, Map<Integer, Integer> productQuantities)
            throws ProductNotFoundException, InsufficientStockException {

        Order order = new Order(customer);

        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();

            Product product = inventory.getProduct(productId); // may throw ProductNotFoundException
            product.reduceStock(quantity);                     // may throw InsufficientStockException

            order.addItem(new OrderItem(product, quantity));
        }

        orders.add(order);
        logOrderToFile(order);
        System.out.println("Order placed successfully! Order ID: " + order.getOrderId());
        return order;
    }

    /**
     * Cancels an existing order and restocks its items.
     */
    public void cancelOrder(int orderId) throws OrderNotFoundException {
        Order order = findOrderById(orderId);

        if (order.getStatus().equals("CANCELLED")) {
            System.out.println("Order " + orderId + " is already cancelled.");
            return;
        }

        for (OrderItem item : order.getItems()) {
            item.getProduct().increaseStock(item.getQuantity());
        }
        order.setStatus("CANCELLED");
        System.out.println("Order " + orderId + " has been cancelled and stock restored.");
    }

    public Order findOrderById(int orderId) throws OrderNotFoundException {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        throw new OrderNotFoundException("No order found with ID: " + orderId);
    }

    public void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders have been placed yet.");
            return;
        }
        for (Order order : orders) {
            order.printInvoice();
        }
    }

    /**
     * Appends a simple text record of the order to a log file (basic file I/O).
     */
    private void logOrderToFile(Order order) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("Order ID: " + order.getOrderId()
                    + " | Customer: " + order.getCustomer().getName()
                    + " | Date: " + order.getOrderDate()
                    + " | Total: Rs." + String.format("%.2f", order.getTotalAmount())
                    + " | Status: " + order.getStatus() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Warning: could not write order to log file. " + e.getMessage());
        }
    }
}
