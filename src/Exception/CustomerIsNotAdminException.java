package Exception;

public class CustomerIsNotAdminException extends Exception {
    public CustomerIsNotAdminException(String message) {
        super(message);
    }
}
