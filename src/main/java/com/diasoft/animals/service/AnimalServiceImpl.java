package com.diasoft.animals.service;

import com.diasoft.animals.dto.AnimalDTO;
import com.diasoft.animals.dto.AnimalResponse;
import com.diasoft.animals.entity.Animal;
import com.diasoft.animals.manager.AnimalManager;
import com.diasoft.animals.mapper.AnimalMapper;
import com.diasoft.animals.util.client.AnimalClient;
import com.diasoft.animals.validator.AnimalValidator;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalClient animalClient;
    private final AnimalManager animalManager;
    private final AnimalValidator animalValidator;
    private final AnimalMapper animalMapper;

    @Override
    @Transactional
    public AnimalResponse createAnimal() {
        List<AnimalDTO> animalsDto = Collections.emptyList();
        try {
            animalsDto = animalClient.getAnimals();
        } catch (FeignException e) {
            log.error("Feign exception");
        }
        animalValidator.validate(animalsDto);
        Animal savedAnimal = animalManager.saveAnimal(animalsDto);
        return animalMapper.animalToAnimalResponse(savedAnimal);
    }

}
