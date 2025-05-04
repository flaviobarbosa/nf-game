package com.neo.neogame.domain.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Data
@Builder
public class ExceptionDTO {

    private OffsetDateTime timestamp;
    private int httpStatus;
    private String error;
    private String message;

}
