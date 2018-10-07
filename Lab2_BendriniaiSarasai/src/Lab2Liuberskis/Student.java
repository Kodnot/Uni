/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Liuberskis;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import studijosKTU.KTUable;
import studijosKTU.Ks;

/**
 *
 * @author Kodnot
 */
public class Student implements KTUable<Student> {

    private static final int minCourse = 1;
    private static final int maxCourse = 6; // max 4 bachelor's and 2 master's years

    private String name;
    private String surname;
    private String phoneNumber;
    private int course;
    private double lastYearGradeAverage;

    public Student() {
    }

    public Student(String name, String surname, String phoneNumber, int course, double lastYearGradeAverage) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.course = course;
        this.lastYearGradeAverage = lastYearGradeAverage;
    }

    public Student(String dataString) {
        parse(dataString);
    }

    // Why the f*ck would I need a non-static factory method...
    @Override
    public Student create(String dataString) {
        return new Student(dataString);
    }

    @Override
    public String validate() {
        String error = "";
        if (name.isEmpty()) {
            error += "Nenustatytas studento vardas\n";
        }
        if (surname.isEmpty()) {
            error += "Nenustatyta studento pavardė\n";
        }
        if (course < minCourse || course > maxCourse) {
            error += "Nustatytas neegzistuojantis kursas\n";
        }
        if (lastYearGradeAverage < 0 || lastYearGradeAverage > 10) {
            error += "Nustatytas neegzistuojantis pažymių vidurkis\n";
        }
        return error;
    }

    @Override
    public void parse(String dataString) {
        try {
            Scanner scanner = new Scanner(dataString);
            name = scanner.next();
            surname = scanner.next();
            setPhoneNumber(scanner.next());
            setCourse(scanner.nextInt());
            setLastYearGradeAverage(scanner.nextDouble());
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie studentą: " + dataString);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie studentą: " + dataString);
        }
    }

    /**
     * Compares students by last year grade average. Students with higher
     * average go first
     *
     * @param e
     * @return
     */
    @Override
    public int compareTo(Student e) {
        if (e == null) {
            return -1;
        }

        double otherAverage = e.getLastYearGradeAverage();
        if (this.lastYearGradeAverage > otherAverage) {
            return -1;
        } else if (this.lastYearGradeAverage < otherAverage) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.surname);
        hash = 61 * hash + Objects.hashCode(this.phoneNumber);
        hash = 61 * hash + this.course;
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.lastYearGradeAverage) ^ (Double.doubleToLongBits(this.lastYearGradeAverage) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.course != other.course) {
            return false;
        }
        if (Double.doubleToLongBits(this.lastYearGradeAverage) != Double.doubleToLongBits(other.lastYearGradeAverage)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        return true;
    }

    public final static Comparator<Student> NameSurnameComparator
            = (Student s1, Student s2) -> {
                int cmp = s1.getName().compareTo(s2.getName());
                return cmp == 0 ? s1.getSurname().compareTo(s2.getSurname()) : cmp;
            };

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-10s %4d %2.1f %s", name, surname, phoneNumber, course, lastYearGradeAverage, validate());
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getCourse() {
        return course;
    }

    public double getLastYearGradeAverage() {
        return lastYearGradeAverage;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setLastYearGradeAverage(double lastYearGradeAverage) {
        this.lastYearGradeAverage = lastYearGradeAverage;
    }

}
