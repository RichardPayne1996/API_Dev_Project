package com.sparta.apidev.webControllers;

import com.sparta.apidev.dtos.CourseDTO;
import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.services.CourseService;
import com.sparta.apidev.services.TraineeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseWebController {

    private final CourseService courseService;

    public CourseWebController(CourseService courseService) {
        this.courseService = courseService;
    }

    // View All Courses
    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute(
                "courses",
                courseService.getAllCourses()
        );

        return "courses/index";
    }
    // view one course
    @GetMapping("/{id}")
    public String viewCourse(
            @PathVariable int id,
            Model model
    ) {

        CourseDTO course =
                courseService.getCourseById(id);

        model.addAttribute("course", course);

        return "courses/view";
    }
    // create new course
    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute(
                "course",
                new CourseDTO()
        );

        return "courses/new";
    }
    // save course
    @PostMapping("/save")
    public String saveCourse(
            @ModelAttribute CourseDTO courseDTO
    ) {

        courseService.saveCourse(courseDTO);

        return "redirect:/courses";
    }
    // edit course
    @GetMapping("/{id}/edit")
    public String editCourse(
            @PathVariable int id,
            Model model
    ) {

        CourseDTO course =
                courseService.getCourseById(id);

        model.addAttribute("course", course);

        return "courses/edit";
    }
    // update course
    @PostMapping("/{id}/update")
    public String updateCourse(
            @PathVariable int id,
            @ModelAttribute CourseDTO courseDTO
    ){
        courseService.updateCourse(id, courseDTO);
        return "redirect:/courses";
    }
    // Delete course
    @PostMapping("/{id}/delete")
    public String deleteCourse(
            @PathVariable int id
    ){
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
