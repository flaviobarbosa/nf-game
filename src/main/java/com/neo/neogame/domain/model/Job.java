package com.neo.neogame.domain.model;

import com.neo.neogame.api.model.NewCharacterDTO;
import com.neo.neogame.domain.exception.InvalidJobException;
import lombok.Getter;

import java.util.Arrays;

import static com.neo.neogame.api.model.NewCharacterDTO.INVALID_JOB_MESSAGE;

@Getter
public enum Job {

    WARRIOR("warrior"),
    THIEF("thief"),
    MAGE("mage");

    private String name;

    Job(String name) {
        this.name = name;
    }

    public static Job getByJobName(String jobName) {
        return Arrays.stream(values())
                .filter(job -> job.getName().equalsIgnoreCase(jobName))
                .findFirst()
                .orElseThrow(() -> new InvalidJobException(INVALID_JOB_MESSAGE));
    }
}
