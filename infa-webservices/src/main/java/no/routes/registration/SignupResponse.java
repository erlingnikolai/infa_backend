package no.routes.registration;

import no.routes.ApiResponse;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
public class SignupResponse extends ApiResponse {


    public SignupResponse(String message) {
        super(message + " registered successfully, but email soon");
    }
}
