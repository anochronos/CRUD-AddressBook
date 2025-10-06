package ak;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String phone;

    @ManyToOne
    @JsonBackReference
    private AddressBook addressBook;

    public BuddyInfo() {
        this.id = null;
        this.name = null;
        this.phone = null;
    }

    public BuddyInfo(String name, String phone) {
        this.id = null;
        this.name = name;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public AddressBook getAddressBook() { return addressBook; }
    public void setAddressBook(AddressBook addressBook) { this.addressBook = addressBook; }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone;
    }
}
