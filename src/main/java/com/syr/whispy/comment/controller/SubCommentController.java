package com.syr.whispy.comment.controller;

import com.syr.whispy.comment.dto.SubCommentCreateDto;
import com.syr.whispy.comment.dto.SubCommentUpdateDto;
import com.syr.whispy.comment.entity.Comment;
import com.syr.whispy.comment.entity.SubComment;
import com.syr.whispy.comment.service.CommentService;
import com.syr.whispy.comment.service.SubCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@Controller
@RequestMapping("/sub-comment")
public class SubCommentController {

    private final SubCommentService subCommentService;
    private final CommentService commentService;

    @GetMapping("/create")
    public String create() {
        return "subComment/create";
    }

    @PostMapping("/create")
    public String create(@Valid SubCommentCreateDto dto) {
        subCommentService.create(dto);
        Comment comment = commentService.findByIdAndGet(dto.getComment());
        return "redirect:/post/%s".formatted(comment.getPost());
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model) {
        SubComment subComment = subCommentService.findByIdAndGet(id);
        model.addAttribute("subComment", subComment);
        return "subComment/update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid SubCommentUpdateDto dto) {
        SubComment subComment = subCommentService.update(dto);
        return "redirect:/post/%s".formatted(commentService.findByIdAndGet(subComment.getComment()).getPost());
    }

    @GetMapping("/delete/{id}")
    public String softDelete(@PathVariable String id) {
        String commentId = subCommentService.softDelete(id);
        return "redirect:/post/%s".formatted(commentService.findByIdAndGet(commentId).getPost());
    }

    @GetMapping("/delete/hard/{id}")
    public String hardDelete(@PathVariable String id) {
        String commentId = subCommentService.hardDelete(id);
        return "redirect:/post/%s".formatted(commentService.findByIdAndGet(commentId).getPost());
    }

}