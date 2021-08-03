package no.routes.login;
import no.TokenProvider;
import no.UserPrincipal;
import no.infa.model.User;
import no.routes.BadRequestException;
import no.routes.registration.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 29/07/2021
 */
@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(getUsernamePasswordToken(loginRequest));
        User user = userService.getUser((UserPrincipal) authentication.getPrincipal());
        if (user.isEnabled()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.createToken(authentication);
            return new AuthResponse(token, user.getUsername());
        }
        throw new BadRequestException("account not verified by the email thingy");
    }

    private static UsernamePasswordAuthenticationToken getUsernamePasswordToken(LoginRequest loginRequest) {
        return new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
