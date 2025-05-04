package com.neo.neogame.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidJobException.class)
    public ResponseEntity<ExceptionDTO> invalidJobException(InvalidJobException ex) {
        var httpStatus = HttpStatus.BAD_REQUEST;

        var exceptionDTO = ExceptionDTO.builder()
                .timestamp(OffsetDateTime.now())
                .httpStatus(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(ex.getMessage())
                .build();

        return ResponseEntity
                .status(httpStatus)
                .body(exceptionDTO);
    }
}
