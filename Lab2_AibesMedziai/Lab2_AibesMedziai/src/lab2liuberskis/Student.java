package lab2liuberskis;

import java.util.Comparator;
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
public final class Student implements KTUable<Student> {

    private static final int minCourse = 1;
    private static final int maxCourse = 6; // max 4 bachelor's and 2 master's years
    private static final String idCode = "SD";
    private static int studentNrCounter = 1;

    private String name;
    private String surname;
    private String phoneNumber;
    private int course;
    private double lastYearGradeAverage;
    private final String registrationId;

    public Student() {
        registrationId = idCode + (studentNrCounter++);
    }

    public Student(String name, String surname, String phoneNumber, int course, double lastYearGradeAverage) {
        this();
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

    /**
     * Compares students registration ID lexicographically
     *
     * @param e
     * @return
     */
    @Override
    public int compareTo(Student e) {
        if (e == null) {
            return -1;
        }
        return getRegistrationId().compareTo(e.getRegistrationId());
    }

    @Override
    public int hashCode() {
        long hash = 3;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.surname);
        hash = 61 * hash + Objects.hashCode(this.phoneNumber);
        hash = 6l * hash + Objects.hashCode(this.registrationId);
        hash = 61 * hash + this.course;
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.lastYearGradeAverage) ^ (Double.doubleToLongBits(this.lastYearGradeAverage) >>> 32));
        return (int) hash;
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

        // Only this should suffice, but meh
        if (this.registrationId != other.registrationId) {
            return false;
        }
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

    public final static Comparator<Student> AverageComparator = (Student s1, Student s2) -> {
        return Double.compare(s1.getLastYearGradeAverage(), s2.getLastYearGradeAverage());
    };

    @Override

    public String toString() {
        return getRegistrationId() + "=" + String.format("%-10s %-10s %-10s %4d %2.1f %s", name, surname, phoneNumber, course, lastYearGradeAverage, validate());
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

    public String getRegistrationId() {
        return registrationId;
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
