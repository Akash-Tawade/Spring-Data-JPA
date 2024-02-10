package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Course;
import com.springdatajpa.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course courseDSA = Course.builder()
                .title("DSA")
                .credit(5)
                .build();

        Course courseJAVA = Course.builder()
                .title("JAVA")
                .credit(10)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Akshay")
                .lastName("Chougule")
                //.courses(List.of(courseDSA,courseJAVA))
                .build();

        teacherRepository.save(teacher);
    }

}