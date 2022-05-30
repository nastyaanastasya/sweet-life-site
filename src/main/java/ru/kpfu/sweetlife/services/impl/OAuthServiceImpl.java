package ru.kpfu.sweetlife.services.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kpfu.sweetlife.exceptions.InvalidResponseBodyException;
import ru.kpfu.sweetlife.exceptions.OAuthResponseException;
import ru.kpfu.sweetlife.models.OauthToken;
import ru.kpfu.sweetlife.models.Role;
import ru.kpfu.sweetlife.models.State;
import ru.kpfu.sweetlife.models.User;
import ru.kpfu.sweetlife.repositories.OAuthTokenRepository;
import ru.kpfu.sweetlife.repositories.UserRepository;
import ru.kpfu.sweetlife.services.OAuthService;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {
    private final String GITHUB_OAUTH_URL = "https://github.com/login/oauth/access_token";
    private final String GITHUB_USERS_URL = "https://api.github.com/user";
    private final String GITHUB_USER_EMAILS_URL = "https://api.github.com/user/emails";

    @Value("${github.oauth.client-id}")
    private String clientId;
    @Value("${github.oauth.client-secrets}")
    private String clientSecret;

    private final UserRepository userRepository;
    private final OAuthTokenRepository oAuthTokenRepository;
    private final OkHttpClient okHttpClient;

    @Override
    public OauthToken getToken(String tempToken) {
        String token = getUserToken(tempToken);
        authUser(token);
        String email = getEmail(token);
        User user = getUser(email);
        return createToken(token, user);
    }

    @Override
    public Optional<User> getUserByToken(String value) {
        return oAuthTokenRepository.findOauthTokenByValue(value).map(OauthToken::getUser);
    }

    private String getEmail(String token) {
        Request request = buildEmailRequest(token);
        Response response = executeRequest(request);
        JsonArray array = getBody(response);
        String email = null;
        for (JsonElement elem : array) {
            JsonObject object = elem.getAsJsonObject();
            if (object.get("primary").getAsBoolean()) {
                email = object.get("email").getAsString();
                break;
            }
        }
        return email;
    }

    private Request buildEmailRequest(String token) {
        return new Request.Builder()
                .url(GITHUB_USER_EMAILS_URL)
                .addHeader("Authorization", "token " + token)
                .build();
    }

    private OauthToken createToken(String token, User user) {
        return oAuthTokenRepository.findOauthTokenByValue(token).orElse(saveNewToken(token, user));
    }

    private OauthToken saveNewToken(String token, User user) {
        OauthToken oauthToken = OauthToken.builder()
                .user(user)
                .value(token)
                .build();
        oAuthTokenRepository.save(oauthToken);
        return oauthToken;
    }

    private User getUser(String email) {
        return userRepository.findUserByUsername(email).orElse(createNewUser(email));
    }

    private void authUser(String token) {
        Request request = buildUserRequest(token);
        executeRequest(request);
    }

    private Request buildUserRequest(String token) {
        return new Request.Builder()
                .url(GITHUB_USERS_URL)
                .addHeader("Authorization", "token " + token)
                .build();
    }

    private String getUserToken(String tempToken) {
        try {
            Request request = buildCodeRequest(buildCodeRequestBody(tempToken));
            Response response = executeRequest(request);
            return Objects.requireNonNull(response.body()).string().substring(13, 53);
        } catch (IOException e) {
            throw new InvalidResponseBodyException("Invalid response body.");
        }
    }

    private JsonArray getBody(Response response) {
        try {
            return new JsonParser().parse(Objects.requireNonNull(response.body()).string()).getAsJsonArray();
        } catch (IOException e) {
            throw new InvalidResponseBodyException("Invalid response body");
        }
    }

    private User createNewUser(String email) {
        User newUser = User.builder()
                .nickname("User")
                .username(email)
                .hashPassword("")
                .state(State.ACTIVE)
                .role(Role.USER)
                .build();
        userRepository.save(newUser);
        return newUser;
    }

    private RequestBody buildCodeRequestBody(String tempToken) {
        return new FormBody.Builder()
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .add("code", tempToken)
                .build();
    }

    private Request buildCodeRequest(RequestBody body) {
        return new Request.Builder()
                .url(GITHUB_OAUTH_URL)
                .post(body)
                .build();
    }

    private Response executeRequest(Request request) {
        try {
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new OAuthResponseException("Unable to get response.");
        }
    }
}
