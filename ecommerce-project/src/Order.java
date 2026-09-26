import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer order containing multiple OrderItems.
 */
public class Order {

    // Static counter shared by all Order objects to auto-generate unique IDs.
    private static int orderCounter = 1000;

    private int orderId;
    private Customer customer;
    private List<OrderItem> items;
    private String orderDate;
    private String status; // PLACED, CANCELLED, DELIVERED

    public Order(Customer customer) {
        this.orderId = ++orderCounter;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.status = "PLACED";
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    /**
     * Prints a formatted invoice for this order to the console.
     */
    public void printInvoice() {
        System.out.println("--------------------------------------------------");
        System.out.println("Order ID     : " + orderId);
        System.out.println("Date         : " + orderDate);
        System.out.println("Customer     : " + customer.getName() + " (" + customer.getEmail() + ")");
        System.out.println("Status       : " + status);
        System.out.println("Items:");
        for (OrderItem item : items) {
            System.out.println("   " + item);
        }
        System.out.printf("Total Amount : Rs.%.2f%n", getTotalAmount());
        System.out.println("--------------------------------------------------");
    }
}
