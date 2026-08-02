import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents one customer's shopping order: the items they've added
 * and the running total. Replaces the old loose "selectedIndices list
 * + totalBill float" pair from the original procedural version.
 */
public class Order {
    private final List<Item> purchasedItems = new ArrayList<>();

    public void addItem(Item item) {
        purchasedItems.add(item);
    }

    public int getItemCount() {
        return purchasedItems.size();
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : purchasedItems) {
            total = total.add(item.getPrice());
        }
        return total;
    }

    /**
     * Prints an itemized receipt followed by the total due.
     */
    public void printReceipt() {
        System.out.println("\n===== Receipt =====");
        if (purchasedItems.isEmpty()) {
            System.out.println("No items purchased.");
        } else {
            for (Item item : purchasedItems) {
                System.out.println(item);
            }
        }
        System.out.printf("--------------------%n");
        System.out.printf("Total items: %d%n", getItemCount());
        System.out.printf("Total bill:  $%.2f%n", getTotal());
    }
}
