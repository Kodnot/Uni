/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Liuberskis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import studijosKTU.ListKTUx;

/**
 *
 * @author Kodnot
 */
public class StudentFacultyTest {

    public StudentFacultyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of filterStudentsByCourse method, of class StudentFaculty.
     */
    @Test
    public void testFilterStudentsByCourse() {
        System.out.println("filterStudentsByCourse");
        int course = 2;
        StudentFaculty instance = new StudentFaculty();
        instance.allStudents.add("Audrius Liuberskis 811111111 2 10");
        instance.allStudents.add("AAudrius Liuberskis 811111111 3 10");
        instance.allStudents.add("AAAudrius Liuberskis 811111111 1 10");
        instance.allStudents.add("AAAAudrius Liuberskis 811111111 2 10");

        ListKTUx<Student> expResult = new ListKTUx<>(new Student());
        expResult.add("Audrius Liuberskis 811111111 2 10");
        expResult.add("AAAAudrius Liuberskis 811111111 2 10");

        ListKTUx<Student> result = instance.filterStudentsByCourse(course);
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.size(), 2);
        assertEquals(expResult.get(0), result.get(0));
        assertEquals(expResult.get(1), result.get(1));
    }

    /**
     * Test of filterStudentsGradeAverage method, of class StudentFaculty.
     */
    @Test
    public void testFilterStudentsGradeAverage() {
        System.out.println("filterStudentsGradeAverage");
        double minAverage = 6.0;
        StudentFaculty instance = new StudentFaculty();
        instance.allStudents.add("Audrius Liuberskis 811111111 2 5");
        instance.allStudents.add("AAudrius Liuberskis 811111111 3 8");
        instance.allStudents.add("AAAudrius Liuberskis 811111111 1 6");
        instance.allStudents.add("AAAAudrius Liuberskis 811111111 2 3");

        ListKTUx<Student> expResult = new ListKTUx<>(new Student());
        expResult.add("AAudrius Liuberskis 811111111 3 8");
        expResult.add("AAAudrius Liuberskis 811111111 1 6");

        ListKTUx<Student> result = instance.filterStudentsGradeAverage(minAverage);
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.size(), 2);
        assertEquals(expResult.get(0), result.get(0));
        assertEquals(expResult.get(1), result.get(1));
    }

    /**
     * Test of getStudentsWithHighestGradeAverage method, of class
     * StudentFaculty.
     */
    @Test
    public void testGetStudentsWithHighestGradeAverage() {
        System.out.println("getStudentsWithHighestGradeAverage");
        StudentFaculty instance = new StudentFaculty();
        instance.allStudents.add("Audrius Liuberskis 811111111 2 5");
        instance.allStudents.add("AAudrius Liuberskis 811111111 3 8");
        instance.allStudents.add("AAAudrius Liuberskis 811111111 1 6");
        instance.allStudents.add("AAAAudrius Liuberskis 811111111 2 8");

        ListKTUx<Student> expResult = new ListKTUx<>(new Student());
        expResult.add("AAudrius Liuberskis 811111111 3 8");
        expResult.add("AAAAudrius Liuberskis 811111111 2 8");

        ListKTUx<Student> result = instance.getStudentsWithHighestGradeAverage();
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.size(), 2);
        assertEquals(expResult.get(0), result.get(0));
        assertEquals(expResult.get(1), result.get(1));
    }

}
