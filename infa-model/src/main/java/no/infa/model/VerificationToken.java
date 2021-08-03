package no.infa.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Entity
@NoArgsConstructor
@Data
@Table(name = "verification_tokens", catalog = "authentication")
public class VerificationToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private LocalDateTime expiryDate;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate();
    }


    private static LocalDateTime calculateExpiryDate() {
        return LocalDateTime.now().plusDays(1L);
    }

    public boolean isExpired() {
        int compareValue = expiryDate.compareTo(LocalDateTime.now());
        return compareValue < 0;
    }
}
