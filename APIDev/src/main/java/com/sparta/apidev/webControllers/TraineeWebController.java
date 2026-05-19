package com.sparta.apidev.webControllers;

import com.sparta.apidev.dtos.TraineeDTO;
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

    // VIEW ALL
    @GetMapping
    public String getAllTrainees(Model model) {

        model.addAttribute(
                "trainees",
                traineeService.getAllTrainees()
        );

        return "trainees/index";
    }

    // VIEW ONE
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
    @GetMapping("/{id}/profile")
    public String profile(@PathVariable int id, Model model) {

        model.addAttribute(
                "trainee",
                traineeService.getTraineeById(id)
        );

        return "trainees/profile";
    }

    // SHOW CREATE FORM
    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute(
                "trainee",
                new TraineeDTO()
        );

        return "trainees/new";
    }

    // SAVE NEW TRAINEE
    @PostMapping("/save")
    public String saveTrainee(
            @ModelAttribute TraineeDTO traineeDTO
    ) {

        traineeService.saveTrainee(traineeDTO);

        return "redirect:/trainees";
    }

    // SHOW EDIT FORM
    @GetMapping("/{id}/edit")
    public String editTrainee(
            @PathVariable int id,
            Model model
    ) {

        TraineeDTO trainee =
                traineeService.getTraineeById(id);

        model.addAttribute("trainee", trainee);

        return "trainees/edit";
    }

    // UPDATE TRAINEE
    @PostMapping("/{id}/update")
    public String updateTrainee(
            @PathVariable int id,
            @ModelAttribute TraineeDTO traineeDTO
    ) {

        traineeService.updateTrainee(id, traineeDTO);

        return "redirect:/trainees";
    }

    // DELETE TRAINEE
    @PostMapping("/{id}/delete")
    public String deleteTrainee(
            @PathVariable int id
    ) {

        traineeService.deleteTrainee(id);

        return "redirect:/trainees";
    }
    @GetMapping("/search")
    public String searchTrainees(@RequestParam(required = false) String name,
                                 Model model) {

        List<TraineeDTO> trainees;

        if (name == null || name.isBlank()) {
            trainees = traineeService.getAllTrainees();
        } else {
            trainees = traineeService.searchTraineesByName(name);
        }

        model.addAttribute("trainees", trainees);
        model.addAttribute("searchName", name);

        return "trainees/index";
    }
}