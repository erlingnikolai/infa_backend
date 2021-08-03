package no.infa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "users", catalog = "authentication")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedDate;

    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "ENUM('UNVERIFIED', 'ENABLED', 'DISABLED')")
    @Enumerated(EnumType.STRING)
    private UserState userState;

    public User(String username, @Email String email, String password, UserState userState) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userState = userState;
    }

    public boolean isEnabled() {
        return userState == UserState.ENABLED;
    }
}
