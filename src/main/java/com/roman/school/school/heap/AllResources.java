package com.roman.school.school.heap;



import com.roman.school.school.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class AllResources {

  public static List<Student> allStudents = new ArrayList<>();
  public static List<ClassLevel> classLVLWithStudents = ClassLevel.createClassLVLs();
  public static final String ALL_STUDENTS_FILE_PATH = "G:\\IdeaProjects\\SchoolSceduleTest\\src\\main\\resources\\Students.txt";
  public static final String ALL_FAKE_STUDENTS_FILE_PATH = "G:\\IdeaProjects\\SchoolSceduleTest\\src\\main\\resources\\Generating students.txt";
  public static final String GENERATING_STUDENTS_FILE_PATH = "G:\\IdeaProjects\\SchoolSceduleTest\\src\\main\\resources\\Generating students.txt";
  public static final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T","U", "V", "W", "X", "Y", "Z"};
  public static List<ClassID> classIDListInAllSchool = new ArrayList<>();
  public static final int MAX_STUDENTS_IN_CLASS = 2;
}
