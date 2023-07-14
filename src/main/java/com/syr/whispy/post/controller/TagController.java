package com.syr.whispy.post.controller;

import com.syr.whispy.post.dto.TagCreateDto;
import com.syr.whispy.post.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
@Controller
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @GetMapping("/list")
    public String getAllTagList(Model model) {
        model.addAttribute("tags", tagService.findAll());
        return "tag/list";
    }

    @GetMapping("/create")
    public String createTag() {
        return "tag/create";
    }

    @PostMapping("/create")
    public String createTag(@Valid TagCreateDto dto) {
        tagService.create(dto.getName());
        return "redirect:/tag/list";
    }

}
