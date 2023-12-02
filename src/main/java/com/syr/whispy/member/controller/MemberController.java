package com.syr.whispy.member.controller;

import com.syr.whispy.member.entity.Follow;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.member.service.FollowService;
import com.syr.whispy.member.service.MemberService;
import com.syr.whispy.post.entity.Post;
import com.syr.whispy.post.entity.Tag;
import com.syr.whispy.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr")
public class MemberController {

    private final MemberService memberService;
    private final FollowService followService;
    private final PostService postService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login() {
        return "usr/login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public String showMyPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        Member member = memberService.findByUsernameAndGet(principal.getName());

        List<Post> posts = postService.findByWriter(member);

        List<Tag> tags = new ArrayList<>();
        posts.forEach(p -> tags.addAll(p.getTags()));

        model.addAttribute("member", member);
        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);
        return "usr/me";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/timeline")
    public String showMyTimeline(Model model, @AuthenticationPrincipal OAuth2User principal) {
        Member member = memberService.findByUsernameAndGet(principal.getName());
        List<Follow> followings = followService.findByFromMember(member);

        List<Post> posts = new ArrayList<>();
        followings.forEach(f -> posts.addAll(postService.findByWriter(f.getFollowedMember())));

        Collections.sort(posts);

        model.addAttribute("posts", posts);
        return "usr/timeline";
    }
}
