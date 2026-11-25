import java.util.Scanner;

public class GroceryBillCalculator {

    public static void main(String[] args) {
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Welcome to the Grocery Store =====");
        store.printCatalog();

        while (true) {
            Order order = new Order();

            System.out.println("\nStarting a new shopping list.");
            System.out.println("Type an item name to add it, or type 'Finish' to complete this order.\n");

            while (true) {
                System.out.print("Enter item: ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("Finish")) {
                    break;
                }

                try {
                    Item item = store.findItem(input);
                    order.addItem(item);
                    System.out.println("Added: " + item);
                } catch (ItemNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            order.printReceipt();

            System.out.print("\nType 'Exit' to leave the store, or press Enter to shop again: ");
            String continueInput = scanner.nextLine().trim();

            if (continueInput.equalsIgnoreCase("Exit")) {
                System.out.println("\nThank you for shopping with us. Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}
