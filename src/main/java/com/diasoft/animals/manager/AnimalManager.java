package com.diasoft.animals.manager;

import com.diasoft.animals.dto.AnimalDTO;
import com.diasoft.animals.entity.Animal;
import com.diasoft.animals.repository.AnimalRepository;
import com.diasoft.animals.util.converter.AnimalConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
public class AnimalManager {
    private final AnimalRepository animalRepository;

    public Animal saveAnimal(List<AnimalDTO> animalsDto) {
        var stringAnimal = AnimalConverter.convert(animalsDto);
        var animal = new Animal();
        animal.setResult(stringAnimal);
        log.info("Animal saved");
        return animalRepository.save(animal);
    }

}
