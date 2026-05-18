package com.sparta.apidev.webControllers;


import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.services.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trainers")
public class TrainerWebController {

    private final TrainerService trainerService;

    public TrainerWebController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    @GetMapping("/view")
    public String viewTrainers(Model model) {

        model.addAttribute("trainers", trainerService.getAllTrainers());

        return "trainers/index";
    }


    @GetMapping("/{id}")
    public String viewTrainer(@PathVariable int id, Model model) {

        TrainerDTO trainer = trainerService.getTrainerByID(id);

        if (trainer == null) {
            return "redirect:/trainers/view";
        }

        model.addAttribute("trainer", trainer);

        return "trainers/view";
    }


    @GetMapping("/new")
    public String newTrainerForm(Model model) {

        model.addAttribute("trainer", new TrainerDTO());

        return "trainers/new";
    }


    @GetMapping("/edit/{id}")
    public String editTrainerForm(@PathVariable int id, Model model) {

        TrainerDTO trainer = trainerService.getTrainerByID(id);

        model.addAttribute("trainer", trainer);

        return "trainers/edit";
    }


    @PostMapping("/edit/{id}")
    public String updateTrainer(@PathVariable int id,
                                @ModelAttribute TrainerDTO trainerDTO) {

        trainerService.updateTrainer(id, trainerDTO);

        return "redirect:/trainers/view";
    }


    @GetMapping("/delete/{id}")
    public String deleteTrainer(@PathVariable int id) {

        trainerService.deleteTrainer(id);

        return "redirect:/trainers/view";
    }
}