package com.mainserver.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HelloController {

    @GetMapping("/index")
    public String index() {
        return "Hello My Server - " + LocalDateTime.now();
    }
}
