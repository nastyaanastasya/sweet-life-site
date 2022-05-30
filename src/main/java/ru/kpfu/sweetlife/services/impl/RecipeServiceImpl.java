package ru.kpfu.sweetlife.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.sweetlife.dto.RecipeDto;
import ru.kpfu.sweetlife.dto.forms.RecipeForm;
import ru.kpfu.sweetlife.exceptions.RecipeNotFoundException;
import ru.kpfu.sweetlife.exceptions.UserNotFoundException;
import ru.kpfu.sweetlife.models.Recipe;
import ru.kpfu.sweetlife.models.User;
import ru.kpfu.sweetlife.repositories.ImageRepository;
import ru.kpfu.sweetlife.repositories.IngredientRepository;
import ru.kpfu.sweetlife.repositories.RecipeRepository;
import ru.kpfu.sweetlife.repositories.UserRepository;
import ru.kpfu.sweetlife.services.ImageService;
import ru.kpfu.sweetlife.services.IngredientService;
import ru.kpfu.sweetlife.services.RecipeService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final IngredientService ingredientService;

    @Override
    public RecipeDto findById(Long recipeId, Long viewerId) {
        RecipeDto recipe = RecipeDto.from(getRecipe(recipeId));
        return setParams(recipe, viewerId);
    }

    @Override
    public List<RecipeDto> findByName(String name, Long viewerId) {
        List<RecipeDto> recipeList = RecipeDto.from(
                recipeRepository.findRecipesByTitle(name)
                        .orElseThrow(RecipeNotFoundException::new)
        );
        recipeList.forEach(recipe -> setParams(recipe, viewerId));
        return recipeList;
    }

    @Override
    public List<RecipeDto> findByAuthorId(Long userId) {
        return RecipeDto.from(recipeRepository.getRecipesByAuthor(userId).orElseThrow(RecipeNotFoundException::new));
    }

    @Override
    public List<RecipeDto> findLikedByAuthorId(Long userId) {
        return RecipeDto.from(recipeRepository.getLikedRecipesByUserId(userId).orElseThrow(RecipeNotFoundException::new));
    }

    @Override
    public List<RecipeDto> findSavedByAuthorId(Long userId) {
        return RecipeDto.from(recipeRepository.getSavedRecipesByUserId(userId).orElseThrow(RecipeNotFoundException::new));
    }

    @Override
    public List<RecipeDto> findAllRecipesByQuery(String query) {
        return RecipeDto.from(recipeRepository.findRecipesByTitleContaining(query));
    }

    @Override
    public RecipeDto save(RecipeForm recipe) {
        Recipe newRecipe = Recipe.builder()
                .author(userRepository.getById(recipe.getAuthorId()))
                .title(recipe.getTitle())
                .description(recipe.getDescription())
                .dateOfEditing(LocalDate.now())
                .timeOfCooking(getTimeOfCooking(recipe.getHours(), recipe.getMinutes()))
                .build();
        Long id = recipeRepository.save(newRecipe).getId();
        recipe.getImages().forEach(img -> imageService.saveWithRecipeId(img, id));
        ingredientService.saveWithRecipeId(recipe.getIngredient(), recipe.getAmount(), recipe.getUnit(), id);
        return RecipeDto.from(recipeRepository.getById(id));
    }

    @Override
    public void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    @Override
    public boolean isLikedByViewer(Long recipeId, Long viewerId) {
        Recipe recipe = getRecipe(recipeId);
        User viewer = getUser(viewerId);
        return isLikedByViewer(recipe, viewer);
    }

    @Override
    public boolean isSavedByViewer(Long recipeId, Long viewerId) {
        Recipe recipe = getRecipe(recipeId);
        User viewer = getUser(viewerId);
        return isSavedByViewer(recipe, viewer);
    }

    @Override
    public boolean isViewerOwner(Long recipeId, Long viewerId) {
        Recipe recipe = getRecipe(recipeId);
        User viewer = getUser(viewerId);
        return isViewerOwner(recipe, viewer);
    }

    @Override
    public void likeRecipe(Long recipeId, Long userId) {
        Recipe recipe = getRecipe(recipeId);
        User user = getUser(userId);
        if (!isLikedByViewer(recipeId, userId)) {
            user.getLikedRecipes().add(recipe);
            userRepository.save(user);
        }
    }

    @Override
    public void unfollowRecipe(Long recipeId, Long userId) {
        Recipe recipe = getRecipe(recipeId);
        User user = getUser(userId);
        if (isLikedByViewer(recipeId, userId)) {
            user.getLikedRecipes().remove(recipe);
            userRepository.save(user);
        }
    }

    @Override
    public void saveRecipe(Long recipeId, Long userId) {
        Recipe recipe = getRecipe(recipeId);
        User user = getUser(userId);
        if (!isSavedByViewer(recipeId, userId)) {
            user.getSavedRecipes().add(recipe);
            userRepository.save(user);
        }
    }

    @Override
    public void cancelSaveRecipe(Long recipeId, Long userId) {
        Recipe recipe = getRecipe(recipeId);
        User user = getUser(userId);
        if (isSavedByViewer(recipeId, userId)) {
            user.getSavedRecipes().remove(recipe);
            userRepository.save(user);
        }
    }

    private RecipeDto setParams(RecipeDto recipe, Long viewerId) {
        recipe.setLikedByCurrentUser(isLikedByViewer(recipe.getId(), viewerId));
        recipe.setSavedByCurrentUser(isSavedByViewer(recipe.getId(), viewerId));
        recipe.setViewerOwner(isViewerOwner(recipe.getId(), viewerId));
        return recipe;
    }

    private Integer getTimeOfCooking(Integer hours, Integer minutes) {
        if (hours == null && minutes == null) return 0;
        if (hours != null && minutes != null) return hours * 60 + minutes;
        if (minutes == null) return hours;
        else return minutes;
    }

    private boolean isLikedByViewer(Recipe recipe, User viewer) {
        return viewer.getLikedRecipes().contains(recipe);
    }

    private boolean isSavedByViewer(Recipe recipe, User viewer) {
        return viewer.getSavedRecipes().contains(recipe);
    }

    private boolean isViewerOwner(Recipe recipe, User viewer) {
        return viewer.getRecipes().contains(recipe);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private Recipe getRecipe(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(RecipeNotFoundException::new);
    }
}
