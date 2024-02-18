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
@Slf4j
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
    public void deleteContact(String id){
        contactRepository.deleteById(id);
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
        try{
            String filename = id + fileExtension.apply(image.getOriginalFilename());
            //if directory does not exist, create it
            Path fileStorageLocation=Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            
            if (!Files.exists(fileStorageLocation)){
                Files.createDirectories(fileStorageLocation);
            }
            //save file
            Files.copy(
                image.getInputStream(), 
                fileStorageLocation.resolve(id + fileExtension.apply(image.getOriginalFilename())), 
                REPLACE_EXISTING);
            //return url
            log.info("Saved picture for user ID: {} successfully", id);
            return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/contacts/image/" + filename)
                .toString();
        } catch (Exception exception){
            throw new RuntimeException("Unable to save image");
        }
    };
}
