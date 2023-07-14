package com.syr.whispy.post.controller;

import com.syr.whispy.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

}
