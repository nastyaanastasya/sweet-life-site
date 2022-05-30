package ru.kpfu.sweetlife.security.oauth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.sweetlife.security.details.UserDetailsImpl;

import java.util.Collection;

public class GitHubOAuthAuthentication implements Authentication {

    private String oAuthToken;
    private boolean isAuth;
    private UserDetails userDetails;

    public GitHubOAuthAuthentication(String oAuthToken) {
        this.oAuthToken = oAuthToken;
        this.userDetails = new UserDetailsImpl();
        isAuth = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return oAuthToken;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuth;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuth = isAuthenticated;
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
