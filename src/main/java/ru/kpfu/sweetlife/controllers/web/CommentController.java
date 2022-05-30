package ru.kpfu.sweetlife.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.sweetlife.dto.forms.CommentForm;
import ru.kpfu.sweetlife.security.details.UserDetailsImpl;
import ru.kpfu.sweetlife.services.CommentService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public String addComment(
            @ModelAttribute("form") CommentForm form,
            @RequestParam("recipeId") Long recipeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.saveComment(form, recipeId, userDetails.getUser().getId());
        return "redirect:/recipe/" + recipeId;
    }

    @PutMapping("/like")
    public void likeComment(
            @RequestParam("commentId") Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            HttpServletResponse response
    ) {
        commentService.likeComment(commentId, userDetails.getUser().getId());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PutMapping("/unfollow")
    public void unfollowComment(
            @RequestParam("commentId") Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            HttpServletResponse response
    ) {
        commentService.unfollowComment(commentId, userDetails.getUser().getId());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
