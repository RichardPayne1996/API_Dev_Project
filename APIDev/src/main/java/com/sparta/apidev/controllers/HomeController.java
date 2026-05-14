package com.sparta.apidev.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class HomeController {

    @GetMapping("/api")
    public RedirectView redirect() {
        return new RedirectView("/swagger-ui/index.html");
    }
}