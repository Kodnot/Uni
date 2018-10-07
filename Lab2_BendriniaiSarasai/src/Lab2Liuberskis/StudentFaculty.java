/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Liuberskis;

import studijosKTU.ListKTUx;

/**
 *
 * @author Kodnot
 */
public class StudentFaculty {

    // Jesus f*cking christ this is a dumb interface...
    public ListKTUx<Student> allStudents = new ListKTUx<>(new Student());

    // None of this shit would be needed if I could use proper lists and stream API
    public ListKTUx<Student> filterStudentsByCourse(int course) {
        ListKTUx<Student> result = new ListKTUx<>(new Student());
        for (Student student : allStudents) {
            if (student.getCourse() == course) {
                result.add(student);
            }
        }
        return result;
    }

    public ListKTUx<Student> filterStudentsGradeAverage(double minAverage) {
        ListKTUx<Student> result = new ListKTUx<>(new Student());
        for (Student student : allStudents) {
            if (Double.compare(student.getLastYearGradeAverage(), minAverage) >= 0) {
                result.add(student);
            }
        }
        return result;
    }

    public ListKTUx<Student> getStudentsWithHighestGradeAverage() {
        double maxAverage = 0;
        for (Student student : allStudents) {
            if (maxAverage < student.getLastYearGradeAverage()) {
                maxAverage = student.getLastYearGradeAverage();
            }
        }
        return filterStudentsGradeAverage(maxAverage);
    }
}
