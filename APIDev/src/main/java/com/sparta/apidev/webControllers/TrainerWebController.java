package com.sparta.apidev.webControllers;


import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.dtos.TrainerMapper;
import com.sparta.apidev.services.EnrollmentService;
import com.sparta.apidev.services.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trainers")
public class TrainerWebController {

    private final TrainerService trainerService;
    private final TrainerMapper trainerMapper;
    private  final EnrollmentService enrollmentService;

    public TrainerWebController(TrainerService trainerService,
                                TrainerMapper trainerMapper, EnrollmentService enrollmentService) {
        this.trainerService = trainerService;
        this.trainerMapper = trainerMapper;
        this.enrollmentService = enrollmentService;
    }


    @GetMapping("/view")
    public String viewTrainers(Model model) {

        model.addAttribute("trainers", trainerService.getAllTrainers());

        return "trainers/index";
    }

    @PostMapping("/save")
    public String saveTrainer(@ModelAttribute TrainerDTO trainerDTO){
        trainerService.saveTrainer(trainerMapper.toEntity(trainerDTO));
        return "redirect:/trainers/view";

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


    @GetMapping("/{id}/edit")
    public String editTrainerForm(@PathVariable int id, Model model) {

        TrainerDTO trainer = trainerService.getTrainerByID(id);
        System.out.println(trainer.getTrainerDob());

        model.addAttribute("trainer", trainer);

        return "trainers/edit";
    }


    @PostMapping("/{id}/update")
    public String updateTrainer(@PathVariable int id,
                                @ModelAttribute TrainerDTO trainerDTO) {

        trainerService.updateTrainer(id, trainerDTO);

        return "redirect:/trainers/view";
    }


    @PostMapping("/{id}/delete")
    public String deleteTrainer(@PathVariable int id) {

        trainerService.deleteTrainer(id);

        return "redirect:/trainers/view";
    }

    // Remove trainer from course
    @PostMapping("/remove")
    public String removeTrainer(@ModelAttribute TrainerDTO trainerDTO) {
        Integer courseId = trainerDTO.getSelectedCourseId();
        enrollmentService.removeTrainerFromCourse(trainerDTO, courseId);
        return "redirect:/trainers/view";
    }
}