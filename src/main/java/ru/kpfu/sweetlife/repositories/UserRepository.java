package ru.kpfu.sweetlife.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.sweetlife.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByNickname(String nickname);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM account WHERE nickname LIKE :query"
    )
    List<User> findUsersByNicknameContaining(String query);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM account a " +
                    "WHERE a.id IN " +
                    "(SELECT af.following_id FROM account_following af " +
                    "WHERE af.subscribers_id = :userId)"
    )
    List<User> getUserFollowing(Long userId);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM account a " +
                    "WHERE a.id IN " +
                    "(SELECT af.subscribers_id FROM account_following af " +
                    "WHERE af.following_id = :userId)"
    )
    List<User> getUserSubscribers(Long userId);
}
