package com.neo.neogame.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidJobException.class)
    public ResponseEntity<ExceptionDTO> invalidJobException(InvalidJobException ex) {
        var httpStatus = HttpStatus.BAD_REQUEST;

        var exceptionDTO = ExceptionDTO.builder()
                .timestamp(OffsetDateTime.now())
                .httpStatus(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .messages(Arrays.asList(ex.getMessage()))
                .build();

        return ResponseEntity
                .status(httpStatus)
                .body(exceptionDTO);
    }

    @ExceptionHandler(CharacterNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleCharacterNotFound(CharacterNotFoundException ex) {
        var httpStatus = HttpStatus.NOT_FOUND;

        var exceptionDTO = ExceptionDTO.builder()
                .timestamp(OffsetDateTime.now())
                .httpStatus(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .messages(Arrays.asList(ex.getMessage()))
                .build();

        return ResponseEntity
                .status(httpStatus)
                .body(exceptionDTO);
    }

    @ExceptionHandler(InvalidBattleException.class)
    public ResponseEntity<ExceptionDTO> handleInvalidBattleException(InvalidBattleException ex) {
        var httpStatus = HttpStatus.BAD_REQUEST;

        var exceptionDTO = ExceptionDTO.builder()
                .timestamp(OffsetDateTime.now())
                .httpStatus(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .messages(Arrays.asList(ex.getMessage()))
                .build();

        return ResponseEntity
                .status(httpStatus)
                .body(exceptionDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
        var messages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        var httpStatus = HttpStatus.BAD_REQUEST;

        var exceptionDTO = ExceptionDTO.builder()
                .timestamp(OffsetDateTime.now())
                .httpStatus(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .messages(messages)
                .build();

        return ResponseEntity
                .status(httpStatus)
                .body(exceptionDTO);
    }
}
