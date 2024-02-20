//ContactService.java
package sean.contact_mgr.service;
import sean.contact_mgr.model.Contact;
import sean.contact_mgr.repository.ContactRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static sean.contact_mgr.constant.Constant.PHOTO_DIRECTORY;

@Service
@Slf4j //logs to console by default
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public Page<Contact> getAllContacts(int page, int size){
        return contactRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
    }
    
    public Contact getContact(String id){
        return contactRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found!!!"));
    }

    @SuppressWarnings("null")
    public Contact createContact(Contact contact){
        return contactRepository.save(contact);
    }

    @SuppressWarnings("null")
    public Contact deleteContact(String id){
        Contact deletedContact = contactRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + id));
        contactRepository.deleteById(id);
        return deletedContact;
    }
    

    public Contact updateContact(String id, Contact updatedContact) {
        log.info("Updating contact with ID: {}", id);
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact existingContact = optionalContact.get();
            existingContact.setName(updatedContact.getName());
            existingContact.setEmail(updatedContact.getEmail());
            existingContact.setTitle(updatedContact.getTitle());
            existingContact.setPhone(updatedContact.getPhone());
            existingContact.setAddress(updatedContact.getAddress());
            existingContact.setStatus(updatedContact.getStatus());
            // Optionally, you can update other fields here

            return contactRepository.save(existingContact);
        } else {
            throw new RuntimeException("Contact not found with ID: " + id);
        }
    }
    
    public String uploadPhoto(String id, MultipartFile file){
        log.info("Saving picture for user ID: {}", id);
        Contact contact = getContact(id);
        String photoUrl = photoFunction.apply(id, file);
        contact.setPhotoUrl(photoUrl);
        contactRepository.save(contact);
        return photoUrl;
    }

    // Takes a String and receive a string
    private final Function<String, String> fileExtension = filename -> Optional
        .of(filename)
        .filter(name -> name.contains("."))
        .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1))
        .orElse(".png");

    //Takes in String, MultiPart File and retuns a string
    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) { Files.createDirectories(fileStorageLocation); }
            Files
                .copy(
                    image.getInputStream(), 
                    fileStorageLocation.resolve(filename), 
                    REPLACE_EXISTING
                    );
            
            log.info("Picture for user ID: {} saved successfully", id);

            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/contacts/image/" + filename).toUriString();
        } catch (Exception exception) {
            throw new RuntimeException("Unable to save image");
        }
    };
}
