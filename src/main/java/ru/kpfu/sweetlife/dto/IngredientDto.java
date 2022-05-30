package ru.kpfu.sweetlife.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ru.kpfu.sweetlife.models.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientDto {

    private String name;

    private float amount;
    private String units;

    public static IngredientDto from(Ingredient ingredient){
        return IngredientDto.builder()
                .name(ingredient.getName())
                .amount(ingredient.getAmount())
                .units(ingredient.getUnits())
                .build();
    }

    public static List<IngredientDto> from(@Nullable List<Ingredient> ingredients){
        return ingredients != null ? ingredients.stream()
                .map(IngredientDto::from)
                .collect(Collectors.toList()) : new ArrayList<>();
    }
}
