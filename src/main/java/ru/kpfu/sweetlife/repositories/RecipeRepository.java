package ru.kpfu.sweetlife.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.sweetlife.models.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<List<Recipe>> findRecipesByTitle(String title);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM recipe WHERE title LIKE :query"
    )
    List<Recipe> findRecipesByTitleContaining(String query);

    void deleteById(Long recipeId);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM recipe r WHERE r.user_id = :userId ORDER BY r.date_of_editing DESC"
    )
    Optional<List<Recipe>> getRecipesByAuthor(Long userId);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM recipe r " +
                    "WHERE r.id in " +
                    "(SELECT sr.recipe_id from saved_recipes sr " +
                    "WHERE sr.user_id = :userId) " +
                    "ORDER BY r.date_of_editing DESC"
    )
    Optional<List<Recipe>> getSavedRecipesByUserId(Long userId);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM recipe r " +
                    "WHERE r.id in " +
                    "(SELECT lr.recipe_id from liked_recipes lr " +
                    "WHERE lr.user_id = :userId) " +
                    "ORDER BY r.date_of_editing DESC"
    )
    Optional<List<Recipe>> getLikedRecipesByUserId(Long userId);
}
