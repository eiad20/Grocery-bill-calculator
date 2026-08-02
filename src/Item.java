import java.math.BigDecimal;

/**
 * Represents a single grocery item available in the store.
 * Encapsulates the item's name and price instead of storing them
 * in separate parallel arrays.
 */
public class Item {
    private final String name;
    private final BigDecimal price;

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%-10s - $%.2f", name, price);
    }
}
