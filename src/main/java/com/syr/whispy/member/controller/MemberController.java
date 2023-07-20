package com.syr.whispy.member.controller;

import com.syr.whispy.member.entity.Follow;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.member.service.FollowService;
import com.syr.whispy.member.service.MemberService;
import com.syr.whispy.post.entity.Post;
import com.syr.whispy.post.entity.Tag;
import com.syr.whispy.post.service.PostService;
import com.syr.whispy.post.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr")
public class MemberController {

    private final MemberService memberService;
    private final FollowService followService;
    private final PostService postService;
    private final TagService tagService;

    @GetMapping("/login")
    public String login() {
        return "usr/login";
    }

    @GetMapping("/me")
    public String showMyPage(Model model, Principal principal) {
        Member member = memberService.findByUsernameAndGet(principal.getName());

        List<Post> posts = postService.findByWriter(member.getUsername());

        List<String> tagIdList = new ArrayList<>();
        posts.forEach(p -> tagIdList.addAll(p.getTags()));

        List<Tag> tags = new ArrayList<>();
        tagIdList.forEach(i -> tags.add(tagService.findByIdAndGet(i)));

        model.addAttribute("member", member);
        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);
        return "usr/me";
    }

    @GetMapping("/timeline")
    public String showMyTimeline(Model model, Principal principal) {
        Member member = memberService.findByUsernameAndGet(principal.getName());
        List<Follow> followings = followService.findByFromMemberId(member.getId());

        List<Post> posts = new ArrayList<>();
        followings.forEach(f -> posts.addAll(postService.findByWriter(f.getFollowedMember())));

        model.addAttribute("posts", posts);
        return "usr/timeline";
    }

}
