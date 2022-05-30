package ru.kpfu.sweetlife.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.kpfu.sweetlife.dto.forms.RecipeForm;
import ru.kpfu.sweetlife.security.details.UserDetailsImpl;
import ru.kpfu.sweetlife.services.RecipeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/recipe/edit")
@RequiredArgsConstructor
public class RecipeEditingController {

    private final RecipeService recipeService;

    @GetMapping
    public String loadRecipeAddingPage(ModelMap map) {
        map.addAttribute("form", new RecipeForm());
        return loadRecipePage(map);
    }

    @PostMapping
    public String saveRecipe(
            @Valid @ModelAttribute("form") RecipeForm recipeForm,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            BindingResult result,
            ModelMap map
    ) {
        if (result.hasErrors()) {
            map.addAttribute("error", result.getAllErrors());
            map.addAttribute("form", recipeForm);
            return loadRecipePage(map);
        }
        recipeForm.setAuthorId(userDetails.getUser().getId());
        Long id = recipeService.save(recipeForm).getId();
        return "redirect:" + MvcUriComponentsBuilder.fromMethodName(
                RecipeViewController.class,
                "loadRecipePage",
                id, userDetails, map
        ).build();
    }

    private String loadRecipePage(ModelMap map) {
        return "recipe/edit";
    }
}
