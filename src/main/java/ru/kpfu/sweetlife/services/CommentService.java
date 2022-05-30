package ru.kpfu.sweetlife.services;

import ru.kpfu.sweetlife.dto.CommentDto;
import ru.kpfu.sweetlife.dto.forms.CommentForm;

public interface CommentService {
    void saveComment(CommentForm form, Long recipeId, Long authorId);

    void likeComment(Long commentId, Long userId);
    void unfollowComment(Long commentId, Long userId);

    void setParams(CommentDto comment, Long userId);
}
