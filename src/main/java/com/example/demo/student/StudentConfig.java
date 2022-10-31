package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository repository) {
//        return args -> {
//            Student marian = new Student(
//                    "marian",
//                    24,
//                    LocalDate.of(2000, Month.DECEMBER, 5),
//                    "marian@gmail.com"
//            );
//
//            Student alex = new Student(
//                    "alex",
//                    24,
//                    LocalDate.of(2000, Month.DECEMBER, 5),
//                    "alex@gmail.com"
//            );
//
//            repository.saveAll(List.of(marian, alex));
//        };
//    }
}
