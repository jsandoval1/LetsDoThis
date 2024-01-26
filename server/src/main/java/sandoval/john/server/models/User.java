package sandoval.john.server.models;

// Importing JPA annotations
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

// Importing validation annotations
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Importing Lombok annotations
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

// Importing other classes
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

// Lombok annotations for getter, setter and no-args constructor
@Getter
@Setter
@NoArgsConstructor

// JPA annotations for entity and table
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    @Transient
    @NotBlank(message = "Confirm password is required")
    @Size(min = 8, max = 100, message = "Confirm password must be between 8 and 100 characters")
    private String confirm;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() { 
        createdAt = new Date();
    }

    protected void onUpdate() {
        updatedAt = new Date();
    }

}
