package ru.kpfu.sweetlife.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.sweetlife.dto.RecipeDto;
import ru.kpfu.sweetlife.dto.forms.CommentForm;
import ru.kpfu.sweetlife.security.details.UserDetailsImpl;
import ru.kpfu.sweetlife.services.CommentService;
import ru.kpfu.sweetlife.services.RecipeService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeViewController {

    private final RecipeService recipeService;
    private final CommentService commentService;

    @GetMapping("/{id}")
    public String loadRecipePage(
            @PathVariable("id") Long recipeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            ModelMap map
    ) {
        RecipeDto recipe = recipeService.findById(recipeId, userDetails.getUser().getId());
        recipe.getComments().forEach(comment -> commentService.setParams(comment, userDetails.getUser().getId()));
        map.addAttribute("recipe", recipe);
        map.addAttribute("form", new CommentForm());
        return "recipe/view";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteRecipe(
            @PathVariable("id") Long recipeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        if (recipeService.isViewerOwner(recipeId, userDetails.getUser().getId())) {
            recipeService.deleteRecipe(recipeId);
        }
        return "redirect:/";
    }

    @PutMapping("/like")
    public void likeRecipe(
            @RequestParam("recipeId") Long recipeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            HttpServletResponse response
    ) {
        recipeService.likeRecipe(recipeId, userDetails.getUser().getId());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PutMapping("/unfollow")
    public void unfollowRecipe(
            @RequestParam("recipeId") Long recipeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            HttpServletResponse response
    ) {
        recipeService.unfollowRecipe(recipeId, userDetails.getUser().getId());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PutMapping("/save")
    public void saveRecipe(
            @RequestParam("recipeId") Long recipeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            HttpServletResponse response
    ) {
        recipeService.saveRecipe(recipeId, userDetails.getUser().getId());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PutMapping("/cancelSave")
    public void cancelSaveRecipe(
            @RequestParam("recipeId") Long recipeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            HttpServletResponse response
    ) {
        recipeService.cancelSaveRecipe(recipeId, userDetails.getUser().getId());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
