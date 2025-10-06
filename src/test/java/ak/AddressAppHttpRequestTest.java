package ak;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressAppHttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createAddressBook(){
        ResponseEntity<AddressBook> bookResponseEntity = restTemplate.postForEntity("http://localhost:"+port+"/addressbooks", new AddressBook(), AddressBook.class);
        assertThat(bookResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(bookResponseEntity.getBody()).isNotNull();
    }

    @Test
    public void addAndGetBuddyInfo() {
        ResponseEntity<AddressBook> bookResponseEntity = restTemplate.postForEntity("http://localhost:" + port + "/addressbooks", new AddressBook(), AddressBook.class);
        assertThat(bookResponseEntity.getBody()).isNotNull();

        int bookId = bookResponseEntity.getBody().getId();

        BuddyInfo buddyInfo = new BuddyInfo("Akshun", "112233");
        ResponseEntity<BuddyInfo> buddyInfoResponseEntity = restTemplate.postForEntity("http://localhost:" + port + "/addressbooks/" + bookId + "/buddies", buddyInfo, BuddyInfo.class);
        assertThat(buddyInfoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(buddyInfoResponseEntity.getBody()).isNotNull();


        ResponseEntity<BuddyInfo[]> buddiesResponse = restTemplate.getForEntity("http://localhost:" + port + "/addressbooks/" + bookId + "/buddies", BuddyInfo[].class);
        assertThat(buddiesResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(buddiesResponse.getBody()).isNotNull();
        assertThat(buddiesResponse.getBody().length).isEqualTo(1);
    }
}
