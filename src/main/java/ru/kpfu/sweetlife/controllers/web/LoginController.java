package ru.kpfu.sweetlife.controllers.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Value("${github.oauth.client-id}")
    private String clientId;

    @GetMapping
    public String showLoginPage(
            @RequestParam(required = false) String error,
            Authentication authentication,
            ModelMap map
    ) {
        if (authentication != null) {
            return "redirect:/";
        }
        map.put("clientId", clientId);
        map.put("error", error);
        return "forms/login";
    }
}
