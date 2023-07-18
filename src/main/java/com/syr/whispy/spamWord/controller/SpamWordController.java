package com.syr.whispy.spamWord.controller;

import com.syr.whispy.spamWord.dto.SpamWordCreateDto;
import com.syr.whispy.spamWord.service.SpamWordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasRole('ROME_ADMIN')")
@RequiredArgsConstructor
@Controller
@RequestMapping("/spam")
public class SpamWordController {

    private final SpamWordService spamWordService;

    @GetMapping("/list")
    public String getAllSpamWordList(Model model) {
        model.addAttribute("spamWords", spamWordService.findAll());
        return "spam/list";
    }

    @GetMapping("/create")
    public String createSpamWord() {
        return "spam/create";
    }

    @PostMapping("/create")
    public String createSpamWord(@Valid SpamWordCreateDto dto) {
        spamWordService.create(dto);
        return "redirect:/spam/list";
    }

}
