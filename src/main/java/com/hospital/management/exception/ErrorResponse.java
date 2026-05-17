package com.hospital.management.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorResponse {

    private final int status;
    private final String message;
    private final LocalDateTime timestamp;
}
