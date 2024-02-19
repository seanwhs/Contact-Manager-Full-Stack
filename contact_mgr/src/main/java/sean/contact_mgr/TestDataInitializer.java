package sean.contact_mgr;
import java.util.Arrays;
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
        List<Contact> contacts = Arrays.asList(
                new Contact("1", "John Doe", "john.doe@email.com", "Software Engineer", "+65 9876 5432", "123 Main St", "Active", null),
                new Contact("2", "Jane Smith", "jane.smith@email.com", "Product Manager", "+60 1234 56789", "456 Elm St", "Active", null),
                new Contact("3", "Michael Johnson", "michael.johnson@email.com", "Sales Representative", "+86 123 4567 8901", "789 Oak St", "Inactive", null),
                new Contact("4", "Emily Davis", "emily.davis@email.com", "Marketing Coordinator", "+886 9876 54321", "101 Maple Ave", "Active", null),
                new Contact("5", "Christopher Lee", "chris.lee@email.com", "Financial Analyst", "+852 1234 5678", "222 Pine St", "Active", null),
                new Contact("6", "Keanu Revves", "keanu.reevs@email.com", "Scrum Master", "+65 8898 7615", "0101 Matrix Rd", "Active", null)
        );

        contactRepository.saveAll(contacts);
    }
}