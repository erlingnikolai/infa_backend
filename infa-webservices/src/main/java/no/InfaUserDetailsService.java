package no;

import lombok.extern.slf4j.Slf4j;
import no.infa.repository.UserRepository;
import no.routes.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import no.infa.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Service
@Slf4j
public class InfaUserDetailsService implements UserDetailsService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("you have logged in with to many wrong attempts chill m8");
        }
        User user = findByEmail(email);
        return UserPrincipal.create(user);
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) return request.getRemoteAddr();
        return xfHeader.split(",")[0];
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = foundById(id);
        return UserPrincipal.create(user);
    }


    private User foundById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
    }
}
