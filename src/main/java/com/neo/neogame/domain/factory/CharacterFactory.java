package com.neo.neogame.domain.factory;

import com.neo.neogame.domain.exception.InvalidJobException;
import com.neo.neogame.domain.model.*;
import com.neo.neogame.domain.model.Character;
import org.springframework.stereotype.Component;

import static com.neo.neogame.api.model.NewCharacterDTO.INVALID_JOB_MESSAGE;

@Component
public class CharacterFactory {

    public Character create(String jobName, String name) {
        Job job = Job.getByJobName(jobName);

        switch (job) {
            case WARRIOR:
                return new Warrior(name);
            case THIEF:
                return new Thief(name);
            case MAGE:
                return new Mage(name);
            default:
                throw new InvalidJobException(INVALID_JOB_MESSAGE);
        }
    }
}
