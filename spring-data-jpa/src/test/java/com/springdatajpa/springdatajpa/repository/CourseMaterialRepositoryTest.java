package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Course;
import com.springdatajpa.springdatajpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial(){

        Course course = Course.builder()
                .credit(6)
                .title("python")
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.yahoo.com")
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);
    }

    @Test //This method fetch only course materials not courses
    public void printOnlyCourseMaterials(){
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();

        System.out.println("Course Materials " + courseMaterials);
    }
}