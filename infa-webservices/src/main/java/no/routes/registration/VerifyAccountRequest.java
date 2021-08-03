package no.routes.registration;


import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Data
public class VerifyAccountRequest {

    @NotBlank
    private String token;
}
