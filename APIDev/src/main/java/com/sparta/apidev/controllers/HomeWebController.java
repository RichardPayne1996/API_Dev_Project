package com.sparta.apidev.controllers;



import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.services.TraineeService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {

    private final TraineeService traineeService;

    public HomeWebController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication auth) {

        String username = auth.getName();

        TraineeDTO trainee = traineeService.getTraineeByName(username);

        model.addAttribute("trainee", trainee);

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
