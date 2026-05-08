package com.sparta.apidev.config;

import com.sparta.apidev.entities.Course;
import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.repositories.CourseRepository;
import com.sparta.apidev.repositories.TraineeRepository;
import com.sparta.apidev.repositories.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class AppConfig {

    @Bean
    @Transactional
    public CommandLineRunner loadData(TrainerRepository trRepo, TraineeRepository teRepo, CourseRepository cRepo){
        return args -> {
        System.out.println("Data Loader Running...");

        if (trRepo.count() == 0){
            var trainer1 = new Trainer("Cathy French", "01/01/1990", "cfrench@spartaglobal.com", "Mrs");
            var trainer2 = new Trainer("Phil Windridge", "02/02/1990", "pwindridge@spartaglobal.com", "Mr");

            trRepo.save(trainer1);
            trRepo.save(trainer2);
        }

        if (teRepo.count() == 0){
            var trainee1 = new Trainee("Ezra", LocalDate.of(2003, 6, 22), "eduncan@spartaglobal.com", "Mr");
            var trainee2 = new Trainee("Richard", LocalDate.of(1996, 7, 24), "rpayne@spartaglobal.com", "Mr");
            var trainee3 = new Trainee("Jacob", LocalDate.of(2001, 9, 26), "jreece@spartaglobal.com", "Mr");
            var trainee4 = new Trainee("Mohammed", LocalDate.of(1998, 9, 11), "mnasseri@spartaglobal.com", "Mr");
            var trainee5 = new Trainee("Pascal", LocalDate.of(2000, 5, 10), "ebibby@spartaglobal.com", "Mr");

            teRepo.save(trainee1);
            teRepo.save(trainee2);
            teRepo.save(trainee3);
            teRepo.save(trainee4);
            teRepo.save(trainee5);
        }

        if (cRepo.count() == 0){
            var course1 = new Course("TECH606", "Java Developers");
            var course2 = new Course("TECH605", "Java Testers");

            cRepo.save(course1);
            cRepo.save(course2);
        }
    };
}
}
