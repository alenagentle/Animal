package com.diasoft.animals.util.converter;

import com.diasoft.animals.dto.AnimalDTO;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

public class AnimalConverter {
    public static String convert(List<AnimalDTO> animalsDto) {
        var sbAnimal = new StringBuilder(String.valueOf(UUID.randomUUID()));
        sbAnimal
                .append("|")
                .append(HttpStatus.OK)
                .append("|");

        animalsDto.forEach(animal ->
                sbAnimal
                        .append(animal.getName())
                        .append(",")
        );
        sbAnimal.deleteCharAt(sbAnimal.length()-1);
        return sbAnimal.toString();
    }
}
