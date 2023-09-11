package com.example.tidsbanken.error;

import lombok.Data;

/**
 * Data structure for the error response generated by Spring for documentation purposes.
 */
@Data
public class ApiErrorResponse {
    private String timestamp;
    private Integer status;
    private String error;
    private String trace;
    private String message;
    private String path;
}