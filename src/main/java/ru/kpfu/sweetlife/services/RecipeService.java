package ru.kpfu.sweetlife.services;

import ru.kpfu.sweetlife.dto.RecipeDto;
import ru.kpfu.sweetlife.dto.forms.RecipeForm;

import java.util.List;

public interface RecipeService {

    RecipeDto findById(Long recipeId, Long viewerId);
    List<RecipeDto> findByName(String name, Long viewerId);
    List<RecipeDto> findByAuthorId(Long userId);
    List<RecipeDto> findLikedByAuthorId(Long userId);
    List<RecipeDto> findSavedByAuthorId(Long userId);

    List<RecipeDto> findAllRecipesByQuery(String query);

    RecipeDto save(RecipeForm recipe);

    void deleteRecipe(Long recipeId);

    boolean isLikedByViewer(Long recipeId, Long viewerId);
    boolean isSavedByViewer(Long recipeId, Long viewerId);
    boolean isViewerOwner(Long recipeId, Long viewerId);

    void likeRecipe(Long recipeId, Long userId);
    void unfollowRecipe(Long recipeId, Long userId);
    void saveRecipe(Long recipeId, Long userId);
    void cancelSaveRecipe(Long recipeId, Long userId);
}
