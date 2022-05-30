package ru.kpfu.sweetlife.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.sweetlife.exceptions.OAuthResponseException;
import ru.kpfu.sweetlife.models.OauthToken;
import ru.kpfu.sweetlife.services.OAuthService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping
    public String auth(
            @RequestParam("code") String token,
            HttpServletResponse response
    ) {
        OauthToken oauthToken;
        try {
            oauthToken = oAuthService.getToken(token);
            response.addCookie(new Cookie("oauth", oauthToken.getValue()));
        } catch (OAuthResponseException e) {
            return "redirect:/login";
        }
        return "redirect:/profile";
    }
}
