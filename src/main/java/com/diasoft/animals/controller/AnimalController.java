package com.diasoft.animals.controller;

import com.diasoft.animals.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping
    public void createAnimal() {
        animalService.createAnimal();
    }

}
