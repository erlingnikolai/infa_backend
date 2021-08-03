package no.routes.login;
import lombok.extern.slf4j.Slf4j;
import no.routes.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 29/07/2021
 */
@RestController
@RequestMapping("/auth/login")
@Slf4j
public class LoginController {


    @Autowired
    private LoginService loginService;

    @PostMapping
    public AuthResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info(loginRequest.getEmail());
        log.info(loginRequest.getPassword());
        AuthResponse response = loginService.login(loginRequest);

        if (isUserAuthenticated()) return response;
        throw new BadRequestException("account not verified by the email thingy");
    }

    private static boolean isUserAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

}