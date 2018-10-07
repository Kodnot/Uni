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
import studijosKTU.KTUable;

/**
 *
 * @author Kodnot
 */
public class StudentTest {

    public StudentTest() {
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
     * Test of create method, of class Student.
     */
    @Test
    public void create_whenPassedDataIsValid_ShouldCreateStudentWithValidData() {
        // Arrange
        System.out.println("create");
        String dataString = "Audrius Liuberskis 811111111 2 10";
        Student instance = new Student();

        // Act
        Student result = instance.create(dataString);

        // Assert
        assertEquals("Audrius", result.getName());
        assertEquals("Liuberskis", result.getSurname());
        assertEquals("811111111", result.getPhoneNumber());
        assertEquals(2, result.getCourse());
        assertEquals(10, result.getLastYearGradeAverage(), 0.1);
    }

    /**
     * Test of validate method, of class Student.
     */
    @Test
    public void validate_WhenInstanceIsValid_ShouldReturnEmptyString() {
        // Arrange
        System.out.println("validate");
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        String expResult = "";

        // Act
        String result = instance.validate();

        // Assert
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Student. Such tests should be written
     * for each error, but f*ck that
     */
    @Test
    public void validate_WhenInstanceIsInvalid_ShouldReturnError() {
        // Arrange
        System.out.println("validate");
        Student instance = new Student("", "Liuberskis", "811111111", 2, 10);
        String expResult = "Nenustatytas studento vardas\n";

        // Act
        String result = instance.validate();

        // Assert
        assertEquals(expResult, result);
    }

    @Test
    public void validate_WhenInstanceHasMultipleInvalidProperties_ShouldReturnCompoundError() {
        // Arrange
        System.out.println("validate");
        Student instance = new Student("", "Liuberskis", "811111111", 16, 10);
        String expResult = "Nenustatytas studento vardas\nNustatytas neegzistuojantis kursas\n";

        // Act
        String result = instance.validate();

        // Assert
        assertEquals(expResult, result);
    }

    /**
     * Test of parse method, of class Student.
     */
    @Test
    public void parse_WhenPassedDataIsValid_ShouldParseDataCorrectly() {
        // Arrange
        System.out.println("parse");
        String dataString = "Audrius Liuberskis 811111111 2 10";
        Student result = new Student();

        // Act
        result.parse(dataString);

        // Assert
        assertEquals("Audrius", result.getName());
        assertEquals("Liuberskis", result.getSurname());
        assertEquals("811111111", result.getPhoneNumber());
        assertEquals(2, result.getCourse());
        assertEquals(10, result.getLastYearGradeAverage(), 0.1);
    }

    @Test
    public void parse_WhenPassedDataIsInvalid_ShouldNotThrow() {
        // Arrange
        System.out.println("parse");
        String dataString = "Audrius Liuberskis 811111111 10";
        Student result = new Student();

        // Act, Assert
        try {
            result.parse(dataString);
        } catch (Exception e) {
            fail("No exception should have been thrown");
        }
    }

    /**
     * Test of compareTo method, of class Student.
     */
    @Test
    public void compareTo_WhenComparingToNull_ShoudReturnNegativeOne() {
        // Arrange
        System.out.println("compareTo");
        Student e = null;
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        int expResult = -1;

        // Act
        int result = instance.compareTo(e);

        // Assert
        assertEquals(expResult, result);
    }

    // TODO: This test should be parameterized, but it's a pain with JUnit
    @Test
    public void compareTo_WhenComparingNonNullInstances_ShoudCompareByGradeAverage() {
        // Arrange
        System.out.println("compareTo");
        Student e = new Student("Audrius", "Liuberskis", "811111111", 2, 8);
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        int expResult = -1;

        // Act
        int result = instance.compareTo(e);

        // Assert
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Student.
     */
    @Test
    public void toString_WhenCalled_ShouldReturnStringWithFormattedStudentData() {
        // Arrange
        System.out.println("toString");
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        String expResult = "Audrius    Liuberskis 811111111     2 10.0 ";

        // Act
        String result = instance.toString();

        // Assert
        assertEquals(expResult, result);
    }

    @Test
    public void toString_WhenCalledOnInvalidInstance_ShouldAppendError() {
        // Arrange
        System.out.println("toString");
        Student instance = new Student("", "Liuberskis", "811111111", 2, 10);
        String expResult = "           Liuberskis 811111111     2 10.0 Nenustatytas studento vardas\n";

        // Act
        String result = instance.toString();

        // Assert
        assertEquals(expResult, result);
    }

    // TODO: These tests should be refactored, but they are too trivial to bother.
    /**
     * Test of getName method, of class Student.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        String expResult = "Audrius";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSurname method, of class Student.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        String expResult = "Liuberskis";
        String result = instance.getSurname();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhoneNumber method, of class Student.
     */
    @Test
    public void testGetPhoneNumber() {
        System.out.println("getPhoneNumber");
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        String expResult = "811111111";
        String result = instance.getPhoneNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourse method, of class Student.
     */
    @Test
    public void testGetCourse() {
        System.out.println("getCourse");
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        int expResult = 2;
        int result = instance.getCourse();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastYearGradeAverage method, of class Student.
     */
    @Test
    public void testGetLastYearGradeAverage() {
        System.out.println("getLastYearGradeAverage");
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        double expResult = 10.0;
        double result = instance.getLastYearGradeAverage();
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of setPhoneNumber method, of class Student.
     */
    @Test
    public void testSetPhoneNumber() {
        System.out.println("setPhoneNumber");
        String phoneNumber = "222222222";
        Student instance = new Student("Audrius", "Liuberskis", "222222222", 2, 10);
        instance.setPhoneNumber(phoneNumber);
        assertEquals(instance.getPhoneNumber(), phoneNumber);
    }

    /**
     * Test of setCourse method, of class Student.
     */
    @Test
    public void testSetCourse() {
        System.out.println("setCourse");
        int course = 3;
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        instance.setCourse(course);
        assertEquals(instance.getCourse(), course);
    }

    /**
     * Test of setLastYearGradeAverage method, of class Student.
     */
    @Test
    public void testSetLastYearGradeAverage() {
        System.out.println("setLastYearGradeAverage");
        double lastYearGradeAverage = 9.5;
        Student instance = new Student("Audrius", "Liuberskis", "811111111", 2, 10);
        instance.setLastYearGradeAverage(lastYearGradeAverage);
        assertEquals(instance.getLastYearGradeAverage(), lastYearGradeAverage, 0.1);
    }

}
