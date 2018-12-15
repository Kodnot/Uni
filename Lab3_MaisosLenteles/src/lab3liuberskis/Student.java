package lab3liuberskis;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import laborai.studijosktu.*;

/**
 *
 * @author Kodnot
 */
public final class Student implements KTUable {

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
        this();
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

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, phoneNumber, course, lastYearGradeAverage);
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

    @Override

    public String toString() {
        return name + "_" + surname + ": " + String.format("%-10s %4d %2.1f", phoneNumber, course, lastYearGradeAverage);
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

    public static class StudentBuilder {

        private final static Random RANDOM = new Random(1697);
        private final static String[] NAMES = {
            "John", "Pete", "Mark", "Don Xuli", "Anne",
            "Adolf", "Joseph", "Lenin", "Adam", "Blackbeard",
            "Donatas", "Arijus", "Tautvydas", "Karolis", "Eligijus",
            "Slipknot", "Sabaton", "Powerwolf", "Toh Kay", "Malukah"
        };

        private final static String[] SURNAMES = {
            "Anderson", "Common", "Zuch", "Kandan", "Frank",
            "Hitler", "Stalin", "Kunt", "Savage", "Rombudus",
            "Kurpys", "Casual", "Alibaba", "Samanuolis", "NoLike",
            "Kasamus", "Deph", "High", "Peterson", "The Inferior"
        };

        private String name = "";
        private String surname = "";
        private String phoneNumber = "";
        private int course = -1;
        private double lastYearGradeAverage = -1.0;

        public Student build() {
            return new Student(name, surname, phoneNumber, course, lastYearGradeAverage);
        }

        public Student buildRandom() {
            int nameIndex = RANDOM.nextInt(NAMES.length);
            int surnameIndex = RANDOM.nextInt(SURNAMES.length);
            String phoneNumber = "";
            for (int i = 0; i < 9; ++i) {
                phoneNumber += RANDOM.nextInt(10);
            }
            return new Student(NAMES[nameIndex], SURNAMES[surnameIndex], phoneNumber,
                    RANDOM.nextInt(Student.maxCourse) + 1, RANDOM.nextDouble() * 10);
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public StudentBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public StudentBuilder course(int course) {
            this.course = course;
            return this;
        }

        public StudentBuilder lastYearGradeAverage(double lastYearGradeAverage) {
            this.lastYearGradeAverage = lastYearGradeAverage;
            return this;
        }
    }

}
