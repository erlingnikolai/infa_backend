package no;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 27/07/2021
 */
@SpringBootApplication
@Slf4j
public class InfaStart {

    public static void main(String[] args) {
        SpringApplication.run(InfaStart.class, args);
    }
}
