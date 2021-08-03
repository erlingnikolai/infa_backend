package no.routes.registration;

import lombok.extern.slf4j.Slf4j;
import no.infa.mail.EmailService;
import no.infa.model.User;
import no.infa.model.UserState;
import no.infa.model.VerificationToken;
import no.infa.repository.VerificationTokenRepository;
import no.routes.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Slf4j
@Service
public class RegistrationService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private EmailService emailService;


    public User registerUser(SignupRequest signUpRequest) {
        validateSignupRequest(signUpRequest);
        User user = userService.save(signUpRequest);
        String verificationCode = UUID.randomUUID().toString();
        createVerificationToken(user, verificationCode);
        return user;
    }

    /**
     * Could do 1 sql for Email and Username, but I want to find out what is taken and sql only responds true/false?
     * Not 100% sure tho
     *
     * @param signUpRequest contains the user details
     */
    private void validateSignupRequest(SignupRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use");
        }
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Username address already in use");
        }
        if (!signUpRequest.getConfirmPassword().equals(signUpRequest.getPassword())) {
            throw new BadRequestException("Passwords does not match");
        }
    }

    private void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
        sendVerificationToken(user, verificationToken);
    }

    private void sendVerificationToken(User user, VerificationToken verificationCode) {
        String body = "For security purposes, we need you to verify your email address before continuing using Thurtimous.  \n" +
                "http://infamous.no/verifyAccount?verification_code=" + verificationCode.getToken();
        log.info(body);
        emailService.sendSimpleMessage(user.getEmail(), "Please Confirm Your Email Address ", body);
    }

    public User verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new BadRequestException("This token is false fucker"));
        if (verificationToken.isExpired()) throw new BadRequestException("Token is expired");
        User user = userService.findById(verificationToken.getUser().getId());
        user.setUserState(UserState.ENABLED);
        verificationTokenRepository.delete(verificationToken);
        return userService.save(user);
    }
}

