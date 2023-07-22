package com.syr.whispy.base.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToTimeline() {
        return "redirect:/usr/timeline";
    }
}
