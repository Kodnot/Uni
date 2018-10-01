/*
 * DS LD1 part 1
 * Author: Audrius Liuberskis IFF-7/13
 */
package ld1port;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import java.util.stream.Stream;

/**
 *
 * @author audri
 */
public class StudentAnalyzer {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws ParseException, IOException {
        new StudentAnalyzer().executeLogic();
    }

    /**
     * Gets students from an input file, analyzes them and prints the results
     * @throws ParseException
     * @throws IOException 
     */
    public void executeLogic() throws ParseException, IOException {
        List<Student> students = readStudentData(Paths.get(System.getProperty("user.dir"), "L1Data.csv"));

        Student oldestStudent = getOldestStudent(students);
        int age = getDiffYears(oldestStudent.getBirthday(), Calendar.getInstance().getTime());
        System.out.printf("Vyriausias studentas: %s %s, amžius: %d\n", oldestStudent.getName(), oldestStudent.getSurname(), age);
        
        int mostCommonCourse = getMostCommonCourse(students);
        if (mostCommonCourse == -1)
            System.out.println("Populiariausio kurso nėra.\n");
        else 
            System.out.printf("Populiariausias kursas: %d, jame studentų: %d\n", mostCommonCourse, getCourseStudentCount(students, mostCommonCourse));
        
        List<Student> higherCourseStudents = getHigherCourseStudents(students);
        saveReportToFile(higherCourseStudents, Paths.get(System.getProperty("user.dir"), "Senbuviai.csv"));
        
        List<Student> fuxStudents = getFuxStudents(students);
        saveReportToFile(fuxStudents, Paths.get(System.getProperty("user.dir"), "Fuksai.csv"));
        
        createAReportTable(students, Paths.get(System.getProperty("user.dir"), "L1ReportTable.txt"));
    }

    /**
     * Reads input data from a given file and parses a student list form it
     * @param path  The path of the input file
     * @return A list of parsed students
     * @throws IOException
     * @throws ParseException 
     */
    private List<Student> readStudentData(Path path) throws IOException, ParseException {
        List<Student> students = new ArrayList<>();
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String line : lines) {
            StringTokenizer tokenizer = new StringTokenizer(line, ";");
            String surname = tokenizer.nextToken();
            String name = tokenizer.nextToken();
            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(tokenizer.nextToken());
            String studentId = tokenizer.nextToken();
            int course = Integer.parseInt(tokenizer.nextToken());
            String phone = tokenizer.nextToken();
            boolean isFux = Boolean.parseBoolean(tokenizer.nextToken());

            students.add(new Student(name, surname, birthday, studentId, course, phone, isFux));
        }

        return students;
    }

    /**
     * Creates a report from a given student list and prints it in a csv format
     * @param students A list of students to generate the report from
     * @param path The path of the result file
     * @throws IOException 
     */
    private void saveReportToFile(List<Student> students, Path path) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            if (students.isEmpty()) {
                writer.write("Pageidautas studentų sąrašas yra tuščias.\n");
            } else {
                writer.write("sep=;\n");
                writer.write("Pavardė;Vardas;Gimtadienis;Pažymėjimo nr.;Kursas;Telefono nr.;Yra \"fuksas\"\n");
                for (Student student : students) {
                    writer.write(String.format("%s;%s;%s;%s;%s;%s\n", student.getSurname(), student.getName(), DateFormat.getDateInstance(DateFormat.SHORT).format(student.getBirthday()),
                            student.getCardId(), student.getCourse(), student.getPhoneNumber(), student.isFux()));
                }
            }
        }
    }

    /**
     * Creates a report table from a given list of students and prints it to a text file
     * @param students A list of students to generate the report from
     * @param path The path of the result file
     * @throws IOException 
     */
    private void createAReportTable(List<Student> students, Path path) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("Studentų atstovybės narių sąrašas\n");
            String separatorLine = Stream.generate(() -> "-").limit(115).collect(Collectors.joining(""));
            // Or maybe this?
//            IntStream.range(0, 150).mapToObj(i -> "-").collect(Collectors.joining(""));
            writer.write(separatorLine + "\n");
            writer.write(String.format("| %-15s | %-15s | %-11s | %-24s | %-6s | %-10s | %-13s |\n", "Vardas", "Pavardė", "Gimtadienis", "Studento pažymėjimo nr.", "Kursas", "Tel. Nr.", "Yra \"fuksas\""));
            writer.write(separatorLine + "\n");

            for (Student student : students) {
                writer.write(String.format("| %-15s | %-15s | %-11s | %-24s | %-6s | %-10s | %-13s |\n", student.getName(), student.getSurname(), DateFormat.getDateInstance(DateFormat.SHORT, new Locale("LT")).format(student.getBirthday()),
                        student.getCardId(), student.getCourse(), student.getPhoneNumber(), student.isFux()));
                writer.write(separatorLine + "\n");
            }
        }
    }

    /**
     * Gets the oldes student 
     * @param students A list of students to analyze
     * @return 
     */
    private Student getOldestStudent(List<Student> students) {
        return students.stream().min((s1, s2) -> {
            return s1.getBirthday().compareTo(s2.getBirthday());
        }).get();
    }

    /**
     * Gets the most common course
     * @param students
     * @return 
     */
    private int getMostCommonCourse(List<Student> students) {
        Map<Integer, List<Student>> map = students.stream().collect(groupingBy(Student::getCourse));
        Map.Entry<Integer, List<Student>> maxEntry = map.entrySet().stream().max((x1, x2) -> {
            return Integer.compare(x1.getValue().size(), x2.getValue().size());
        }).get(); // don't use get, it's bad practice. Instead, throw a particular exception
        int mostCommonCourse = maxEntry.getKey();
        int courseStudentCount = maxEntry.getValue().size();

        int mostCommonCourseCount = (int)map.entrySet().stream().filter(x -> x.getValue().size() == courseStudentCount).count();
        return mostCommonCourseCount == 1 ? mostCommonCourse : -1;
    }
    
    /**
     * Gets the number of students present in a given course
     * @param students
     * @param course
     * @return 
     */
    private int getCourseStudentCount(List<Student> students, int course) {
        return (int)students.stream().filter(x -> x.getCourse() == course).count();
    }

    /**
     * Gets students who are in course 2 or higher
     * @param students
     * @return 
     */
    private List<Student> getHigherCourseStudents(List<Student> students) {
        return students.stream().filter(x -> x.getCourse() >= 2).collect(Collectors.toList());
    }

    /**
     * Gets a list of students who are "fuxes"
     * @param students
     * @return 
     */
    private List<Student> getFuxStudents(List<Student> students) {
        return students.stream().filter(x -> x.isFux()).collect(Collectors.toList());
    }

    /**
     * Gets year difference between two dates
     * @param first
     * @param last
     * @return 
     */
    private static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH)
                || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    /**
     * Gets a calendar instance with the given date set
     * @param date
     * @return 
     */
    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
