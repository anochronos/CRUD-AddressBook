package ak;

import org.springframework.web.bind.annotation.*;


@RestController
class AddressController {
    private final AddressRepo addressRepo;
    private final BuddyRepo buddyRepo;

    AddressController(AddressRepo addressRepo, BuddyRepo buddyRepo) {
        this.addressRepo = addressRepo;
        this.buddyRepo = buddyRepo;
    }

    @GetMapping("/addressbooks")
    Iterable<AddressBook> all() {
        return addressRepo.findAll();
    }

    @PostMapping("/addressbooks")
    AddressBook newAddress(@RequestBody AddressBook addressBook) {
        System.out.println("POSTING NEW ADDRESS");
        return addressRepo.save(addressBook);
    }


    @GetMapping("/addressbooks/{id}")
    AddressBook one(@PathVariable Integer id) {
        return addressRepo.findById(id).orElseThrow(() -> new AddressBookNotFoundException(id));
    }

    @PostMapping("/addressbooks/{id}/buddies")
    AddressBook addBuddy(@RequestBody BuddyInfo buddyInfo, @PathVariable Integer id) {
        AddressBook book = addressRepo.findById(id).orElseThrow(() -> new AddressBookNotFoundException(id));
        book.addBuddyInfo(buddyInfo);
        buddyRepo.save(buddyInfo);
        return addressRepo.save(book);
    }


    @DeleteMapping("/addressbooks/{bookid}/buddies/{buddyid}")
    AddressBook removeBuddy(@PathVariable Integer bookid, @PathVariable Integer buddyid) {
        AddressBook book = addressRepo.findById(bookid).orElseThrow(() -> new AddressBookNotFoundException(bookid));
        BuddyInfo buddy = buddyRepo.findById(buddyid).orElseThrow(() -> new RuntimeException("Buddy not found"));

        if (!buddy.getAddressBook().getId().equals(book.getId())) {
            throw new RuntimeException("Buddy does not belong to this AddressBook");
        }

        book.removeBuddyInfo(buddy);
        buddyRepo.delete(buddy);
        return addressRepo.save(book);
    }




}
