package lab3liuberskis;

import java.util.Iterator;
import java.util.Locale;
import laborai.studijosktu.HashType;
import laborai.studijosktu.Ks;
import laborai.studijosktu.MapKTUx;

/**
 *
 * @author Kodnot
 */
public class StudentTests {

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        mapTests();
        performanceTests();
    }

    public static void mapTests() {
        Student a1 = new Student("John", "Snow", "844455978", 2, 7.9);
        Student a2 = new Student("John", "Peterson", "122244668", 3, 4.6);
        Student a3 = new Student("Pete", "Kunt", "128596668", 1, 9.6);
        Student a4 = new Student("Anthony Anderson 899977544 2 8.9");
        Student a5 = new Student("Klinton Blazy 851777544 3 4.9");
        Student a6 = new Student("Johny", "SonGoku", "122544668", 2, 6.6);
        Student a7 = new Student("Popo", "TheBlack", "198744668", 3, 5.3);

        String[] studIds = {"SD156", "SD102", "SD178", "SD171", "SD105", "SD106", "SD107", "SD108"};
        int id = 0;
        MapKTUx<String, Student> map = new MapKTUx<>(new String(), new Student(), HashType.DIVISION);

        // Reikšmių masyvas
        Student[] students = {a1, a2, a3, a4, a5, a6, a7};
        for (Student a : students) {
            map.put(studIds[id++], a);
        }
        map.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Ar egzistuoja pora atvaizdyje?");
        Ks.oun(map.contains(studIds[6]));
        Ks.oun(map.contains(studIds[7]));
        Ks.oun("Pašalinamos poros iš atvaizdžio:");
        Ks.oun(map.remove(studIds[1]));
        Ks.oun(map.remove(studIds[7]));
        map.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Atliekame porų paiešką atvaizdyje:");
        Ks.oun(map.get(studIds[2]));
        Ks.oun(map.get(studIds[7]));
        Ks.oun("Išspausdiname atvaizdžio poras String eilute:");
        Ks.ounn(map);
    }

    //Konsoliniame režime
    public static void performanceTests() {
        System.out.println("Greitaveikos tyrimas:\n");
        GreitaveikosTyrimas gt = new GreitaveikosTyrimas();
        //Šioje gijoje atliekamas greitaveikos tyrimas
        new Thread(() -> gt.pradetiTyrima(),
                "Greitaveikos_tyrimo_gija").start();
        try {
            String result;
            while (!(result = gt.getResultsLogger().take())
                    .equals(GreitaveikosTyrimas.FINISH_COMMAND)) {
                System.out.println(result);
                gt.getSemaphore().release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        gt.getSemaphore().release();
    }

}
