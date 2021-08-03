package no.routes;

import lombok.Data;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Data
public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(String message) {
        this(true, message);
    }
}
