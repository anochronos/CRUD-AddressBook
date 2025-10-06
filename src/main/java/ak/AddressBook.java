package ak;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BuddyInfo> buddyInfos = new ArrayList<>();

    public AddressBook() {}


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addBuddyInfo(BuddyInfo buddyInfo) {
        this.buddyInfos.add(buddyInfo);
        buddyInfo.setAddressBook(this);
    }

    public void removeBuddyInfo(BuddyInfo buddyInfo) {
        buddyInfo.setAddressBook(null);
        buddyInfos.remove(buddyInfo);
    }

    @Override
    public String toString() {
        for (BuddyInfo buddy: buddyInfos){
            return "AddressBook{id=" + id + buddy.toString();
        }
        return "";
    }


    public List<BuddyInfo> getBuddyInfos() {
        return this.buddyInfos;
    }

    public void setBuddyInfos(List<BuddyInfo> buddyInfos) {
        this.buddyInfos = buddyInfos;
    }

}
