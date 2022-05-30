package ru.kpfu.sweetlife.services;

import ru.kpfu.sweetlife.models.OauthToken;
import ru.kpfu.sweetlife.models.User;

import java.util.Optional;

public interface OAuthService {
    OauthToken getToken(String tempToken);
    Optional<User> getUserByToken(String value);
}
