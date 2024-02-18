//Contact.java
package sean.contact_mgr.model;
import org.hibernate.annotations.UuidGenerator;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Contact {

    @Id
    @UuidGenerator
    @Column(name="id", unique = true, updatable = false)
    private String id;
    private String name;
    private String email;
    private String title;
    private String phone; 
    private String address;
    private String status;
    private String photoUrl;

}
