import java.util.HashMap;
import java.util.Map;

/**
 * Manages the collection of products in the store.
 * Uses a HashMap for fast lookup by product ID.
 */
public class Inventory {

    private Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
        System.out.println("Product added: " + product.getProductName());
    }

    public Product getProduct(int productId) throws ProductNotFoundException {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("No product found with ID: " + productId);
        }
        return product;
    }

    public void removeProduct(int productId) throws ProductNotFoundException {
        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Cannot remove. No product found with ID: " + productId);
        }
        Product removed = products.remove(productId);
        System.out.println("Product removed: " + removed.getProductName());
    }

    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available in inventory.");
            return;
        }
        System.out.println("---------------- PRODUCT CATALOG ----------------");
        for (Product p : products.values()) {
            System.out.println(p);
        }
        System.out.println("--------------------------------------------------");
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
