package com.syr.whispy.spamWord.controller;

import com.syr.whispy.spamWord.service.SpamWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
