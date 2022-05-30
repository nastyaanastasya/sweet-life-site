package ru.kpfu.sweetlife.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.sweetlife.services.RecipeService;
import ru.kpfu.sweetlife.services.UserService;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final UserService userService;
    private final RecipeService recipeService;

    @GetMapping("/users")
    public String getUsersSearchPage(
            ModelMap map
    ) {
        return loadUserSearchContent(map);
    }

    @GetMapping("/recipes")
    public String getRecipesSearchPage(
            ModelMap map
    ) {
        return loadRecipeSearchContent(map);
    }

    @PostMapping("/users")
    public String loadSearchPageWithUsers(
            @RequestParam("query") String nickname,
            ModelMap map
    ) {
        map.put("enteredQuery", nickname);
        map.put("accounts", userService.findAllUsersByNickname(nickname.trim()));
        return "search/users";
    }

    @PostMapping("/recipes")
    public String loadSearchPageWithRecipes(
            @RequestParam("query") String recipeName,
            ModelMap map
    ) {
        map.put("enteredQuery", recipeName);
        map.put("recipeList", recipeService.findAllRecipesByQuery(recipeName.trim()));
        return "search/recipes";
    }

    private String loadUserSearchContent(ModelMap map) {
        map.put("placeholder", "search.placeholder.users");
        return loadSearchPage(map);
    }

    private String loadRecipeSearchContent(ModelMap map) {
        map.put("placeholder", "search.placeholder.recipes");
        return loadSearchPage(map);
    }

    private String loadSearchPage(ModelMap map) {
        return "search/search";
    }
}
