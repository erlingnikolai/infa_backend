package no.infa.repository;

import no.infa.model.User;
import no.infa.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {


    Optional<VerificationToken> findByToken(String token);


    VerificationToken findByUser(User user);
}
