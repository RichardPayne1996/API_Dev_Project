package com.sparta.apidev.webControllers;



import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.services.TraineeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    // create new one
    @GetMapping("/new")
    public String showCreateTraineeForm(Model model) {
        model.addAttribute("trainee",new Trainee());
        return "trainees/new";
    }
    // Save trainee
    @PostMapping("/save")
    public String saveTrainee(@ModelAttribute TraineeDTO traineeDTO) {
        traineeService.saveTrainee(
                traineeService
                        .saveTrainee(
                                traineeDTO
                        )

        );

        return "redirect:/trainees";
    }
    // View trainee details
    @GetMapping("/{id}")
    public String viewTrainee(
            @PathVariable int id,
            Model model
    ) {

        TraineeDTO trainee =
                traineeService.getTraineeById(id);

        model.addAttribute("trainee", trainee);

        return "trainees/view";
    }
    // Delete trainee
    @PostMapping("/{id}/delete")
    public String deleteTrainee(
            @PathVariable int id
    ) {

        traineeService.deleteTrainee(id);

        return "redirect:/trainees";
    }



}
