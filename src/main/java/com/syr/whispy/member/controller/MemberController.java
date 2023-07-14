package com.syr.whispy.member.controller;

import com.syr.whispy.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr")
public class MemberController {

    private MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "usr/login";
    }

}
