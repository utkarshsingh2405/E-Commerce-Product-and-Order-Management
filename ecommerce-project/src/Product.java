/**
 * Represents a product available in the e-commerce store.
 * Demonstrates encapsulation: fields are private, accessed via getters/setters.
 */
public class Product {

    private int productId;
    private String productName;
    private String category;
    private double price;
    private int quantityAvailable;

    public Product(int productId, String productName, String category, double price, int quantityAvailable) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    // ---------- Getters ----------
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    // ---------- Setters ----------
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Reduces stock when an order is placed.
     * Throws a checked exception if there is not enough stock.
     */
    public void reduceStock(int quantity) throws InsufficientStockException {
        if (quantity > quantityAvailable) {
            throw new InsufficientStockException(
                    "Not enough stock for '" + productName + "'. Available: " + quantityAvailable
                            + ", Requested: " + quantity);
        }
        quantityAvailable -= quantity;
    }

    /**
     * Restocks a product (used for new stock or order cancellation).
     */
    public void increaseStock(int quantity) {
        quantityAvailable += quantity;
    }

    @Override
    public String toString() {
        return String.format("ID: %-4d | %-20s | Category: %-12s | Price: Rs.%-8.2f | Stock: %d",
                productId, productName, category, price, quantityAvailable);
    }
}
