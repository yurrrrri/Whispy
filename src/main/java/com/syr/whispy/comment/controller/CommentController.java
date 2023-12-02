package com.syr.whispy.comment.controller;

import com.syr.whispy.comment.dto.CommentCreateDto;
import com.syr.whispy.comment.dto.CommentUpdateDto;
import com.syr.whispy.comment.entity.Comment;
import com.syr.whispy.comment.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/create")
    public String create() {
        return "comment/create";
    }

    @PostMapping("/create")
    public String create(@Valid CommentCreateDto dto) {
        commentService.create(dto);
        return "redirect:/post/%d".formatted(dto.getPost().getId());
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Comment comment = commentService.findByIdAndGet(id);
        model.addAttribute("comment", comment);
        return "comment/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid CommentUpdateDto dto) {
        Comment comment = commentService.update(id, dto);
        return "redirect:/post/%d".formatted(comment.getPost().getId());
    }

    @GetMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id) {
        Long postId = commentService.softDelete(id);
        return "redirect:/post/%d".formatted(postId);
    }

    @GetMapping("/delete/hard/{id}")
    public String hardDelete(@PathVariable Long id) {
        Long postId = commentService.hardDelete(id);
        return "redirect:/post/%d".formatted(postId);
    }
}
