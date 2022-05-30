package ru.kpfu.sweetlife.services;

import ru.kpfu.sweetlife.dto.IngredientDto;
import ru.kpfu.sweetlife.models.Ingredient;

import java.util.List;

public interface IngredientService {

    IngredientDto save(Ingredient ingredient);
    List<IngredientDto> getIngredientsByRecipeId(Long recipeId);
    void saveWithRecipeId(String[] ingredient, String[] amount, String[] unit, Long id);
}
