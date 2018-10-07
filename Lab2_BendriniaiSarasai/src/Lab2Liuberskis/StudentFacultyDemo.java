/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Liuberskis;

import java.util.Locale;
import studijosKTU.Ks;
import studijosKTU.ListKTUx;

/**
 *
 * @author Kodnot
 */
public class StudentFacultyDemo {

    ListKTUx<Student> testStudents = new ListKTUx<>(new Student());

    private void executeLogic() {
//        demoStudents();
        formTestStudentList();
        printList();
        extendList();
        demoFacultyMethods();
        demoSorting();
        demoCustomMethods();
    }

    void demoStudents() {
        Student s1 = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        Student s2 = new Student("AAudrius", "Liuberskis", "811111111", 2, 8);
        Student s3 = new Student("AAAudrius Liuberskis 811111111 2 10");
        Student s4 = new Student();
        s4.parse("AAAAudrius Liuberskis 811111111 3 9");

        Ks.oun(s1);
        Ks.oun(s2);
        Ks.oun(s3);
        Ks.oun(s4);

        Ks.ouf("Studentų vidurkių vidurkis: %1.2f\n", (s1.getLastYearGradeAverage()
                + s2.getLastYearGradeAverage()
                + s3.getLastYearGradeAverage()
                + s4.getLastYearGradeAverage()) / 4);
    }

    void formTestStudentList() {
        Student s1 = new Student("Audrius", "Liuberskis", "811111111", 2, 6);
        Student s2 = new Student("AAudrius", "Liuberskis", "811111111", 3, 8);
        Student s3 = new Student("AAAudrius Liuberskis 811111111 2 9");

        testStudents.add(s1);
        testStudents.add(s2);
        testStudents.add(s3);
        testStudents.println("Pirmi 3 studentai");

        testStudents.add("Budrius Liuberskis 811111111 2 10");
        testStudents.add("BBudrius Liuberskis 811111111 1 7");
        testStudents.add("BBBudrius Liuberskis 811111111 5 4");

        testStudents.println("Visi 6 studentai");
        testStudents.forEach(System.out::println);
        Ks.ouf("Pirmų trijų studentų vidurkių vidurkis: %1.2f\n",
                (testStudents.get(0).getLastYearGradeAverage()
                + testStudents.get(1).getLastYearGradeAverage()
                + testStudents.get(2).getLastYearGradeAverage()) / 3);

        testStudents.add(0, new Student("Cudrius Liuberskis 811111111 5 10"));
        testStudents.add(6, new Student("Cudrius Liuberskis 811111111 5 10"));
        testStudents.set(2, s1);
        testStudents.println("Po modifikacijų");

        testStudents.remove(0);
        testStudents.remove(1);
        testStudents.println("Po šalinimų");

    }

    void printList() {
        int sk = 0;
        for (Student student : testStudents) {
            if (student.getName().charAt(0) == 'A') {
                sk++;
            }
        }
        Ks.oun("Studentų, kurių vardas prasideda raide A, yra: " + sk);
    }

    void extendList() {
        testStudents.add("Donatas AKuprys 866666666 1 6,2");
        testStudents.add("Donatas DKuprys 866666666 2 9,3");
        testStudents.add("Donatas BKuprys 866666666 3 6,2");
        testStudents.add("Eonatas ZKuprys 866666666 4 7,2");
        testStudents.add("EEonatas AKuprys 866666666 5 8,1");
        testStudents.add("EEEonatas Kuprys 866666666 6 6,0");

        testStudents.println("Testuojamų studentų sąrašas");
        testStudents.save("ban.txt");
    }

    void demoFacultyMethods() {
        StudentFaculty faculty = new StudentFaculty();
        faculty.allStudents.load("ban.txt");
        faculty.allStudents.println("Bandomasis rinkinys");

        testStudents = faculty.filterStudentsByCourse(2);
        testStudents.println("Antro kurso studentai");

        int sk = 0;
        for (Student student : testStudents) {
            sk++;
        }
        Ks.oun("Antro kurso studentų sk: " + sk);

        testStudents = faculty.filterStudentsGradeAverage(5);
        testStudents.println("Studentai su teigiamu vidurkiu");

        testStudents = faculty.getStudentsWithHighestGradeAverage();
        testStudents.println("Studentai su aukščiausiu vidurkiu");

    }

    void demoSorting() {
        StudentFaculty faculty = new StudentFaculty();
        faculty.allStudents.load("ban.txt");

        Ks.oun("--------" + faculty.allStudents.get(0));
        faculty.allStudents.println("Bandomasis rinkinys");
        faculty.allStudents.sortBuble(Student.NameSurnameComparator);
        faculty.allStudents.println("Pagal vardą ir pavardę");

        faculty.allStudents.sortBuble();
        faculty.allStudents.println("Pagal vidurkį");

        faculty.allStudents.sortBuble((s1, s2) -> Integer.compare(s1.getCourse(), s2.getCourse()));
        faculty.allStudents.println("Pagal kursą");

    }

    public static void main(String... args) {
        Locale.setDefault(new Locale("LT"));
        new StudentFacultyDemo().executeLogic();
    }

    private void demoCustomMethods() {
        testStudents.load("ban.txt");
        testStudents.println("Bandomasis rinkinys");
        
        testStudents.addLast(new Student("Cudrius Liuberskis 811111111 5 10"));
        testStudents.println("Pridėjus prie pabaigos");
        
        testStudents.removeLastOccurrence(new Student("Cudrius Liuberskis 811111111 5 10"));
        testStudents.println("Pašalinus last occurrence");
        
        testStudents.removeRange(1, 5);
        testStudents.println("Pašalinus [1, 5) el.");
        
        testStudents.removeRange(0, 4);
        testStudents.println("Pašalinus [0,4) el.");
        
        testStudents.removeRange(0, 4);
        testStudents.println("Pašalinus [0,4) el.");
    }

}
