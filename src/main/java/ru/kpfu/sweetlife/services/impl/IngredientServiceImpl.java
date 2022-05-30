package ru.kpfu.sweetlife.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.sweetlife.dto.IngredientDto;
import ru.kpfu.sweetlife.exceptions.IngredientNotFoundException;
import ru.kpfu.sweetlife.models.Ingredient;
import ru.kpfu.sweetlife.models.Recipe;
import ru.kpfu.sweetlife.repositories.IngredientRepository;
import ru.kpfu.sweetlife.repositories.RecipeRepository;
import ru.kpfu.sweetlife.services.IngredientService;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    @Override
    public IngredientDto save(Ingredient ingredient) {
        return IngredientDto.from(ingredientRepository.save(ingredient));
    }

    @Override
    public List<IngredientDto> getIngredientsByRecipeId(Long recipeId) {
        return IngredientDto.from(
                ingredientRepository.findIngredientsByRecipeId(recipeId).orElseThrow(IngredientNotFoundException::new)
        );
    }

    @Override
    public void saveWithRecipeId(String[] ingredient, String[] amount, String[] unit, Long id) {
        Recipe recipe = recipeRepository.getById(id);
        for (int i = 0; i < ingredient.length; i++) {
            ingredientRepository.save(
                    Ingredient.builder()
                            .recipe(recipe)
                            .name(ingredient[i])
                            .amount(Float.valueOf(amount[i]))
                            .units(unit[i])
                            .build()
            );
        }
    }
}
