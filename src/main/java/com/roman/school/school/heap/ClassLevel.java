package com.roman.school.school.heap;

import com.roman.school.school.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ClassLevel {

    private String name;
    private String shortName;
    private List<Student> studentsInClassLvl = new ArrayList<>();
    private int numberOfClassesInClassLvl = 0;
    private List<ClassID> classIDListInClassLevel = new ArrayList<>();
    private List<String> classIdNamesInClassLevelList = new ArrayList<>();


    public ClassLevel() {
    }

    public ClassLevel(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public static int getAgeFromDate(LocalDate birthDate) {
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        int years = (int) ChronoUnit.YEARS.between(birthDate, now);
        System.out.println("Years " + years); // 17
        return years;
    }

    public static void getClassLVLFromAge(Student student) {
        int age = getAgeFromDate(student.getBirthDate());
        switch (age) {
            case (7):
                student.setClassLevelName(1);
                AllResources.classLVLWithStudents.get(0).getStudentsInClassLvl().add(student);
                break;
            case (8):
                student.setClassLevelName(2);
                AllResources.classLVLWithStudents.get(1).getStudentsInClassLvl().add(student);
                break;
            case (9):
                student.setClassLevelName(3);
                AllResources.classLVLWithStudents.get(2).getStudentsInClassLvl().add(student);
                break;
            case (10):
                student.setClassLevelName(4);
                AllResources.classLVLWithStudents.get(3).getStudentsInClassLvl().add(student);
                break;
            case (11):
                student.setClassLevelName(5);
                AllResources.classLVLWithStudents.get(4).getStudentsInClassLvl().add(student);
                break;
            case (12):
                student.setClassLevelName(6);
                AllResources.classLVLWithStudents.get(5).getStudentsInClassLvl().add(student);
                break;
            case (13):
                student.setClassLevelName(7);
                AllResources.classLVLWithStudents.get(6).getStudentsInClassLvl().add(student);
                break;
            case (14):
                student.setClassLevelName(8);
                AllResources.classLVLWithStudents.get(7).getStudentsInClassLvl().add(student);
                break;
            case (15):
                student.setClassLevelName(9);
                AllResources.classLVLWithStudents.get(8).getStudentsInClassLvl().add(student);
                break;
            case (16):
                student.setClassLevelName(10);
                AllResources.classLVLWithStudents.get(9).getStudentsInClassLvl().add(student);
                break;
            case (17):
                student.setClassLevelName(11);
                AllResources.classLVLWithStudents.get(10).getStudentsInClassLvl().add(student);
                break;
            default:
                student.setClassLevelName(-1);
                AllResources.classLVLWithStudents.get(11).getStudentsInClassLvl().add(student);
                break;
        }
    }

    public static List<ClassLevel> createClassLVLs() {
        List<ClassLevel> classLVLList = new ArrayList<>();
        classLVLList.add(new ClassLevel("firstClassLVL", "1"));
        classLVLList.add(new ClassLevel("secondClassLVL", "2"));
        classLVLList.add(new ClassLevel("thirdClassLVL", "3"));
        classLVLList.add(new ClassLevel("fourthClassLVL", "4"));
        classLVLList.add(new ClassLevel("fifthClassLVL", "5"));
        classLVLList.add(new ClassLevel("sixthClassLVL", "6"));
        classLVLList.add(new ClassLevel("seventhClassLVL", "7"));
        classLVLList.add(new ClassLevel("eighthClassLVL", "8"));
        classLVLList.add(new ClassLevel("ninthClassLVL", "9"));
        classLVLList.add(new ClassLevel("tenthClassLVL", "10"));
        classLVLList.add(new ClassLevel("eleventhClassLVL", "11"));
        classLVLList.add(new ClassLevel("nonClassLVL", "-1"));
        return classLVLList;

    }

    private int countNumberOfClasses() {
        int numberOfClasses = this.getStudentsInClassLvl().size() / AllResources.MAX_STUDENTS_IN_CLASS;
        if ((this.getStudentsInClassLvl().size() % AllResources.MAX_STUDENTS_IN_CLASS) > 0) {
            return numberOfClasses + 1;
        }
        return numberOfClasses;
    }

    private void createClassesInClassLvl(int numberOfClasses) {
        for (int i = 0; i < numberOfClasses; i++) {
            this.getClassIDListInClassLevel()
                    .add(new ClassID(this.getShortName() + AllResources.letters[i]));
        }
    }

    private List<Student> movingStudentsToClasses(List<Student> studentsInClassLevel) {
        List<Student> studentsWithClassIdName = new ArrayList<>();
        if (this.numberOfClassesInClassLvl == 0) {
            return studentsInClassLevel;
        }
        int indexCounter = 0;
        Student student = null;
        for (int i = 0; i < studentsInClassLevel.size(); i++) {
            student = studentsInClassLevel.get(i);
            student.setClassIdName(this.classIdNamesInClassLevelList.get(indexCounter));
            studentsWithClassIdName.add(student);
            if (indexCounter < numberOfClassesInClassLvl - 1) {
                indexCounter++;
            } else {
                indexCounter = 0;
            }
        }
        return studentsWithClassIdName;
    }


    public List<Student> distributionStudentsInClassLvlByClassId(List<Student> studentsInClassLevel) {
        this.setNumberOfClassesInClassLvl(this.countNumberOfClasses());
        this.createClassesInClassLvl(this.getNumberOfClassesInClassLvl());
        List<Student> studentsWithClassIdName = this.movingStudentsToClasses(studentsInClassLevel);
        return studentsWithClassIdName;
    }


//    private int countNumberOfClasses() {
//        int numberOfClasses = this.getStudentsInClassLvl().size() / AllResources.MAX_STUDENTS_IN_CLASS;
//        if ((this.getStudentsInClassLvl().size() % AllResources.MAX_STUDENTS_IN_CLASS) > 0) {
//            return numberOfClasses + 1;
//        }
//        return numberOfClasses;
//    }
//
//    private void createClassesInClassLvl(int numberOfClasses) {
//        for (int i = 0; i < numberOfClasses; i++) {
//            this.getClassIDListInClassLevel()
//                    .add(new ClassID(this.getShortName() + AllResources.letters[i]));
//        }
//    }
//
//    private void movingStudentsToClasses() {
//        if (this.numberOfClassesInClassLvl == 0) {
//            return;
//        }
//        int indexCounter = 0;
//        int allStudentsInClassLevel = this.getStudentsInClassLvl().size();
//        for (int i = 0; i < allStudentsInClassLevel; i++) {
//            this.getStudentsInClassLvl().get(i)
//                    .setClassIdName(this.getClassIDListInClassLevel().get(indexCounter).getName());
//            this.getClassIDListInClassLevel().get(indexCounter).getStudentsInClass()
//                    .add(this.getStudentsInClassLvl().get(i));
//            if (indexCounter < numberOfClassesInClassLvl - 1) {
//                indexCounter++;
//            } else {
//                indexCounter = 0;
//            }
//        }
//    }
//
//
//    public static void distributionStudentsInClassLvlByClassId(ClassLevel classLevel) {
//        classLevel.setNumberOfClassesInClassLvl(classLevel.countNumberOfClasses());
//        classLevel.createClassesInClassLvl(classLevel.getNumberOfClassesInClassLvl());
//        classLevel.movingStudentsToClasses();
//    }


//    private int countNumberOfClasses(List<Student> students) {
//        int numberOfClasses = students.size() / AllResources.MAX_STUDENTS_IN_CLASS;
//        if ((students.size() % AllResources.MAX_STUDENTS_IN_CLASS) > 0) {
//            return numberOfClasses + 1;
//        }
//        return numberOfClasses;
//    }
//
//    private void createClassesInClassLvl(int numberOfClasses) {
//        for (int i = 0; i < numberOfClasses; i++) {
//            this.getClassIdNamesInClassLevelList().add(this.getShortName() + AllResources.letters[i]);
////            this.getClassIDListInClassLevel()
////                    .add(new ClassID(this.getShortName() + AllResources.letters[i]));
//        }
//    }
//
//    private List<Student> movingStudentsToClasses(List<Student> students) {
//        ArrayList<Student> studentsInClassLevel = new ArrayList<>(students);
//        if (countNumberOfClasses(studentsInClassLevel) == 0) {
//            return studentsInClassLevel;
//        }
//        int indexCounter = 0;
//        for (int i = 0; i < studentsInClassLevel.size(); i++) {
//            studentsInClassLevel.get(i)
//                    .setClassIdName(this.classIdNamesInClassLevelList.get(indexCounter));
//            if (indexCounter < numberOfClassesInClassLvl - 1) {
//                indexCounter++;
//            } else {
//                indexCounter = 0;
//            }
//        }
//        return studentsInClassLevel;
//    }
//
//    public List<Student> distributionStudentsInClassLvlByClassId(List<Student> students) {
//        this.setNumberOfClassesInClassLvl(this.countNumberOfClasses(students));
//        this.createClassesInClassLvl(this.getNumberOfClassesInClassLvl());
//        return this.movingStudentsToClasses(students);
//    }

    public List<Student> getStudentsInClassLvl() {
        return studentsInClassLvl;
    }

    public void setStudentsInClassLvl(List<Student> studentsInClassLvl) {
        this.studentsInClassLvl = studentsInClassLvl;
    }

    public List<String> getClassIdNamesInClassLevelList() {
        return classIdNamesInClassLevelList;
    }

    public void setClassIdNamesInClassLevelList(List<String> classIdNamesInClassLevelList) {
        this.classIdNamesInClassLevelList = classIdNamesInClassLevelList;
    }


}
