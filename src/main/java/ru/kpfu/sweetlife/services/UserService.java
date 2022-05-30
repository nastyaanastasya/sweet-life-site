package ru.kpfu.sweetlife.services;

import ru.kpfu.sweetlife.dto.UserDto;
import ru.kpfu.sweetlife.dto.forms.ProfileUpdateForm;
import ru.kpfu.sweetlife.dto.forms.RegistrationForm;

import java.util.List;

public interface UserService {

    UserDto getUserById(Long userId);
    UserDto getUserById(Long userId, Long viewerId);
    UserDto getUserByNickname(String nickname);
    UserDto getUserByUsername(String username);

    UserDto register(RegistrationForm form);
    UserDto updateUser(ProfileUpdateForm form, Long userId);

    String getUserRating(Long userId);

    boolean isSubscribed(Long userId, Long subscriberId);
    void subscribe(Long userId, Long subscriberId);
    void unsubscribe(Long userId, Long subscriberId);

    void banUser(Long userId);
    void unbanUser(Long userId);

    List<UserDto> getAllUsers();

    List<UserDto> getUserSubscribers(Long userId);
    List<UserDto> getUserFollowing(Long userId);

    List<UserDto> findAllUsersByNickname(String query);
}
