package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Course;
import com.springdatajpa.springdatajpa.entity.Student;
import com.springdatajpa.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getCourses(){
        List<Course> courses = courseRepository.findAll();

        System.out.println("Courses Available " + courses);
    }

    @Test
    public void saveCoursesWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Mayur")
                .lastName("Kalage")
                .build();

        Course course = Course.builder()
                .title("Rust")
                .credit(11)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPageWIthThreeRecords = PageRequest.of(1,2);

        List<Course> courseList = courseRepository.findAll(firstPageWithThreeRecords).stream().toList();
        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        long totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();

        System.out.println("Total Elements " + totalElements);
        System.out.println("Total Pages " + totalPages);
        System.out.println("Courses " + courseList);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle = PageRequest.of(0,2, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0,2,Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc = PageRequest.of(0,2,Sort.by("title").and(Sort.by("credit")));

        List<Course> coursesTitle = courseRepository.findAll(sortByTitle).stream().toList();

        List<Course> coursesCredit = courseRepository.findAll(sortByCreditDesc).stream().toList();

        List<Course> coursesTitleAndCredit = courseRepository.findAll(sortByTitleAndCreditDesc).stream().toList();

        System.out.println("Courses List based on Title " + coursesTitle);
        System.out.println("Courses List based on Credit" + coursesCredit);
        System.out.println("Courses List based on Title and Credit" + coursesTitleAndCredit);
    }

    @Test
    public void findByTitleContaining(){
        Pageable firstPageTenRecords = PageRequest.of(0,10);
        List<Course> courseList = courseRepository.findByTitleContaining("D",firstPageTenRecords).stream().toList();

        System.out.println("Courses List " + courseList);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Arjun")
                .lastName("Patil")
                .build();

        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishek@gmail.com")
                .build();
        Course course = Course.builder()
                .title("AI")
                .credit(15)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }


}