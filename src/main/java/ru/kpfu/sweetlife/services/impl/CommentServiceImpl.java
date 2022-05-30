package ru.kpfu.sweetlife.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.sweetlife.dto.CommentDto;
import ru.kpfu.sweetlife.dto.forms.CommentForm;
import ru.kpfu.sweetlife.models.Comment;
import ru.kpfu.sweetlife.models.User;
import ru.kpfu.sweetlife.repositories.CommentRepository;
import ru.kpfu.sweetlife.repositories.RecipeRepository;
import ru.kpfu.sweetlife.repositories.UserRepository;
import ru.kpfu.sweetlife.services.CommentService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Override
    public void saveComment(CommentForm form, Long recipeId, Long authorId) {
        if (form.getRating() != null && !form.getText().trim().isEmpty()) {
            Comment comment = Comment.builder()
                    .author(userRepository.getById(authorId))
                    .dateOfAdding(LocalDate.now())
                    .recipeRate(form.getRating())
                    .text(form.getText())
                    .recipe(recipeRepository.getById(recipeId))
                    .build();
            commentRepository.save(comment);
        }
    }

    @Override
    public void likeComment(Long commentId, Long userId) {
        User user = getUser(userId);
        Comment comment = getComment(commentId);
        if (!isLikedByUser(comment, user)) {
            saveLike(comment, user);
        }
    }

    @Override
    public void unfollowComment(Long commentId, Long userId) {
        User user = getUser(userId);
        Comment comment = getComment(commentId);
        if (isLikedByUser(comment, user)) {
            removeLike(comment, user);
        }
    }

    @Override
    public void setParams(CommentDto comment, Long userId) {
        User user = getUser(userId);
        Comment dbComment = getComment(comment.getId());
        comment.setLikedByCurrentUser(isLikedByUser(dbComment, user));
    }

    private void saveLike(Comment comment, User user) {
        user.getLikedComments().add(comment);
        userRepository.save(user);
    }

    private void removeLike(Comment comment, User user) {
        user.getLikedComments().remove(comment);
        userRepository.save(user);
    }

    private boolean isLikedByUser(Comment comment, User user) {
        return user.getLikedComments().contains(comment);
    }

    private User getUser(Long userId) {
        return userRepository.getById(userId);
    }

    private Comment getComment(Long commentId) {
        return commentRepository.getById(commentId);
    }
}
