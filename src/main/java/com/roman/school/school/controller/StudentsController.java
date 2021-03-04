package com.roman.school.school.controller;

import com.roman.school.school.dao.StudentRepository;
import com.roman.school.school.entity.Student;
import com.roman.school.school.heap.AllResources;
import com.roman.school.school.heap.ClassLevel;
import com.roman.school.school.studentgenerator.GenerateStudents;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentsController {

    @Autowired
    StudentsController studentsController;

    @Autowired
    private GenerateStudents generateStudents;

    @Autowired
    private StudentRepository studentRepository;


    @PutMapping("/updateOneStudent")
    public Student updateStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return student;
    }

    @PostMapping("/students")
    public Student addNewStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return student;
    }

    @PostMapping("/addAllFromList")
    public List<Student> addAllStudentsFromList() {
        List<Student> allStudents = AllResources.allStudents;
        studentRepository.saveAll(allStudents);
        return allStudents;
    }

    @PostMapping("/generate/{number}")
    public void generateAndSaveStudentsByNumber(@PathVariable int number) {
        GenerateStudents generatorOfStudents = new GenerateStudents();
        studentRepository.saveAll(generatorOfStudents.generateStudents(number));
    }

    @GetMapping("/allStudents")
    public List<Student> showAllStudents() {
        List<Student> studentRepositoryAll = studentRepository.findAll();
        return studentRepositoryAll;
    }

//    @PutMapping("/moveStudentsToClasses")
//    public List<Student> moveAllStudentsToClasses() {
//        List<Student> allStudentsWithClassIdName = new ArrayList<>();
//        AllResources.classLVLWithStudents.forEach(classLevel -> {
//            List<Student> studentsInClassLevel = studentRepository.findAllByClassLevelName(classLevel.getShortName());
//            List<Student> studentsInClassLevelWithClassIdName = classLevel.distributionStudentsInClassLvlByClassId(studentsInClassLevel);
//            allStudentsWithClassIdName.addAll(studentsInClassLevelWithClassIdName);
//        });
//        studentRepository.saveAll(allStudentsWithClassIdName);
//
//        return allStudentsWithClassIdName;
//    }

    @PutMapping("/moveStudentsToClasses")
    public List<Student> moveAllStudentsToClasses() {
        List<Student> allStudentsWithClassIdName = new ArrayList<>();
        AllResources.classLVLWithStudents.forEach(classLevel -> {
            List<Student> studentsInClassLevel = classLevel.getStudentsInClassLvl();
            List<Student> studentsInClassLevelWithClassIdName = classLevel.distributionStudentsInClassLvlByClassId(studentsInClassLevel);
            allStudentsWithClassIdName.addAll(studentsInClassLevelWithClassIdName);
        });
//        studentRepository.saveAll(allStudentsWithClassIdName);
        allStudentsWithClassIdName.forEach(student -> {
        studentsController.updateStudent(student);
        });

        return allStudentsWithClassIdName;
    }







//        @GetMapping("/employees")
//        public List<Employee> showAllEmployees() {
//            List<Employee> allEmployees = employeeService.getAllEmployees();
//            return allEmployees;
//        }
//
//        @GetMapping("/employees/{id}")
//        public Employee getEmployee(@PathVariable int id) {
//            Employee employee = employeeService.getEmployee(id);
//            return employee;
//        }
//
//        @PostMapping("/employees")
//        public Employee addNewEmployee(@RequestBody Employee employee) {
//            employeeService.saveEmployee(employee);
//            return employee;
//        }
//
//        @PutMapping("/employees")
//        public Employee updateEmployee(@RequestBody Employee employee){
//            employeeService.saveEmployee(employee);
//            return employee;
//        }
//
//        @DeleteMapping("/employees/{id}")
//        public String deleteEmployee(@PathVariable int id){
//            employeeService.deleteEmployee(id);
//            return "Employee with ID = " + id + " was deleted";
//
//        }
//
//        @GetMapping("/employees/name/{name}")
//        public List<Employee> showAllEmployeesByName(@PathVariable String name){
//            List<Employee> employees = employeeService.findAllByName(name);
//            return employees;
//        }


}
