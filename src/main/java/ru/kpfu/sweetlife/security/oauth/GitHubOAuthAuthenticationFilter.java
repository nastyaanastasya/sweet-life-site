package ru.kpfu.sweetlife.security.oauth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GitHubOAuthAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie oAuthCookie = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("oauth")) {
                    oAuthCookie = cookie;
                    break;
                }
            }
            if (oAuthCookie != null)
                SecurityContextHolder.getContext().setAuthentication(new GitHubOAuthAuthentication(oAuthCookie.getValue()));
        }
        filterChain.doFilter(request, response);
    }
}
