package ak;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AddressBookViewController {

    private final AddressRepo addressRepo;

    public AddressBookViewController(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @GetMapping("/view/addressbooks/{id}")
    public String viewAddressBook(@PathVariable Integer id, Model model) {
        AddressBook book = addressRepo.findById(id)
                .orElseThrow(() -> new AddressBookNotFoundException(id));

        model.addAttribute("addressBook", book);
        model.addAttribute("buddies", book.getBuddyInfos());

        return "addressbook";
    }
}
