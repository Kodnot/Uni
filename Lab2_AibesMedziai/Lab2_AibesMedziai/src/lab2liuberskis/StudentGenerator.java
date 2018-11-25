/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2liuberskis;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import laborai.gui.MyException;

/**
 *
 * @author Kodnot
 */
public class StudentGenerator {

    private static Student[] students;
    private static int startIndex = 0, endIndex = 0;
    private static boolean isStart = true;

    public static Student[] generate(int count) {
        students = new Student[count];
        Student.StudentBuilder builder = new Student.StudentBuilder();
        for (int i = 0; i < count; i++) {
            students[i] = builder.buildRandom();
        }
        return students;
    }

    public static Student[] generateAndShuffle(int setSize,
            double distributionCoff) throws MyException {
        return generateAndShuffle(setSize, setSize, distributionCoff);
    }

    /**
     *
     * @param setSize
     * @param generatedSetSize
     * @param distributionCoff
     * @return Gražinamas aibesImtis ilgio masyvas
     * @throws MyException
     */
    public static Student[] generateAndShuffle(int setSize,
            int generatedSetSize, double distributionCoff) throws MyException {
        students = generate(setSize);
        return suffle(students, generatedSetSize, distributionCoff);
    }

    // Galima paduoti masyvą išmaišymui iš išorės
    public static Student[] suffle(Student[] studentBase,
            int count, double distributionCoff) throws MyException {
        if (studentBase == null) {
            throw new IllegalArgumentException("StudentBase is null");
        }
        if (count <= 0) {
            throw new MyException(String.valueOf(count), 1);
        }
        if (studentBase.length < count) {
            throw new MyException(studentBase.length + " >= " + count, 2);
        }
        if ((distributionCoff < 0) || (distributionCoff > 1)) {
            throw new MyException(String.valueOf(distributionCoff), 3);
        }

        int remainingCount = studentBase.length - count;
        int startIndex = (int) (remainingCount * distributionCoff / 2);

        Student[] initialStudents = Arrays.copyOfRange(studentBase, startIndex, startIndex + count);
        Student[] remainingStudents = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(studentBase, 0, startIndex)),
                        Arrays.stream(Arrays.copyOfRange(studentBase, startIndex + count, studentBase.length)))
                .toArray(Student[]::new);

        Collections.shuffle(Arrays.asList(initialStudents)
                .subList(0, (int) (initialStudents.length * distributionCoff)));
        Collections.shuffle(Arrays.asList(remainingStudents)
                .subList(0, (int) (remainingStudents.length * distributionCoff)));

        StudentGenerator.startIndex = 0;
        endIndex = remainingStudents.length - 1;
        StudentGenerator.students = remainingStudents;
        return initialStudents;
    }

    public static Student getFromBase() throws MyException {
        if ((endIndex - startIndex) < 0) {
            throw new MyException(String.valueOf(endIndex - startIndex), 4);
        }
        // Vieną kartą Automobilis imamas iš masyvo pradžios, kitą kartą - iš galo.     
        isStart = !isStart;
        return students[isStart ? startIndex++ : endIndex--];
    }
}
