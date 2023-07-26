package com.syr.whispy.post.controller;

import com.syr.whispy.member.entity.Member;
import com.syr.whispy.member.service.MemberService;
import com.syr.whispy.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public String showMyEntirePostList(Model model, @AuthenticationPrincipal OAuth2User principal) {
        Member member = memberService.findByUsernameAndGet(principal.getName());
        model.addAttribute("posts", postService.findByWriter(member.getUsername()));
        return "post/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public String showPostDetail(@PathVariable String id, Model model) {
        model.addAttribute("post", postService.findByIdAndGet(id));
        return "post/detail";
    }

}
