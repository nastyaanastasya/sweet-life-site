package ru.kpfu.sweetlife.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.sweetlife.models.Role;
import ru.kpfu.sweetlife.models.State;
import ru.kpfu.sweetlife.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String nickname;
    private String username;

    private Integer recipeNum;
    private Integer subscribersNum;

    private Role role;
    private State state;

    private ImageDto profileImage;
    private LocalDate dateOfRegistration;

    private boolean isViewer;
    private boolean isViewerAdmin;
    private boolean isFollowed;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .username(user.getUsername())
                .role(user.getRole())
                .state(user.getState())
                .profileImage(ImageDto.from(user.getProfileImage()))
                .recipeNum(user.getRecipes().size())
                .subscribersNum(user.getSubscribers().size())
                .dateOfRegistration(user.getDateOfRegistration())
                .isViewer(true)
                .isFollowed(false)
                .isViewerAdmin(false)
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    public boolean isViewer() {
        return isViewer;
    }

    public boolean isViewerAdmin() {
        return isViewerAdmin;
    }

    public boolean isFollowed() {
        return isFollowed;
    }
}
