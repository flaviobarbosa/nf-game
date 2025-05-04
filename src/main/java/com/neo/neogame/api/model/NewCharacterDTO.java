package com.neo.neogame.api.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewCharacterDTO {

    public static final String NAME_LENGTH_MESSAGE = "Name length should be between 4 and 15 characters";
    public static final String NAME_PATTERN_MESSAGE = "Name must contain letters or _ (underscore) characters";
    public static final String INVALID_JOB_MESSAGE = "The job is invalid";

    @Size(min = 4, max = 15, message = NAME_LENGTH_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z_]+$", message = NAME_PATTERN_MESSAGE)
    private String name;

    @Pattern(regexp = "warrior|thief|mage", flags = Pattern.Flag.CASE_INSENSITIVE, message = INVALID_JOB_MESSAGE)
    private String job;

}
