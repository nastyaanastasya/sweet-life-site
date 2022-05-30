package ru.kpfu.sweetlife.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.sweetlife.dto.forms.ProfileUpdateForm;
import ru.kpfu.sweetlife.exceptions.DuplicateUsernameException;
import ru.kpfu.sweetlife.security.details.UserDetailsImpl;
import ru.kpfu.sweetlife.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final UserService userService;

    @GetMapping
    public String loadPage(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            ModelMap map
    ) {
        map.put("user", userService.getUserById(userDetails.getUser().getId()));
        map.put("form", new ProfileUpdateForm());
        return "user/settings";
    }

    @PostMapping
    public String updateUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @ModelAttribute("form") ProfileUpdateForm updateForm,
            BindingResult result,
            ModelMap map
    ) {
        if (result.hasErrors()) {
            map.put("form", updateForm);
            return "user/settings";
        }
        try {
            userService.updateUser(updateForm, userDetails.getUser().getId());
            return "redirect:/";
        } catch (DuplicateUsernameException e) {
            map.put("form", updateForm);
            map.addAttribute("errorMessage", true);
            return "user/settings";
        }
    }
}
