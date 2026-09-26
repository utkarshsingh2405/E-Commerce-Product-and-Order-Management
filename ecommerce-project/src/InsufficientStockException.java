/**
 * Custom checked exception thrown when an order requests more
 * quantity of a product than is currently available in stock.
 */
public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}
