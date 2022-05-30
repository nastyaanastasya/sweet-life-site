package ru.kpfu.sweetlife.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.sweetlife.dto.UserDto;
import ru.kpfu.sweetlife.security.details.UserDetailsImpl;
import ru.kpfu.sweetlife.services.RecipeService;
import ru.kpfu.sweetlife.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final RecipeService recipeService;

    @GetMapping
    public String loadProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap map) {
        UserDto user = getUserById(userDetails.getUser().getId());
        map.addAttribute("user", user);
        map.addAttribute("recipeList", recipeService.findByAuthorId(user.getId()));
        map.addAttribute("title", "user.profile.recipes");
        return showProfile(map);
    }

    @GetMapping("/{id}")
    public String loadUserPage(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("id") Long profileId,
            ModelMap map
    ) {
        UserDto user = getUserById(profileId, userDetails.getUser().getId());
        map.addAttribute("user", user);
        map.addAttribute("recipeList", recipeService.findByAuthorId(user.getId()));
        map.addAttribute("title", "user.profile.recipes");
        return showProfile(map);
    }

    @GetMapping("/likedRecipes")
    public String loadLikedRecipes(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap map) {
        UserDto user = getUserById(userDetails.getUser().getId());
        map.addAttribute("user", user);
        map.addAttribute("recipeList", recipeService.findLikedByAuthorId(user.getId()));
        map.addAttribute("title", "user.profile.list.liked");
        return showProfile(map);
    }

    @GetMapping("/savedRecipes")
    public String loadSavedRecipes(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap map) {
        UserDto user = getUserById(userDetails.getUser().getId());
        map.addAttribute("user", user);
        map.addAttribute("recipeList", recipeService.findSavedByAuthorId(user.getId()));
        map.addAttribute("title", "user.profile.list.saved");
        return showProfile(map);
    }

    @GetMapping("/following")
    public String loadFollowing(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("userId") Long userId,
            ModelMap map
    ) {
        UserDto user = getUserById(userId, userDetails.getUser().getId());
        map.addAttribute("user", user);
        map.addAttribute("accounts", userService.getUserFollowing(user.getId()));
        map.addAttribute("title", "user.profile.list.following");
        return showProfile(map);
    }

    @GetMapping("/subscribers")
    public String loadSubscribers(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("userId") Long userId,
            ModelMap map
    ) {
        UserDto user = getUserById(userId, userDetails.getUser().getId());
        map.addAttribute("user", user);
        map.addAttribute("accounts", userService.getUserSubscribers(user.getId()));
        map.addAttribute("title", "user.profile.list.subscribers");
        return showProfile(map);
    }

    @PutMapping("/subscribe")
    public void subscribe(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam Long profileId,
            HttpServletResponse response
    ) {
        userService.subscribe(profileId, userDetails.getUser().getId());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PutMapping("/unsubscribe")
    public void unsubscribe(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam Long profileId,
            HttpServletResponse response
    ) {
        userService.unsubscribe(profileId, userDetails.getUser().getId());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/ban")
    public void banUser(
            @RequestParam Long profileId,
            HttpServletResponse response
    ) {
        userService.banUser(profileId);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/unban")
    public void unbanUser(
            @RequestParam Long profileId,
            HttpServletResponse response
    ) {
        userService.unbanUser(profileId);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private String showProfile(ModelMap map) {
        UserDto user = (UserDto) map.getAttribute("user");
        map.put("rating", userService.getUserRating(Objects.requireNonNull(user).getId()));
        return "user/profile";
    }

    private UserDto getUserById(Long userId) {
        return userService.getUserById(userId);
    }

    private UserDto getUserById(Long userId, Long viewerId) {
        return userService.getUserById(userId, viewerId);
    }
}
