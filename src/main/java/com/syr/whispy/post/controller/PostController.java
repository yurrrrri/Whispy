package com.syr.whispy.post.controller;

import com.syr.whispy.member.entity.Member;
import com.syr.whispy.member.service.MemberService;
import com.syr.whispy.post.dto.PostCreateDto;
import com.syr.whispy.post.dto.PostUpdateDto;
import com.syr.whispy.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String showMyEntirePostList(Model model, @AuthenticationPrincipal OAuth2User principal) {
        Member member = memberService.findByUsernameAndGet(principal.getName());
        model.addAttribute("posts", postService.findByWriter(member));
        return "post/list";
    }

    @GetMapping("/{id}")
    public String showPostDetail(@PathVariable String id, Model model) {
        model.addAttribute("post", postService.findByIdAndGet(id));
        return "post/detail";
    }

    @GetMapping("/create")
    public String createPost() {
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(@Valid PostCreateDto dto) {
        postService.create(dto);
        return "redirect:/post/list";
    }

    @GetMapping("/update/{id}")
    public String updatePost(@PathVariable String id, Model model) {
        model.addAttribute("post", postService.findByIdAndGet(id));
        return "post/update";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable String id, @Valid PostUpdateDto dto) {
        postService.update(dto);
        return "redirect:/post/%s".formatted(id);
    }

    @GetMapping("/delete/{id}")
    public String softDeletePost(@PathVariable String id) {
        postService.softDelete(id);
        return "redirect:/post/list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete/hard/{id}")
    public String hardDeletePost(@PathVariable String id) {
        postService.hardDelete(id);
        return "redirect:/post/list";
    }
}
