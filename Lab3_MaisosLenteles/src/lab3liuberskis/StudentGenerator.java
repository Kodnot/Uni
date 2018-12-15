package lab3liuberskis;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import laborai.gui.MyException;

/**
 *
 * @author Kodnot
 */
public class StudentGenerator {

    private static final String idCode = "SD";
    private static int studentNrCounter = 1000;

    private static Student[] students;
    private static String[] keys;
    private int count = 0, idCount = 0;

    public static Student[] generateStudents(int count) {
        Student[] students = IntStream.range(0, count)
                .mapToObj(i -> new Student.StudentBuilder().buildRandom())
                .toArray(Student[]::new);
        Collections.shuffle(Arrays.asList(students));
        return students;
    }

    public static String[] generateStudentIds(int kiekis) {
        String[] keys = IntStream.range(0, kiekis)
                .mapToObj(i -> idCode + (studentNrCounter++))
                .toArray(String[]::new);
        Collections.shuffle(Arrays.asList(keys));
        return keys;
    }

    // TODO: Naming??
    public Student[] generateAndAssignStudents(int aibesDydis,
            int aibesImtis) throws MyException {
        if (aibesImtis > aibesDydis) {
            aibesImtis = aibesDydis;
        }
        students = generateStudents(aibesDydis);
        keys = generateStudentIds(aibesDydis);
        this.count = aibesImtis;
        return Arrays.copyOf(students, aibesImtis);
    }

    // Imamas po vienas elementas iš sugeneruoto masyvo. Kai elementai baigiasi sugeneruojama
    // nuosava situacija ir išmetamas pranešimas.
    public Student assignStudent() {
        if (students == null) {
            throw new MyException("carsNotGenerated");
        }
        if (count < students.length) {
            return students[count++];
        } else {
            throw new MyException("allSetStoredToMap");
        }
    }

    public String getStudentId() {
        if (keys == null) {
            throw new MyException("carsIdsNotGenerated");
        }
        if (idCount >= keys.length) {
            idCount = 0;
        }
        return keys[idCount++];
    }
}
