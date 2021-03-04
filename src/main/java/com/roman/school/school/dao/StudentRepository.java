package com.roman.school.school.dao;

import com.roman.school.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

//    @Modifying
//    @Transactional
//    @Query("Tr table students")
//    void truncateTable();
    public List<Student> findAllByClassLevelName(String name);
}
