package no.routes.registration;

import lombok.extern.slf4j.Slf4j;
import no.infa.model.User;
import no.routes.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 27/07/2021
 */
@Slf4j
@RestController
@RequestMapping("/auth/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;


    @PostMapping("/signup")
    public SignupResponse registerUser(@Valid @RequestBody final SignupRequest signUpRequest) {
        User user = registrationService.registerUser(signUpRequest);
        return new SignupResponse(user.getUsername());
    }

    @PostMapping("/verifyaccount")
    public SignupVerifyResponse signupVerifyResponse(@Valid @RequestBody final VerifyAccountRequest VerifyAccountRequest) {
        User user = registrationService.verifyAccount(VerifyAccountRequest.getToken());
        return new SignupVerifyResponse();
    }


    @GetMapping("/signuptest")
    public SignupResponse registerUser2() {
        if (true) {
            throw new BadRequestException("Email address already in use");
        }
        return new SignupResponse("gikk bra");
    }
}
