package ak;

public class AddressBookNotFoundException extends RuntimeException {
    AddressBookNotFoundException(Integer id) {
        super("Could not find address book with id " + id);
    }
}
