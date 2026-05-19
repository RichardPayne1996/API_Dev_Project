package com.sparta.apidev.webControllers;

import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.services.EnrollmentService;
import com.sparta.apidev.services.CourseService;
import com.sparta.apidev.services.TraineeService;
import com.sparta.apidev.services.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentWebController {

    private final EnrollmentService enrollmentService;
    private final CourseService courseService;
    private final TraineeService traineeService;
    private final TrainerService trainerService;

    public EnrollmentWebController(EnrollmentService enrollmentService,
                                   CourseService courseService,
                                   TraineeService traineeService,
                                   TrainerService trainerService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
    }

    // Show form to enroll trainee in a course
    @GetMapping("/trainee/new")
    public String showEnrollTraineeForm(Model model) {
        model.addAttribute("trainees", traineeService.getAllTrainees());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("traineeDTO", new TraineeDTO());
        return "enrollment/enroll-trainee";
    }

    // Submit trainee enrollment
    @PostMapping("/trainee/save")
    public String enrollTrainee(@ModelAttribute TraineeDTO traineeDTO,
                                @RequestParam("courseId") int courseId) {
        enrollmentService.enrollTraineeToCourse(traineeDTO, courseId);
        return "redirect:/trainees";
    }

    // Remove trainee from course
    @PostMapping("/trainee/remove")
    public String removeTrainee(@ModelAttribute TraineeDTO traineeDTO,
                                @RequestParam("courseId") int courseId) {
        enrollmentService.removeTraineeFromCourse(traineeDTO, courseId);
        return "redirect:/trainees";
    }

    // Show form to assign trainer to course
    @GetMapping("/trainer/new")
    public String showAssignTrainerForm(Model model) {
        model.addAttribute("trainers", trainerService.getAllTrainers());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("trainerDTO", new TrainerDTO());
        return "enrollment/assign-trainer";
    }

    // Submit trainer assignment
    @PostMapping("/trainer/save")
    public String assignTrainer(@ModelAttribute TrainerDTO trainerDTO,
                                @RequestParam("courseId") int courseId) {
        enrollmentService.assignTrainerToCourse(trainerDTO, courseId);
        return "redirect:/trainers" + courseId;
    }

    // Show form to remove trainer from course
    @GetMapping("/trainer/remove")
    public String showRemoveTrainerForm(Model model) {
        model.addAttribute("trainers", trainerService.getAllTrainers());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("trainerDTO", new TrainerDTO());
        return "enrollment/remove-trainer";
    }

    // Remove trainer from course
    @PostMapping("/trainer/remove")
    public String removeTrainer(@ModelAttribute TrainerDTO trainerDTO,
                                @RequestParam("courseId") int courseId) {
        enrollmentService.removeTrainerFromCourse(trainerDTO, courseId);
        return "redirect:/trainers";
    }
}
