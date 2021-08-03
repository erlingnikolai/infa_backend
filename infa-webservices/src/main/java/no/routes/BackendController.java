package no.routes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 27/07/2021
 */
@Slf4j
@RestController
@RequestMapping("/backend")
public class BackendController {


    @GetMapping()
    public String test() {
        return "Hei fra backend :)";
    }

}
