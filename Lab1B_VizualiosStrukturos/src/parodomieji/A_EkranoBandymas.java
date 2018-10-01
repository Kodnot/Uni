/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2013 09 09
 *
 *  Tai yra demonstracinė ekrano valdymo klasė,
 *    kurioje demonstruojami įvairūs darbo su ekranu ScreenKTU metodai.
 *  Atkreipkite dėmesį į refresh panaudojimo būtinybę, atnaujinant vaizdą.
 *  IŠBANDYKITE įvairius metodus, modifikuokite juos.
 *  EKSPERIMENTUOKITE su spalvomis ir ekrano formavimo algoritmais.
 *  SURAŠYKITE reikiamus veiksmus neužbaigtiems metodams.
 *************************************************************************** */
package parodomieji;

import java.awt.Color;
import static java.awt.Color.*;
import studijosKTU.ScreenKTU;

public class A_EkranoBandymas {

    // šiame metode demonstruojamos ekrano valdymo metodų ypatybės
    void demoĮvadinis() {
        // sukuriame naujus ekranus
        ScreenKTU sc1 = new ScreenKTU(ScreenKTU.Grid.ON);
        ScreenKTU sc2 = new ScreenKTU();
        sc1.printRowsColumnsNumbers();
        sc2.printRowsColumnsNumbers();
        // į pirmąjį surašome spalvotą tekstą
        sc1.setColors(Color.lightGray, Color.red);
        sc1.print(2, 2, "Labas ");
        sc1.setFontColor(Color.green);
        sc1.print(2, 8, "rytas " + sc1.SMILE1 + sc1.SMILE2);
        // išbandome spalvotų blokų kūrimą
        sc1.fillRect(4, 2, 3, 20);
        sc1.fillRect(7, 4, 3, 16, Color.yellow);
        sc1.setColors(Color.magenta, Color.blue);
        sc1.fillRect(10, 6, 3, 12, '+');
        sc1.fillBorder(4, 29, 6, 8, '#');
        sc1.fillBorder(13, 29, 6, 8, Color.CYAN);
        sc1.setColors(Color.black, Color.yellow);
        sc1.print(15, 2, 123456789);
        sc1.print(16, 2, Math.PI);
        sc1.refresh();
        // antrame ekrane išbandome linijinius blokus
        sc2.setColors(Color.pink, Color.green);
        sc2.lineBorder(2, 2, 10, 14, 3);
        sc2.lineBorder(3, 3, 8, 12, 2);
        sc2.lineBorder(4, 4, 6, 10, 1);
        sc2.print(6, 6, "TESTAS");
        // o čia optinė apgaulė - susikabinę stačiakampiai
        sc2.fillBorder(2, 20, 6, 8, Color.CYAN);
        sc2.fillBorder(5, 25, 8, 8, Color.YELLOW);
        sc2.fillBorder(10, 20, 6, 8, Color.GREEN);
        sc2.print(7, 25, Color.CYAN);
        sc2.print(10, 25, Color.YELLOW);
        // stačiakampius galima paversti tiesiog linijomis
        sc2.lineBorder(sc2.getHeight() - 2, 0, 1, sc2.getWidth(), 1);
        sc2.lineBorder(0, sc2.getWidth() - 2, sc2.getHeight(), 1, 1);
        sc2.refresh();
    }

    // pateikiame galimus vardinius spalbų tipus
    // spalvų vardai pateikiami be klasės vardo Color dėl statinio importo
    Color contrast(Color c) {
        int rgb = c.getRGB();
        int b = ((rgb & 0xFF) <= 0x7F) ? 0xFF : 0;
        int g = (((rgb >> 8) & 0xFF) <= 0x7F) ? 0xFF : 0;
        int r = ((rgb >> 16) <= 0x7F) ? 0xFF : 0;
        return new Color(r, g, b);
    }

    void demoSpalvųPaletė() {
        ScreenKTU scr = new ScreenKTU(24, 20, 18, 20, ScreenKTU.Fonts.boldB);
        scr.clearAll(blue);
        Color[] spalvos = {black, darkGray, gray, lightGray, red, magenta,
            blue, cyan, green, yellow, orange, pink, white};
        String[] spVardai = {"black   ", "darkGray", "gray    ", "lightGray",
            "red    ", "magenta", "blue   ", "cyan  ",
            "green ", "yellow", "orange", "pink ", "white"};
        for (int i = 0; i < spalvos.length; i++) {
            scr.setColors(spalvos[i], spalvos[(i + 7) % spalvos.length]);
            scr.setColors(spalvos[i], contrast(spalvos[i]));
//            scr.setColors(spalvos[i], i<7? white: black);
            scr.print(i + 1, 2, "" + i);
            scr.print(i + 1, 5, spVardai[i]);
        }
        scr.setColors(blue, yellow);
        scr.print(15, 1, "Iš viso turime");
        scr.print(16, 1, "13 vardinių spalvų");
        scr.refresh();
    }

