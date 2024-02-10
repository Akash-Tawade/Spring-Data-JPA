package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Guardian;
import com.springdatajpa.springdatajpa.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("akash@gmail.com")
                .firstName("Akash")
                .lastName("Tawade")
                //.guardianName("Akki")
                //.guardianEmail("Akki@gmail.com")
                //.guardianMobile("1234567890")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveGuardianWIthStudent(){
        Guardian guardian = Guardian.builder()
                .email("prakash@gmail.com")
                .mobile("1234567890")
                .name("Akki")
                .build();

        Student student = Student.builder()
                .emailId("aniket@gmail.com")
                .firstName("Aniket")
                .lastName("Patil")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void getAllStudent(){
        List<Student> studentList = studentRepository.findAll();

        System.out.println("StudentList" + studentList);
    }

    @Test
    public void getStudentByFirstName(){
        List<Student> studentList = studentRepository.findByFirstName("Aniket");

        System.out.println("Students" + studentList);
    }

    @Test
    public void getStudentByFirstNameContaining(){
        List<Student> studentList = studentRepository.findByFirstNameContaining("ket");

        System.out.println("Students" + studentList);
    }

    @Test
    public void getStudentByGuardianName(){
        List<Student> studentList = studentRepository.findByGuardianName("Akki");

        System.out.println("Guarian List" + studentList);
    }

    @Test
    public void getStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("akash@gmail.com");

        System.out.println("Student" + student);
    }

    @Test
    public void getStudentFirstNameByEmailAddress(){
        String firstName = studentRepository.getStudentFirstNameByEmailAddress("akash@gmail.com");

        System.out.println("Student " + firstName);
    }

    @Test
    public void getStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("aniket@gmail.com");

        System.out.println("Student " + student);
    }

    @Test
    public void getStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("aniket@gmail.com");

        System.out.println("Student " + student);
    }

    @Test
    public void updateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId(
                "Aniket", "aniket@gmail.com"
        );
    }
}