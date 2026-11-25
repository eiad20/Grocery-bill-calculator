import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroceryBillCalculatorTest {

    // -------------------------------------------------------------------------
    // Item Tests
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Item")
     class ItemTests {

        @Test
        @DisplayName("stores name and price accurately")
        void createsItemWithNameAndPrice() {
            Item item = new Item("Milk", new BigDecimal("3.50"));

            assertEquals("Milk", item.getName());
            assertEquals(0, new BigDecimal("3.50").compareTo(item.getPrice()));
        }

        @Test
        @DisplayName("formats toString() with padded name and two decimal places")
        void formatsToStringCorrectly() {
            Item item = new Item("Coffee", new BigDecimal("9.99"));

            assertEquals("Coffee     - $9.99", item.toString());
        }
    }

    // -------------------------------------------------------------------------
    // Store Tests
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Store")
    class StoreTests {

        private Store store;

        @BeforeEach
        void setUp() {
            store = new Store();
        }

        @Test
        @DisplayName("seeds catalog with 12 default items")
        void seedsDefaultCatalog() {
            List<Item> catalog = store.getCatalog();

            assertNotNull(catalog);
            assertEquals(12, catalog.size());
        }

        @Test
        @DisplayName("finds an existing item by exact name")
        void findsItemExactMatch() throws ItemNotFoundException {
            Item item = store.findItem("Bread");

            assertEquals("Bread", item.getName());
            assertEquals(0, new BigDecimal("2.75").compareTo(item.getPrice()));
        }

        @Test
        @DisplayName("finds an existing item regardless of case")
        void findsItemCaseInsensitive() throws ItemNotFoundException {
            Item lower = store.findItem("milk");
            Item upper = store.findItem("EGGS");
            Item mixed = store.findItem("bAnAnAs");

            assertEquals("Milk", lower.getName());
            assertEquals("Eggs", upper.getName());
            assertEquals("Bananas", mixed.getName());
        }

        @Test
        @DisplayName("throws ItemNotFoundException for unavailable items")
        void throwsExceptionWhenItemNotFound() {
            ItemNotFoundException exception = assertThrows(
                    ItemNotFoundException.class,
                    () -> store.findItem("Chocolate")
            );

            assertEquals("'Chocolate' is not available in this store.", exception.getMessage());
        }

        @Test
        @DisplayName("throws ItemNotFoundException for empty input")
        void throwsExceptionForEmptyInput() {
            assertThrows(ItemNotFoundException.class, () -> store.findItem(""));
        }
    }

    // -------------------------------------------------------------------------
    // Order Tests
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Order")
    class OrderTests {

        private Order order;
        private final PrintStream originalOut = System.out;
        private ByteArrayOutputStream outputStream;

        @BeforeEach
        void setUp() {
            order = new Order();
            outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
        }

        @AfterEach
        void tearDown() {
            System.setOut(originalOut);
        }

        @Test
        @DisplayName("starts empty with zero total")
        void startsEmpty() {
            assertEquals(0, order.getItemCount());
            assertEquals(0, BigDecimal.ZERO.compareTo(order.getTotal()));
        }

        @Test
        @DisplayName("calculates item count and total accurately for multiple items")
        void addsMultipleItemsAndSumsTotal() {
            order.addItem(new Item("Milk", new BigDecimal("3.50")));
            order.addItem(new Item("Coffee", new BigDecimal("9.99")));
            order.addItem(new Item("Salt", new BigDecimal("1.50")));

            assertEquals(3, order.getItemCount());
            assertEquals(0, new BigDecimal("14.99").compareTo(order.getTotal()));
        }

        @Test
        @DisplayName("allows adding duplicate items")
        void allowsDuplicateItems() {
            Item apples = new Item("Apples", new BigDecimal("3.10"));
            order.addItem(apples);
            order.addItem(apples);

            assertEquals(2, order.getItemCount());
            assertEquals(0, new BigDecimal("6.20").compareTo(order.getTotal()));
        }

        @Test
        @DisplayName("prints empty receipt message when no items were added")
        void printsEmptyReceipt() {
            order.printReceipt();
            String output = outputStream.toString();

            assertTrue(output.contains("No items purchased."));
            assertTrue(output.contains("Total items: 0"));
            assertTrue(output.contains("Total bill:  $0.00"));
        }

        @Test
        @DisplayName("prints itemized receipt with total for populated order")
        void printsPopulatedReceipt() {
            order.addItem(new Item("Butter", new BigDecimal("5.10")));
            order.addItem(new Item("Sugar", new BigDecimal("2.20")));

            order.printReceipt();
            String output = outputStream.toString();

            assertTrue(output.contains("Butter     - $5.10"));
            assertTrue(output.contains("Sugar      - $2.20"));
            assertTrue(output.contains("Total items: 2"));
            assertTrue(output.contains("Total bill:  $7.30"));
        }
    }
}