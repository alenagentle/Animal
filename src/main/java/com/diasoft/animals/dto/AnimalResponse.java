package com.diasoft.animals.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResponse {

    private Long id;
    private String result;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalResponse animalResponse = (AnimalResponse) o;
        return Objects.equals(result, animalResponse.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    public static AnimalResponse substringUID(AnimalResponse animalResponse) {
        var result = animalResponse.getResult();
        String substringResult = result.substring(result.indexOf("|"));
        animalResponse.setResult(substringResult);
        return animalResponse;
    }

}
