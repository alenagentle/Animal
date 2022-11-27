package com.diasoft.animals.util.client;

import com.diasoft.animals.config.FeignConfig;
import com.diasoft.animals.dto.AnimalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "animal-feign-client",
        url = "${animalClient.url}",
        configuration = FeignConfig.class
)
public interface AnimalClient {

    @GetMapping("/animal")
    List<AnimalDTO> getAnimals();
}
