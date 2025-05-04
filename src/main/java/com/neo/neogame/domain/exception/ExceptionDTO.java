package com.neo.neogame.domain.exception;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class ExceptionDTO {

    private OffsetDateTime timestamp;
    private int httpStatus;
    private String error;
    private List<String> messages;

}
