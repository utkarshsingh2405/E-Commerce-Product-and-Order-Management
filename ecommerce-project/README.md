# E-Commerce Product & Order Management System (Java)

A simple **console-based** Java application for managing products, customers,
and orders in an e-commerce style system. Built using only **core Java (Java SE)**
fundamentals — no frameworks, no database, no external libraries.

## Concepts Demonstrated

- **Classes & Objects** — `Product`, `Customer`, `Order`, `OrderItem`
- **Encapsulation** — private fields with public getters/setters
- **Collections** — `HashMap` (inventory lookup), `ArrayList` (orders, customers)
- **Exception Handling** — custom checked exceptions:
  - `ProductNotFoundException`
  - `InsufficientStockException`
  - `OrderNotFoundException`
- **File I/O** — every placed order is appended to `orders_log.txt` using `FileWriter`
- **Static members** — `Order` uses a static counter to auto-generate unique order IDs
- **Basic date/time handling** — `LocalDateTime` for order timestamps
- **User input handling** — `Scanner` with input validation in a menu-driven loop

## Project Structure

```
ecommerce-project/
└── src/
    ├── Product.java                    # Product entity
    ├── Customer.java                   # Customer entity
    ├── OrderItem.java                  # A single line item in an order
    ├── Order.java                      # An order (list of OrderItems)
    ├── Inventory.java                  # Manages all products
    ├── OrderManager.java               # Places / cancels / lists orders
    ├── ProductNotFoundException.java   # Custom exception
    ├── InsufficientStockException.java # Custom exception
    ├── OrderNotFoundException.java     # Custom exception
    └── ECommerceApp.java               # Main class (console menu)
```

## How to Run

1. Make sure you have **Java JDK 8+** installed:
   ```
   java -version
   javac -version
   ```

2. Clone this repository and navigate to the `src` folder:
   ```
   cd ecommerce-project/src
   ```

3. Compile all files:
   ```
   javac *.java
   ```

4. Run the application:
   ```
   java ECommerceApp
   ```

## Sample Menu

```
============= E-COMMERCE MANAGEMENT SYSTEM =============
1. Add Product
2. View All Products
3. Register Customer
4. Place Order
5. View All Orders
6. Cancel Order
7. Exit
==========================================================
```

The app preloads a few sample products so you can place an order right away.

## Notes

- Data is stored **in-memory only** (no database) — it resets when the program stops.
  Order history is additionally logged to `orders_log.txt` for reference.
- This project is intentionally kept beginner-friendly, using only fundamental
  Java concepts, so it's easy to read, extend, and explain.

## Possible Extensions

- Add inheritance (e.g., `Electronics extends Product`, `Clothing extends Product`)
- Persist data using file serialization or a simple database (JDBC)
- Add a search/filter feature for products
- Build a GUI using Java Swing
