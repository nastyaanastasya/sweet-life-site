package ru.kpfu.sweetlife.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.sweetlife.models.Comment;
import ru.kpfu.sweetlife.models.Recipe;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDto {
    private Long id;
    private String title;

    private UserDto author;

    private String description;

    private List<IngredientDto> ingredients;
    private List<ImageDto> images;

    private Integer likesCount;
    private Integer savesCount;
    private Double recipeRating;

    private boolean isLikedByCurrentUser;
    private boolean isSavedByCurrentUser;
    private boolean isViewerOwner;

    private Integer hours;
    private Integer minutes;
    private LocalDate dateOdEditing;

    private List<CommentDto> comments;

    public static RecipeDto from(Recipe recipe) {
        return RecipeDto.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .description(recipe.getDescription())
                .likesCount(recipe.getLikedBy() == null ? 0 : recipe.getLikedBy().size())
                .savesCount(recipe.getSavedBy() == null ? 0 : recipe.getSavedBy().size())
                .author(UserDto.from(recipe.getAuthor()))
                .dateOdEditing(recipe.getDateOfEditing())
                .hours(recipe.getTimeOfCooking() / 60)
                .minutes(recipe.getTimeOfCooking() % 60)
                .ingredients(IngredientDto.from(recipe.getIngredients()))
                .comments(CommentDto.from(recipe.getComments()))
                .images(ImageDto.from(recipe.getImages()))
                .recipeRating(recipe.getComments() == null ? 0 : recipe.getComments().stream().mapToInt(Comment::getRecipeRate).average().orElse(0))
                .isLikedByCurrentUser(false)
                .isSavedByCurrentUser(false)
                .isViewerOwner(false)
                .build();
    }

    public static List<RecipeDto> from(List<Recipe> recipes) {
        return recipes.stream()
                .map(RecipeDto::from)
                .collect(Collectors.toList());
    }
}
