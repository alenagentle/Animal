package com.diasoft.animals.util.client;

import com.diasoft.animals.dto.AnimalDTO;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class AnimalMock {

    public static void setupMockAnimalResponse_WithServiceUnavailable(WireMockServer mockService) {
        mockService.stubFor(get(urlEqualTo("/animal"))
                .willReturn(
                        aResponse()
                                .withStatus(HttpStatus.SERVICE_UNAVAILABLE.value())));

    }

    public static void setupMockAnimalResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(get(WireMock.urlEqualTo("/animal"))
                .willReturn(
                        aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                AnimalDTO.class.getClassLoader().getResourceAsStream("payload/get-animal-response.json"),
                                                defaultCharset()))));
    }

}