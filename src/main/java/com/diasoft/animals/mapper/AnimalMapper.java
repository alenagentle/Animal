package com.diasoft.animals.mapper;

import com.diasoft.animals.dto.AnimalResponse;
import com.diasoft.animals.entity.Animal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    AnimalResponse animalToAnimalResponse(Animal animal);

}
