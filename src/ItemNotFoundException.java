/**
 * Thrown when a customer requests an item that does not exist in the store.
 */
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
