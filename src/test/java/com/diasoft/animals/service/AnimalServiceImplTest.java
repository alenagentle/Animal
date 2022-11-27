package com.diasoft.animals.service;

import com.diasoft.animals.dto.AnimalDTO;
import com.diasoft.animals.dto.AnimalResponse;
import com.diasoft.animals.entity.Animal;
import com.diasoft.animals.exception.EmptyElementsException;
import com.diasoft.animals.exception.EmptyNameElementsException;
import com.diasoft.animals.manager.AnimalManager;
import com.diasoft.animals.mapper.AnimalMapper;
import com.diasoft.animals.mapper.AnimalMapperImpl;
import com.diasoft.animals.repository.AnimalRepository;
import com.diasoft.animals.util.client.AnimalClient;
import com.diasoft.animals.util.converter.AnimalConverter;
import com.diasoft.animals.validator.AnimalValidator;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class AnimalServiceImplTest {

    private final AnimalClient animalClient = mock(AnimalClient.class);
    private final AnimalRepository animalRepository = mock(AnimalRepository.class);
    private final AnimalValidator animalValidator = new AnimalValidator();
    private final AnimalManager animalManager = new AnimalManager(animalRepository);
    private final AnimalMapper animalMapper = new AnimalMapperImpl();
    private final AnimalServiceImpl animalServiceImpl =
            new AnimalServiceImpl(
                    animalClient,
                    animalManager,
                    animalValidator,
                    animalMapper);

    @Test
    void givenAnimal() {
        var animalsDto = buildListAnimalsDto();

        when(animalClient.getAnimals()).thenReturn(animalsDto);
        var convertedAnimal = AnimalConverter.convert(animalsDto);
        var animal = buildAnimal(convertedAnimal);
        var expectedAnimal = animalMapper.animalToAnimalResponse(animal);

        when(animalRepository.save(any())).thenReturn(animal);
        var actualAnimalResponse = animalServiceImpl.createAnimal();

        assertEquals(AnimalResponse.substringUID(actualAnimalResponse), AnimalResponse.substringUID(expectedAnimal));
    }

    @Test
    void whenEmptyNameAnimal() {
        var animalsDto = buildListAnimalsDto_WithEmptyName();
        when(animalClient.getAnimals()).thenReturn(animalsDto);
        EmptyNameElementsException thrown = assertThrows(
                EmptyNameElementsException.class,
                animalServiceImpl::createAnimal);

        assertTrue(thrown.getMessage().contains("Empty name"));
    }

    @Test
    void whenEmptyListAnimal() {
        var animalsDto = buildEmptyListAnimalsDto();
        when(animalClient.getAnimals()).thenReturn(animalsDto);
        EmptyElementsException thrown = assertThrows(
                EmptyElementsException.class,
                animalServiceImpl::createAnimal);
        assertTrue(thrown.getMessage().contains("Empty list"));
    }

    private List<AnimalDTO> buildListAnimalsDto() {
        return List.of(
                buildAnimalDto("cow"),
                buildAnimalDto("gees"),
                buildAnimalDto("cat"));
    }

    private List<AnimalDTO> buildListAnimalsDto_WithEmptyName() {
        return List.of(
                buildAnimalDto("cow"),
                buildAnimalDto(""),
                buildAnimalDto("cat"));
    }

    private List<AnimalDTO> buildEmptyListAnimalsDto() {
        return Collections.emptyList();
    }

    private AnimalDTO buildAnimalDto(String name) {
        var animalDTO = new AnimalDTO();
        animalDTO.setName(name);
        return animalDTO;
    }

    private Animal buildAnimal(String resultAnimal) {
        var animal = new Animal();
        animal.setResult(resultAnimal);
        return animal;
    }

}