    // metodas formuoja krepšinio varžybų tablo
    void demoKrepšinioTablo() {
        ScreenKTU scr = new ScreenKTU(24, 20, 18, 22, ScreenKTU.Fonts.boldB);
        int points1 = 86, points2 = 79;
        String team1 = "LITHUANIA", team2 = "BELGIUM";
        String player1 = "Kleiza", player2 = "Hervelle";
        scr.clearAll(blue);
        scr.setFontColor(red);
        scr.print(6, 5, "Best players");
        scr.setFontColor(green);
        scr.print(2, 6, "" + points1);
        scr.print(4, 1, team1);
        scr.print(7, 2, player1);
        scr.lineBorder(1, 5, 3, 4, 1);
        scr.setFontColor(yellow);
        scr.print(2, 14, "" + points2);
        scr.print(4, 12, team2);
        scr.print(7, 12, player2);
        scr.lineBorder(1, 13, 3, 4, 1);
        scr.fillRect(0, 0, 1, 3, yellow); // Lietuvos vėliava
        scr.fillRect(1, 0, 1, 3, green);
        scr.fillRect(2, 0, 1, 3, red);
        scr.fillRect(0, 19, 3, 1, black); // Belgijos vėliava
        scr.fillRect(0, 20, 3, 1, yellow);
        scr.fillRect(0, 21, 3, 1, red);
        scr.refresh();
    }

    // pasrinkite kitas varžybas ar kitokį renginį su tablo informacija
    void demoKitasTAblo() {
        // surašykite reikiamus veiksmus
    }

    // demonstruojama, kaip figūra kuriama santykinėmis koordinatėmis,
    // ekrane scr atžvilgiu atžvigiu viršutinio kairiojo kampo (eil, stu)
    void formuotiFigūrą(ScreenKTU sc, int eil, int stu, int nr) {
        sc.setColors(green, red);
        sc.fillRect(eil + 2, stu + 1, 3, 5);
        sc.fillRect(eil + 1, stu + 2, 5, 3);
        sc.print(eil + 3, stu + 3, "" + nr);
        sc.fillRect(eil + 6, stu + 3, 3, 1, gray);
    }

    // visa ekrano erdvė užpildoma figūromis
    void formuotiErdvę() {
        ScreenKTU scr = new ScreenKTU(12, 50);
        int kiekis = 33, figH = 8, figW = 5;
        for (int i = 0; i < kiekis; i++) {
            formuotiFigūrą(scr, i / 7 * (figH + 2), i % 7 * (figW + 2), i);
            scr.refresh();
        }
    }

    // demonstruojams ekrano pildymas įstrižainėmis
    void demoĮstrižainės() {
        int n = 80; // eilučių ir stulpelių skaičius ekrane 
        int h = 5;  // tarpas tarp įstrižainių
        ScreenKTU scr = new ScreenKTU(8, n);
        for (int k = h - 1; k < n; k += h) {
            for (int i = k, j = 0; i >= 0; j++, i--) {
                scr.print(i, j, Color.green);
                // palaipsniui atidenkite visas sekančias print eilutes
                scr.print(i, n - j - 1, Color.pink);
                scr.print(n - i - 1, n - j - 1, Color.red);
                scr.print(n - i - 1, j, Color.yellow);
                scr.refresh();  // pabandykite iškelti už skliausto }
            }
        }
    }

    void demoRombas() {
        int n = 80;
        // surašykite reikiamus veiksmus rombo sukūrimui
        ScreenKTU scr = new ScreenKTU(8, n);
        for (int i = n / 2; i < n; ++i) {
            scr.setColors(white, red);
            scr.fillRect(i, (i - n / 2), 1, (n - i) * 2, red);
            scr.fillRect(n-i, (i - n / 2), 1, (n - i) * 2, red);

            scr.refresh();
        }
    }

    void demoSpiralė() {
        // surašykite reikiamus veiksmus spiralės sukūrimui
    }

    void demoŠachmatųLenta() {
        // surašykite reikiamus veiksmus šachmatų lentos sukūrimui
    }

    // demonstuojamas informacijos kitimas laike
    void demoLaikrodis() {
        int vienaSekundė = 300;  // demonstracijai sutrumpiname trukmę (1sek = 300ms)
        ScreenKTU scr = new ScreenKTU(24, 20, 16, 30, ScreenKTU.Fonts.boldB);
        for (int laikas = 0; laikas < 100_000; laikas++) {
            if (laikas % 60 == 0) {
                scr.fillRect(3, 3, 6, 12, gray);
            }
            if (laikas / 60 == 10) {
                laikas = 0; // skaičiuojame tik 10 minučių
            }
            scr.setColors(yellow, red);
            String tarpinis0 = laikas % 60 < 10 ? "0" : "";
            scr.print(1, 3, "" + laikas / 60 + ":" + tarpinis0 + laikas % 60);
            scr.print(3 + laikas / 10 % 6, 3, "" + laikas / 10 % 6);

            scr.setColors(green, white);
            scr.print(3 + laikas / 10 % 6, 5 + laikas % 10, "" + laikas % 10);
            scr.refresh(vienaSekundė);
        }
    }

    // išbandykite įvairius demonstracinius metodus
    public static void main(String[] args) {
        A_EkranoBandymas d = new A_EkranoBandymas();
//        d.demoĮvadinis();
//        d.demoSpalvųPaletė();
//        d.demoKrepšinioTablo();
//        d.formuotiErdvę();
        d.demoĮstrižainės();
//        d.demoLaikrodis();
//        d.demoRombas();
    }
}
