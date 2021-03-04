package com.roman.school.school.heap;

import com.roman.school.school.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ClassID {

    private static List<ClassID> classIDList = new ArrayList<>();
    String name;
//    private Teacher classroomTeacher;
    private List<Student> studentsInClass = new ArrayList<>();

    public ClassID(String name) {
        this.name = name;
    }

    public ClassID(String name, List<Student> studentsInClass) {
        this.name = name;
        this.studentsInClass = studentsInClass;
    }

    public ClassID() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<ClassID> getClassIDList() {
        return classIDList;
    }

    public static void setClassIDList(List<ClassID> classIDList) {
        ClassID.classIDList = classIDList;
    }

    public List<Student> getStudentsInClass() {
        return studentsInClass;
    }

    public void setStudentsInClass(List<Student> studentsInClass) {
        this.studentsInClass = studentsInClass;
    }

    @Override
    public String toString() {
        return "ClassID{" +
                "name='" + name + '\'' +
                ", studentsInClass=" + studentsInClass +
                '}';
    }
}

