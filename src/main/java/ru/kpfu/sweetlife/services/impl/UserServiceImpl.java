package ru.kpfu.sweetlife.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.sweetlife.dto.RecipeDto;
import ru.kpfu.sweetlife.dto.UserDto;
import ru.kpfu.sweetlife.dto.forms.ProfileUpdateForm;
import ru.kpfu.sweetlife.dto.forms.RegistrationForm;
import ru.kpfu.sweetlife.exceptions.DuplicateEmailException;
import ru.kpfu.sweetlife.exceptions.UserNotFoundException;
import ru.kpfu.sweetlife.models.Role;
import ru.kpfu.sweetlife.models.State;
import ru.kpfu.sweetlife.models.User;
import ru.kpfu.sweetlife.repositories.UserRepository;
import ru.kpfu.sweetlife.services.ImageService;
import ru.kpfu.sweetlife.services.UserService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    @Override
    public UserDto getUserById(Long userId) {
        return UserDto.from(userRepository.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDto getUserById(Long userId, Long viewerId) {
        UserDto user = getUserById(userId);
        UserDto viewer = getUserById(viewerId);
        user.setViewer(user.getId().equals(viewer.getId()));
        if (!user.isViewer() && viewer.getRole().equals(Role.ADMIN)) {
            user.setViewerAdmin(true);
        }
        if (isSubscribed(userId, viewerId)) {
            user.setFollowed(true);
        }
        return user;
    }

    @Override
    public UserDto getUserByNickname(String nickname) {
        return UserDto.from(userRepository.findUserByNickname(nickname).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return UserDto.from(userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDto register(RegistrationForm form) {
        checkIfUsernameAlreadyRegistered(form.getUsername());
        checkIfNicknameAlreadyRegistered(form.getNickname());

        User user = User.builder()
                .nickname(form.getNickname())
                .username(form.getUsername())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(Role.USER)
                .state(State.ACTIVE)
                .dateOfRegistration(LocalDate.now())
                .build();

        return UserDto.from(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(ProfileUpdateForm form, Long userId) {
        User user = userRepository.getById(userId);
        if(!user.getNickname().equals(form.getNickname())) {
            checkIfNicknameAlreadyRegistered(form.getNickname());
            user.setNickname(form.getNickname());
        }
        if(user.getHashPassword().equals(passwordEncoder.encode(form.getCurrentPassword()))){
            user.setHashPassword(passwordEncoder.encode(form.getNewPassword()));
        }
        imageService.saveProfileImage(form.getFile(), user.getId());
        if (form.getFile() != null) {
            imageService.saveProfileImage(form.getFile(), userId);
        }
        return UserDto.from(userRepository.save(user));
    }

    @Override
    public String getUserRating(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return String.format("%.1f", RecipeDto.from(user.getRecipes()).stream().mapToDouble(RecipeDto::getRecipeRating).filter(rating -> rating != 0).average().orElse(0));
    }

    @Override
    public boolean isSubscribed(Long userId, Long subscriberId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User viewer = userRepository.findById(subscriberId).orElseThrow(UserNotFoundException::new);
        return isSubscribed(user, viewer);
    }

    @Override
    public void subscribe(Long userId, Long subscriberId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User viewer = userRepository.findById(subscriberId).orElseThrow(UserNotFoundException::new);
        subscribe(user, viewer);
    }

    @Override
    public void unsubscribe(Long userId, Long subscriberId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User viewer = userRepository.findById(subscriberId).orElseThrow(UserNotFoundException::new);
        unsubscribe(user, viewer);
    }

    @Override
    public void banUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setState(State.BANNED);
        userRepository.save(user);
    }

    @Override
    public void unbanUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setState(State.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(userRepository.findAll());
    }

    @Override
    public List<UserDto> getUserSubscribers(Long userId) {
        return UserDto.from(userRepository.getUserSubscribers(userId));
    }

    @Override
    public List<UserDto> getUserFollowing(Long userId) {
        return UserDto.from(userRepository.getUserFollowing(userId));
    }

    @Override
    public List<UserDto> findAllUsersByNickname(String query) {
        return UserDto.from(userRepository.findUsersByNicknameContaining(query));
    }

    private void subscribe(User user, User viewer) {
        if (!isSubscribed(user, viewer)) {
            user.getSubscribers().add(viewer);
            viewer.getFollowing().add(user);
            userRepository.save(user);
            userRepository.save(viewer);
        }
    }

    private void unsubscribe(User user, User viewer) {
        if (isSubscribed(user, viewer)) {
            user.getSubscribers().remove(viewer);
            viewer.getFollowing().remove(user);
            userRepository.save(user);
            userRepository.save(viewer);
        }
    }

    private boolean isSubscribed(User user, User viewer) {
        return viewer.getFollowing().contains(user);
    }

    private void checkIfNicknameAlreadyRegistered(String nickname) {
        if (userRepository.findUserByNickname(nickname).isPresent()) {
            throw new DuplicateEmailException("User with nickname " + nickname + " is already registered");
        }
    }

    private void checkIfUsernameAlreadyRegistered(String username) {
        if (userRepository.findUserByUsername(username).isPresent()) {
            throw new DuplicateEmailException("User with username " + username + " is already registered");
        }
    }
}
