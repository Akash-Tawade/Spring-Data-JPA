package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByGuardianName(String guardianName);

    //JPQL
    @Query("Select t from Student t where t.emailId=?1")
    Student getStudentByEmailAddress(String emailId);

    //JPQL
    @Query("Select t.firstName from Student t where t.emailId=?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    //Native Query
    @Query( value = "Select * from tbl_student s where s.email_address = ?1", nativeQuery = true)
    Student getStudentByEmailAddressNative(String emailId);

    //Native Named Param
    @Query( value = "Select * from tbl_student s where s.email_address= :emailId", nativeQuery = true)
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query( value = "update tbl_student set first_name= ?1 where email_address= ?2", nativeQuery = true)
    int updateStudentNameByEmailId(String firstName, String emailId);
}
