package com.sparta.apidev.controllers;



import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.services.TraineeService;

import com.sparta.apidev.services.TrainerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {

    private final TraineeService traineeService;
    private final TrainerService trainerService;

    public HomeWebController(TraineeService traineeService, TrainerService trainerService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication auth) {

        String username = auth.getName();

        try {
            TraineeDTO trainee = traineeService.getTraineeByName(username);
            model.addAttribute("trainee", trainee);
        } catch (RuntimeException e) {
            TrainerDTO trainer = trainerService.getTrainerByName(username);
            model.addAttribute("trainee", trainer);
        }

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
