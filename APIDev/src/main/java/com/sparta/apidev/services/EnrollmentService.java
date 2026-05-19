package com.sparta.apidev.services;

import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.entities.Course;
import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.repositories.CourseRepository;
import com.sparta.apidev.repositories.TraineeRepository;
import com.sparta.apidev.repositories.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    private final TraineeRepository traineeRepo;
    private final TrainerRepository trainerRepo;
    private final CourseRepository courseRepo;

    public EnrollmentService(TraineeRepository traineeRepo,
                             TrainerRepository trainerRepo,
                             CourseRepository courseRepo) {
        this.traineeRepo = traineeRepo;
        this.trainerRepo = trainerRepo;
        this.courseRepo = courseRepo;
    }

    // Enroll a trainee to a course
    @Transactional
    public void enrollTraineeToCourse(TraineeDTO traineeDTO, int courseId) {
        Trainee trainee = traineeRepo.findById(traineeDTO.getTraineeId())
                .orElseThrow(() -> new RuntimeException("Trainee not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        trainee.addCourse(course);
        course.addTrainee(trainee);

        traineeRepo.save(trainee);
        courseRepo.save(course);
    }

    // Remove a trainee from a course
    @Transactional
    public void removeTraineeFromCourse(TraineeDTO traineeDTO, int courseId) {
        Trainee trainee = traineeRepo.findById(traineeDTO.getTraineeId())
                .orElseThrow(() -> new RuntimeException("Trainee not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        trainee.dropCourse(course);
        course.deleteTrainee(trainee);

        traineeRepo.save(trainee);
        courseRepo.save(course);
    }

    // Assign a trainer to a course
    @Transactional
    public void assignTrainerToCourse(TrainerDTO trainerDTO, int courseId) {
        Trainer trainer = trainerRepo.findById(trainerDTO.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        trainer.getTeacherCourse().add(course);
        course.getTrainers().add(trainer);

        trainerRepo.save(trainer);
        courseRepo.save(course);
    }

    // Remove a trainer from a course
    @Transactional
    public void removeTrainerFromCourse(TrainerDTO trainerDTO, int courseId) {
        Trainer trainer = trainerRepo.findById(trainerDTO.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        trainer.getTeacherCourse().remove(course);
        course.getTrainers().remove(trainer);

        trainerRepo.save(trainer);
        courseRepo.save(course);
    }
}
