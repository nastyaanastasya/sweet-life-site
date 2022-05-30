package ru.kpfu.sweetlife.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.sweetlife.models.OauthToken;

import java.util.Optional;

@Repository
public interface OAuthTokenRepository extends JpaRepository<OauthToken, Long> {
    Optional<OauthToken> findOauthTokenByValue(String value);
}
