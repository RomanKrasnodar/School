package com.roman.school.school.studentgenerator;

import com.roman.school.school.entity.Student;
import com.roman.school.school.heap.AllResources;
import com.roman.school.school.heap.ClassLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Component
public class GenerateStudents {

  public static final File surnamesFile = new File(
      "G:\\GitHub\\school\\src\\main\\resources\\generate students\\Surnames.txt");
  public static final File manNamesFile = new File(
      "G:\\GitHub\\school\\src\\main\\resources\\generate students\\Man names.txt");
  public static final File womanNamesFile = new File(
      "G:\\GitHub\\school\\src\\main\\resources\\generate students\\Woman names.txt");
  public static List<String> surnames = new ArrayList<>();
  public static List<String> manNames = new ArrayList<>();
  public static List<String> womanNames = new ArrayList<>();
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd",
      Locale.ENGLISH);
  public static Date minAgeForEnterASchool;
  public static Date maxAgeForEnterASchool;

//  @Autowired
//  Student student;

  static {
    try {
      minAgeForEnterASchool = DATE_FORMAT.parse("2014.02.20");
      maxAgeForEnterASchool = DATE_FORMAT.parse("2003.02.20");

    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public GenerateStudents() {
    this.initializeAllListsFromFiles();
  }

  public List<String> listFromFile(File file) {
    List<String> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line = reader.readLine();
      while (!(line == null)) {
        list.add(line);
        line = reader.readLine();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  public void initializeAllListsFromFiles() {
    surnames = listFromFile(surnamesFile);
    manNames = listFromFile(manNamesFile);
    womanNames = listFromFile(womanNamesFile);
  }

  public static boolean generateGender() {
    double random = Math.random() * 2;
    if (random < 1) {
      return true;
    } else {
      return false;
    }
  }

  public static int randomIndex(List list) {
    return (int) (Math.random() * list.size());
  }

  public String generateName(boolean isMan) {
    String name;
    if (isMan) {
      name = manNames.get(randomIndex(manNames));
    } else {
      name = womanNames.get(randomIndex(womanNames));
    }
    return name;
  }

  public String generateSurname(boolean isMan) {
    String surname = surnames.get(randomIndex(surnames));
    if (!isMan) {
      if (surname.endsWith("в") || surname.endsWith("н")) {
        return surname + "а";
      } else if (surname.endsWith("ой") || surname.endsWith("ий") || surname.endsWith("ый")) {
        return surname.substring(0, surname.length() - 2) + "ая";
      }
    }
    return surname;
  }

  public String generatePatronymic(boolean isMan) {
    String pathronymic = manNames.get(randomIndex(manNames));
    if (pathronymic.endsWith("й") || pathronymic.endsWith("а") || pathronymic.endsWith("ь")) {
      pathronymic = pathronymic.substring(0, pathronymic.length() - 1);
      if (isMan) {
        return pathronymic + "евич";
      } else {
        return pathronymic + "евна";
      }
    }
    if (isMan) {
      return pathronymic + "ович";
    } else {
      return pathronymic + "овна";
    }
  }

  public String generateFullName() {
    boolean isMan = generateGender();
    String name = this.generateName(isMan);
    String surname = this.generateSurname(isMan);
    String patronymic = this.generatePatronymic(isMan);
    return surname + " " + name + " " + patronymic;
  }

//  public void generateStudents(int quantityOfStudents) {
//    for (int i = 0; i < quantityOfStudents; i++) {
//      String fullName = generateFullName();
//      LocalDate birthDate = generateBirthDate();
//      String formattedBirthDate = DATE_FORMAT.format(birthDate);
//      System.out.println(fullName + " " + formattedBirthDate);
//    }
//  }

  public List<Student> generateStudents(int quantityOfStudents){
    ArrayList<Student> students= new ArrayList<>();
    for(int i=0; i<quantityOfStudents; i++){
        students.add(this.generateOneStudent());
    }
    return students;
  }

//  public String generateOneStudent() {
//    String fullName = generateFullName();
//    Date birthDate = generateBirthDate();
//    String formattedBirthDate = DATE_FORMAT.format(birthDate);
//
//    return fullName + " - " + formattedBirthDate;
//  }

  public Student generateOneStudent() {
    String fullName = generateFullName();
    LocalDate birthDate = generateBirthDate();
    Student student = new Student(fullName,birthDate);
//    student.setName(fullName);
//    student.setBirthDate(birthDate);
    return student;
  }

  public LocalDate generateBirthDate() {
    long minDate = minAgeForEnterASchool.getTime();
    long maxDate = maxAgeForEnterASchool.getTime();
    long randomTimeInMs = (long) (Math.random() * ((maxDate - minDate) + 1)) + minDate;
    return new Date(randomTimeInMs).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

//  public void distributionOfAllStudentsByClassId() {
//    AllResources.classLVLWithStudents.forEach(classLVL -> {
//      if (!classLVL.getName().equals("nonClassLVL")) {
//        ClassLevel.distributionStudentsInClassLvlByClassId(classLVL);
//        classLVL.getClassIDListInClassLevel().forEach(classID -> {
//          AllResources.classIDListInAllSchool.add(classID);
//        });
//      }
//    });
//  }



//  public void writeGeneratingStudentsInFile(int quantityOfStudents, String path) {
//    File file = new File(path);
//    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//      for (int i = 0; i < quantityOfStudents; i++) {
//        String student = generateOneStudent();
//        writer.write(student);
//        if (i < quantityOfStudents - 1) {
//          writer.newLine();
//        }
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
}
