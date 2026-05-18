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
public class Config {

    @Bean
    @Transactional
    public CommandLineRunner loadData(TrainerRepository trRepo, TraineeRepository teRepo, CourseRepository cRepo){
        return args -> {
            System.out.println("Data Loader Running...");

            if (trRepo.count() == 0){
                var trainer1 = new Trainer("Cathy French", LocalDate.of(1990, 1, 1), "cfrench@spartaglobal.com", "Mrs", true);
                var trainer2 = new Trainer("Phil Windridge", LocalDate.of(1990,2,2), "pwindridge@spartaglobal.com", "Mr", true);

                trRepo.save(trainer1);
                trRepo.save(trainer2);
            }

            if (teRepo.count() == 0){
                var trainee1 = new Trainee("Ezra", LocalDate.of(2003, 6, 22), "email.com", "Mr", false);
                var trainee2 = new Trainee("Richard", LocalDate.of(1996, 7, 24), "aglobal.com", "Mr", false);
                var trainee3 = new Trainee("Jacob", LocalDate.of(2001, 9, 26), "jglobal.com", "Mr", false);
                var trainee4 = new Trainee("Mohammed", LocalDate.of(1998, 9, 11), "taglobal.com", "Mr", false);
                var trainee5 = new Trainee("Pascal", LocalDate.of(2000, 5, 10), "eobal.com", "Mr", false);

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

