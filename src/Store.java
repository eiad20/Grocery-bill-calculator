import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the grocery store's catalog of available items.
 * Owns the item list and the logic for looking items up by name,
 * replacing the old static array + index-based search.
 */
public class Store {
    private final List<Item> catalog;

    public Store() {
        catalog = new ArrayList<>();
        seedCatalog();
    }

    private void seedCatalog() {
        catalog.add(new Item("Milk", new BigDecimal("3.50")));
        catalog.add(new Item("Bread", new BigDecimal("2.75")));
        catalog.add(new Item("Eggs", new BigDecimal("4.20")));
        catalog.add(new Item("Butter", new BigDecimal("5.10")));
        catalog.add(new Item("Cheese", new BigDecimal("6.30")));
        catalog.add(new Item("Rice", new BigDecimal("8.00")));
        catalog.add(new Item("Sugar", new BigDecimal("2.20")));
        catalog.add(new Item("Salt", new BigDecimal("1.50")));
        catalog.add(new Item("Coffee", new BigDecimal("9.99")));
        catalog.add(new Item("Tea", new BigDecimal("7.25")));
        catalog.add(new Item("Apples", new BigDecimal("3.10")));
        catalog.add(new Item("Bananas", new BigDecimal("1.80")));
    }

    public List<Item> getCatalog() {
        return catalog;
    }

    /**
     * Finds an item by name (case-insensitive).
     * Throws ItemNotFoundException if no match exists in the catalog.
     */
    public Item findItem(String name) throws ItemNotFoundException {
        for (Item item : catalog) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        throw new ItemNotFoundException("'" + name + "' is not available in this store.");
    }

    public void printCatalog() {
        System.out.println("\nAvailable items:");
        for (Item item : catalog) {
            System.out.println(item);
        }
    }
}
