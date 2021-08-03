package no.routes.login;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 29/07/2021
 */

@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private static String tokenType = "Bearer";
    private String username;

}