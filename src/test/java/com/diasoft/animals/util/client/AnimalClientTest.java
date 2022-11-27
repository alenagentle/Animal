package com.diasoft.animals.util.client;

import com.diasoft.animals.dto.AnimalDTO;
import com.diasoft.animals.util.client.config.WireMockConfig;
import com.github.tomakehurst.wiremock.WireMockServer;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

import static com.diasoft.animals.util.client.AnimalMock.setupMockAnimalResponse;
import static com.diasoft.animals.util.client.AnimalMock.setupMockAnimalResponse_WithServiceUnavailable;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {WireMockConfig.class})
class AnimalClientTest {

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private AnimalClient animalClient;

    @Test
    public void whenGetAnimals_thenTheCorrectAnimalsWillBeReturned() throws IOException {
        setupMockAnimalResponse(wireMockServer);
        assertTrue(animalClient.getAnimals()
                .containsAll(asList(
                        new AnimalDTO("gees"),
                        new AnimalDTO("cat"),
                        new AnimalDTO("cow"))));
    }

    @Test
    public void givenAnimalApiIsNotAvailable_whenGetAnimalsCalled_thenThrowFeignException() {
        setupMockAnimalResponse_WithServiceUnavailable(wireMockServer);
        assertThrows(FeignException.class, () -> animalClient.getAnimals());
    }

}