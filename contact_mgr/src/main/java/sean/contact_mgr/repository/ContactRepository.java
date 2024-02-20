//ContactRepository.java
package sean.contact_mgr.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sean.contact_mgr.model.Contact;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository <Contact, String> {
    @SuppressWarnings("null")
    Optional <Contact>  findById(String id);
}
