/**
 * Custom checked exception thrown when a requested product ID
 * does not exist in the inventory.
 */
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
