//TestDataInitializer.java
package sean.contact_mgr;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sean.contact_mgr.model.Contact;
import sean.contact_mgr.repository.ContactRepository;

@Component
public class TestDataInitializer implements CommandLineRunner {

    private final ContactRepository contactRepository;

    public TestDataInitializer(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @SuppressWarnings("null")
    @Override
    public void run(String... args) throws Exception {
        List<Contact> contacts = generateTestData(50);
        contactRepository.saveAll(contacts);
    }

    private List<Contact> generateTestData(int count) {
        List<Contact> testData = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String id = String.valueOf(i);
            String name = "Test User " + i;
            String email = "user" + i + "@example.com";
            String jobTitle = "Test Job " + i;
            String phone = "+65 1234 5678"; // Replace with your desired phone format or generate dynamically
            String address = "Test Address " + i;
            String status = i % 2 == 0 ? "Active" : "Inactive"; // Alternate between Active and Inactive

            testData.add(new Contact(id, name, email, jobTitle, phone, address, status, null));
        }
        return testData;
    }
}
