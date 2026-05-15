package com.sparta.apidev.webControllers;



import com.sparta.apidev.services.TraineeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/trainees")
public class TraineeWebController {
    private final TraineeService traineeService;
    public TraineeWebController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }
    //  Display all trainees
    @GetMapping
    public String getAllTrainees(Model model) {

        model.addAttribute(
                "trainees",
                traineeService.getAllTrainees()
        );


        return "trainees/index";
    }
    
}
