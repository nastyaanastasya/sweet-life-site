package ru.kpfu.sweetlife.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ru.kpfu.sweetlife.models.Comment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    private String text;
    private int likesNumber;
    private int recipeRate;

    private boolean isLikedByCurrentUser;

    private LocalDate dateOfAdding;
    private UserDto author;

    public static CommentDto from(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .author(UserDto.from(comment.getAuthor()))
                .text(comment.getText())
                .likesNumber(comment.getLikedBy().size())
                .recipeRate(comment.getRecipeRate())
                .dateOfAdding(comment.getDateOfAdding())
                .isLikedByCurrentUser(false)
                .build();
    }

    public static List<CommentDto> from(@Nullable List<Comment> comments){
        return comments != null ? comments.stream()
                .map(CommentDto::from)
                .collect(Collectors.toList()) : new ArrayList<>();
    }
}
