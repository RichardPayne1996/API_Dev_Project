package com.sparta.apidev.config;

import com.sparta.apidev.entities.Course;
import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.repositories.CourseRepository;
import com.sparta.apidev.repositories.TraineeRepository;
import com.sparta.apidev.repositories.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

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
            var trainee1 = new Trainee("Ezra", "22/06/2003", "eduncan@spartaglobal.com", "Mr");
            var trainee2 = new Trainee("Richard", "24/07/1996", "rpayne@spartaglobal.com", "Mr");
            var trainee3 = new Trainee("Jacob", "26/09/2001", "jreece@spartaglobal.com", "Mr");
            var trainee4 = new Trainee("Mohammed", "11/09/1998", "mnasseri@spartaglobal.com", "Mr");
            var trainee5 = new Trainee("Pascal", "10/05/2000", "ebibby@spartaglobal.com", "Mr");

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
