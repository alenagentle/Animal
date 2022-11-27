package com.diasoft.animals.validator;

import com.diasoft.animals.dto.AnimalDTO;
import com.diasoft.animals.exception.EmptyElementsException;
import com.diasoft.animals.exception.EmptyNameElementsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnimalValidator {
    public void validate(List<AnimalDTO> animalDTOS) {

        if (animalDTOS.isEmpty()) {
            log.error("Empty list");
            throw new EmptyElementsException("Empty list");
        }
        if (animalDTOS
                .stream()
                .anyMatch(animalDTO -> animalDTO.getName().isEmpty())) {
            log.error("Empty name");
            throw new EmptyNameElementsException("Empty name");
        }
    }

}
