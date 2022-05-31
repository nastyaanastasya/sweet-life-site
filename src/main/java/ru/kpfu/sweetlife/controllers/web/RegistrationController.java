package ru.kpfu.sweetlife.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.sweetlife.dto.forms.RegistrationForm;
import ru.kpfu.sweetlife.exceptions.DuplicateEmailException;
import ru.kpfu.sweetlife.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String getSignUpPage(Authentication authentication, ModelMap map) {
        if (authentication != null) {
            return "redirect:/";
        }
        map.addAttribute("form", new RegistrationForm());
        return "forms/registration";
    }

    @PostMapping
    public String signUp(
            @ModelAttribute("form") @Valid RegistrationForm registrationForm,
            BindingResult result,
            ModelMap map
    ) {
        if (result.hasErrors()) {
//            map.addAttribute("error", result.getAllErrors());
            map.addAttribute("form", registrationForm);
            return "forms/registration";
        }
        try {
            userService.register(registrationForm);
            return "redirect:/";
        } catch (DuplicateEmailException e) {
            map.addAttribute("form", registrationForm);
            map.addAttribute("errorMessage", true);
            return "forms/registration";
        }
    }
}
