package lab2liuberskis;

import java.util.Iterator;
import java.util.Locale;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.BstSetKTUx;
import laborai.studijosktu.Ks;
import laborai.studijosktu.SetADT;
import laborai.studijosktu.SortedSetADTx;

/**
 *
 * @author Kodnot
 */
public class StudentTests {

    static Student[] studentsBase;
    static SortedSetADTx<Student> studentsByAverage = new BstSetKTUx(new Student(), Student.AverageComparator);

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        setTest();
    }

    public static void setTest() throws CloneNotSupportedException {
        Student a1 = new Student("John", "Snow", "844455978", 2, 7.9);
        Student a2 = new Student.StudentBuilder()
                .name("John")
                .surname("Peterson")
                .phoneNumber("122244668")
                .course(3)
                .lastYearGradeAverage(4.6)
                .build();
        Student a3 = new Student.StudentBuilder().buildRandom();
        Student a4 = new Student("Anthony Anderson 899977544 2 8.9");
        Student a5 = new Student("Klinton Blazy 851777544 3 4.9");
        Student a6 = new Student.StudentBuilder().buildRandom();
        Student a7 = new Student.StudentBuilder().buildRandom();
        Student a8 = new Student("Kunt Kerston 898477544 1 8.9");
        Student a9 = new Student.StudentBuilder().buildRandom();

        Student[] studentArray = {a9, a7, a8, a5, a1, a4, a6, a6};

        Ks.oun("Studentų Aibė:");
        SortedSetADTx<Student> studentSet = new BstSetKTUx(new Student());

        for (Student a : studentArray) {
            studentSet.add(a);
            Ks.oun("Aibė papildoma: " + a + ". Jos dydis: " + studentSet.size());
        }
        Ks.oun("");
        Ks.oun(studentSet.toVisualizedString(""));

        Ks.oun("Studentų aibės poaibis iki elemento " + a5.toString());
        SetADT<Student> studHeadSet = studentSet.headSet(a5);
        for (Student a : studHeadSet) {
            Ks.oun(a);
        }
        Ks.oun("");

        Ks.oun("Studentų aibės poaibis tarp lemento " + a5.toString() + " ir elemento " + a8.toString());
        SetADT<Student> studSubdSet = studentSet.subSet(a5, a8);
        for (Student a : studSubdSet) {
            Ks.oun(a);
        }
        Ks.oun("");

        Ks.oun("Studentų aibės poaibis nue elemento " + a8.toString());
        SetADT<Student> studTailSet = studentSet.tailSet(a8);
        for (Student a : studTailSet) {
            Ks.oun(a);
        }
        Ks.oun("");

        SortedSetADTx<Student> studentSetCopy
                = (SortedSetADTx<Student>) studentSet.clone();

        studentSetCopy.add(a2);
        studentSetCopy.add(a3);
        studentSetCopy.add(a4);
        Ks.oun("Papildyta studentų aibės kopija:");
        Ks.oun(studentSetCopy.toVisualizedString(""));

        a9.setCourse(2);

        Ks.oun("Originalas:");
        Ks.ounn(studentSet.toVisualizedString(""));

        Ks.oun("Ar elementai egzistuoja aibėje?");
        for (Student a : studentArray) {
            Ks.oun(a + ": " + studentSet.contains(a));
        }
        Ks.oun(a2 + ": " + studentSet.contains(a2));
        Ks.oun(a3 + ": " + studentSet.contains(a3));
        Ks.oun(a4 + ": " + studentSet.contains(a4));
        Ks.oun("");

        Ks.oun("Ar elementai egzistuoja aibės kopijoje?");
        for (Student a : studentArray) {
            Ks.oun(a + ": " + studentSetCopy.contains(a));
        }
        Ks.oun(a2 + ": " + studentSetCopy.contains(a2));
        Ks.oun(a3 + ": " + studentSetCopy.contains(a3));
        Ks.oun(a4 + ": " + studentSetCopy.contains(a4));
        Ks.oun("");

        Ks.oun("Elementų šalinimas iš kopijos. Aibės dydis prieš šalinimą:  " + studentSetCopy.size());
        for (Student a : new Student[]{a2, a1, a9, a8, a5, a3, a4, a2, a7, a6, a7, a9}) {
            studentSetCopy.remove(a);
            Ks.oun("Iš studentų aibės kopijos pašalinama: " + a + ". Jos dydis: " + studentSetCopy.size());
        }
        Ks.oun("");

        Ks.oun("Studentų aibė su iteratoriumi:");
        Ks.oun("");
        for (Student a : studentSet) {
            Ks.oun(a);
        }
        Ks.oun("");
        Ks.oun("Studentų aibė AVL-medyje:");
        SortedSetADTx<Student> studentSet3 = new AvlSetKTUx(new Student());
        for (Student a : studentArray) {
            studentSet3.add(a);
        }
        Ks.ounn(studentSet3.toVisualizedString(""));

        SetADT<String> studSet4 = StudentAccounting.fullNames(studentArray);
        Ks.oun("Pasikartojantys studentų vardai+pavardės:\n" + studSet4.toString());

        Ks.oun("Studentų aibė su iteratoriumi:");
        Ks.oun("");
        for (Student a : studentSet3) {
            Ks.oun(a);
        }

        Ks.oun("");
        Ks.oun("Studentų aibė su atvirkštiniu iteratoriumi:");
        Ks.oun("");
        Iterator<Student> iter = studentSet3.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Šalinimas su iteratoriumi:");
        Ks.oun("");
        Ks.ounn(studentSet3.toVisualizedString(""));
        iter = studentSet3.descendingIterator();
        while (iter.hasNext()) {
            Student val = iter.next();
            Ks.oun("Apdorojama " + val.toString());
            if (val.compareTo(a4) == 0 || val.compareTo(a5) == 0 || val.compareTo(a8) == 0) {
                Ks.oun("Pašalinta " + val.toString());
                iter.remove();
                Ks.ounn(studentSet3.toVisualizedString(""));
            }
        }

        Ks.oun("");
        Ks.oun("Studentų aibės toString() metodas:");
        Ks.ounn(studentSet3);

        // Išvalome ir suformuojame aibes skaitydami iš failo
        studentSet.clear();
        studentSet3.clear();

        Ks.oun("");
        Ks.oun("Studentų aibė DP-medyje:");
        studentSet.load("Duomenys\\ban.txt");
        Ks.ounn(studentSet.toVisualizedString(""));

        Ks.oun("");
        Ks.oun("Studentų aibė AVL-medyje:");
        studentSet3.load("Duomenys\\ban.txt");
        Ks.ounn(studentSet3.toVisualizedString(""));

        AVLSetRemoveTests();
    }

    public static void AVLSetRemoveTests() {
        Student a0 = new Student.StudentBuilder().buildRandom();
        Student a1 = new Student("John", "Snow", "844455978", 2, 7.9);
        Student a2 = new Student.StudentBuilder().buildRandom();
        Student a3 = new Student.StudentBuilder().buildRandom();
        Student a4 = new Student("Anthony Anderson 899977544 2 8.9");
        Student a5 = new Student("Klinton Blazy 851777544 3 4.9");
        Student a6 = new Student.StudentBuilder().buildRandom();

        Student[] studentArray = {a2, a1, a3, a4}; // Mind the order

        SortedSetADTx<Student> studentSet = new AvlSetKTUx(new Student());

        for (Student a : studentArray) {
            studentSet.add(a);
            Ks.oun("Aibė papildoma: " + a + ". Jos dydis: " + studentSet.size());
        }
        
        Ks.oun("AVLSet remove testai");
        Ks.oun(studentSet.toVisualizedString(""));
        Ks.oun("Išmetam kairį vaiką ir žiūrim balansą: (right right case)");
        studentSet.remove(a1);
        Ks.oun(studentSet.toVisualizedString(""));
        
        studentSet.add(a0);
        Ks.oun(studentSet.toVisualizedString(""));
        Ks.oun("Išmetam dešinį vaiką ir žiūrim balansą: (left left case)");
        studentSet.remove(a4);
        Ks.oun(studentSet.toVisualizedString(""));
        
        studentSet.add(a1);
        Ks.oun(studentSet.toVisualizedString(""));
        Ks.oun("Išmetam dešinį vaiką ir žiūrim balansą: (right left case)");
        studentSet.remove(a3);
        Ks.oun(studentSet.toVisualizedString(""));
        
        studentSet.remove(a2);
        studentSet.add(a3);
        studentSet.add(a2);
        Ks.oun(studentSet.toVisualizedString(""));
        Ks.oun("Išmetame kairį vaiką ir žiūrime balansą (left right case)");
        studentSet.remove(a0);
        Ks.oun(studentSet.toVisualizedString(""));
        
        Ks.oun("Išmetame dešinį vaiką, niekas nebalansuojama:");
        studentSet.remove(a3);
        Ks.oun(studentSet.toVisualizedString(""));
    }

}
