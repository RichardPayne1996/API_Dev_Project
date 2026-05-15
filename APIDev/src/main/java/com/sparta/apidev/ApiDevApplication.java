package com.sparta.apidev;

import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.repositories.CourseRepository;
import com.sparta.apidev.repositories.TraineeRepository;
import com.sparta.apidev.repositories.TrainerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiDevApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ApiDevApplication.class, args);
        TraineeRepository teRepo = context.getBean(TraineeRepository.class);
        TrainerRepository trRepo = context.getBean(TrainerRepository.class);
        CourseRepository cRepo = context.getBean(CourseRepository.class);

        for (Trainer trainer : trRepo.findAll()){
            System.out.println(trainer.getTrainerEmail());
        }


    }

}
