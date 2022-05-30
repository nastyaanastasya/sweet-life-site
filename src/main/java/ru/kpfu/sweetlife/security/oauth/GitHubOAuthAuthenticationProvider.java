package ru.kpfu.sweetlife.security.oauth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.kpfu.sweetlife.models.OauthToken;
import ru.kpfu.sweetlife.repositories.OAuthTokenRepository;
import ru.kpfu.sweetlife.security.details.UserDetailsImpl;

@Component
@RequiredArgsConstructor
public class GitHubOAuthAuthenticationProvider implements AuthenticationProvider {

    private final OAuthTokenRepository oAuthTokenRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        GitHubOAuthAuthentication oAuthAuthentication = (GitHubOAuthAuthentication) authentication;
        OauthToken oAuthToken = oAuthTokenRepository.findOauthTokenByValue((String) oAuthAuthentication.getCredentials())
                .orElseThrow(() -> new BadCredentialsException("Token not found"));
        oAuthAuthentication.setUserDetails(new UserDetailsImpl(oAuthToken.getUser()));
        oAuthAuthentication.setAuthenticated(true);
        return oAuthAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return GitHubOAuthAuthentication.class.equals(authentication);
    }
}
