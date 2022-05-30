package ru.kpfu.sweetlife.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.sweetlife.models.Ingredient;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<List<Ingredient>> findIngredientsByRecipeId(Long recipeId);
}
