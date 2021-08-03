package no.routes.registration;

import no.CurrentUser;
import no.UserPrincipal;
import no.infa.model.User;
import no.infa.model.UserState;
import no.infa.repository.UserRepository;
import no.infa.repository.VerificationTokenRepository;
import no.routes.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }


    public User getUser(UserPrincipal principal) {
        return getUserById(principal);
    }

    public User getUserById(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    public User save(SignupRequest signUpRequest) {
        String name = signUpRequest.getUsername();
        String email = signUpRequest.getEmail();
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        User entity = new User(name, email, password, UserState.UNVERIFIED);
        return userRepository.save(entity);
    }

    /**
     * Better Error dealing maybe?
     * What if a method calls this and does not expect the error something not a webController
     */
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }


}
