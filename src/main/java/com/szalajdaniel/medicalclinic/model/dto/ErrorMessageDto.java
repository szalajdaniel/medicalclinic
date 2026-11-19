package com.szalajdaniel.medicalclinic.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.Instant;

@Getter
@Setter
public class ErrorMessageDto {
    private String message;
    private int status;
    private Instant timestamp;

    public ErrorMessageDto(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.timestamp = Instant.now();
    }
}
