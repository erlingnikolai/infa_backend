package no.infa;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Erling Nikolai Ukkelberg
 * @created 02/08/2021
 */
@SpringBootApplication
@Slf4j
public class GatewayInfa {

    public static void main(String[] args) {
        SpringApplication.run(GatewayInfa.class, args);
    }

}
