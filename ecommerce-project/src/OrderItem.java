/**
 * Represents a single line item within an order (a product + quantity ordered).
 */
public class OrderItem {

    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-20s x%-3d = Rs.%.2f", product.getProductName(), quantity, getSubtotal());
    }
}
