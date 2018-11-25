/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2liuberskis;

import laborai.studijosktu.BstSetKTU;
import laborai.studijosktu.SetADT;

/**
 *
 * @author Kodnot
 */
public class StudentAccounting {

    /*
    Returns a set of duplicate name + surname combinations from a given list of students
    */
    public static SetADT<String> fullNames(Student[] students) {
        SetADT<Student> unique = new BstSetKTU<>(Student.NameSurnameComparator);
        SetADT<String> duplicates = new BstSetKTU<>();
        for (Student a : students) {
            int sizeBefore = unique.size();
            unique.add(a);

            if (sizeBefore == unique.size()) {
                duplicates.add(a.getName() + " " + a.getSurname());
            }
        }
        return duplicates;
    }
}
