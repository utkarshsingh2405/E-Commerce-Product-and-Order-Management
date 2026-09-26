/**
 * Custom checked exception thrown when a requested order ID
 * does not exist.
 */
public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
