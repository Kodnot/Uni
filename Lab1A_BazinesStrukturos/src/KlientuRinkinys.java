
/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2010 08 28
 *
 * Sukuriami klasių Klijentas objektai. Imituojami Lietvoje žinomų bankų kodai.
 * Atliekamos manipuliacijos su sukurtais objektais.
 * Tiriamas klasių metodų veikimas.
 */
public class KlientuRinkinys {
// -----------------------------------------------------------------------------

    /**
     * Demonstravimui reikalingiems metodams komentarai yra nuimami. Laikinai
     * nereikalingiems - komentarai yra uždedami.
     */
    void metodoParinkimas() {
        System.out.println("****** Klasė KlijentuRinkinys **********");
//        met1PavieniaiKlientai();
//        met2KlientųMasyvas();
//        met3GeneruojamasKlientųMasyvas();
        met4();
    }
// -----------------------------------------------------------------------------

    /**
     * Sukuriami objektai, jie spausdinami ir skaičiuojamos reikšmės.
     */
    void met1PavieniaiKlientai() {
        System.out.println("===== metodas1 Pavieniai Klijentai");

        Klientas b1 = new Klientas("SEB268", 32, 443.60);
        Klientas b2 = new Klientas("SEB476", 42, 533.20);
        Klientas b3 = new Klientas("SWE293", 12, 23.10);

        // spausdinant suveikia metodas toString()
        System.out.println("Atskiras 1-as klijentas -> " + b1);
        System.out.println("Atskiras 2-as klijentas -> " + b2);
        System.out.println("Atskiras 3-as klijentas -> " + b3);

        double sumaInd = b1.getIndėlis() + b2.getIndėlis() + b3.getIndėlis();
        System.out.println("Indėlių suma =" + sumaInd);

        int sumaAmž = b1.getAmžius() + b2.getAmžius() + b3.getAmžius();
        System.out.println("Klijentų amžiaus vidurkis = " + sumaAmž / 3.0);

        String kodai1 = b1.getKodas() + " " + b2.getKodas() + " " + b2.getKodas();
        String kodai2 = "" + b1 + " || " + b2 + " || " + b3;
        System.out.println("kodai1 = " + kodai1);
        System.out.println("kodai2 = " + kodai2);

        // demonstruojamas aliasing efektas,
        // kai dviejų ar daugiau objektų rodyklės rodo į tą patį objektą
        b2 = b1;
        b1.keistiIndėlį(1000);  // pakeičiame tik vieno klijento indėlį
        System.out.println("1-as klijentas po keitimo b2=b1; -> " + b1);
        System.out.println("2-as klijentas po keitimo b2=b1; -> " + b2);
        System.out.println("3-as klijentas po keitimo b2=b1; -> " + b3);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------

    /**
     * Formuojamas klijentų objektų masyvas. Todėl jų analizei galima naudoti
     * ciklo sakinį.
     */
    void met2KlientųMasyvas() {
        System.out.println("===== metodas2 Klientų Masyvas ");
        Klientas[] klijentai = {
            new Klientas("kodas579", 19, 2310.55),
            new Klientas("kodas123", 29, 3100.00),
            new Klientas("kodasx29", 16, 90.45),
            // išbandykite ir su super klijentu
            new Klientas("super777", 64, 800_950_000.00),
            new Klientas("kodasy79", 35, 5610.11)
        };
        double suma = 0.0;
        for (Klientas k1 : klijentai) {
            System.out.println("Mūsų klijentai : " + k1);
            suma += k1.getIndėlis();
        }
        System.out.println("Visų klijentų skaičius =   " + klijentai.length);
        System.out.println("Visų klijentų indėlių suma =   " + String.format("%.4f", suma));
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------

    /**
     * Pagal tam tikrą dėsnį generuojame klijentų masyvą. Po to jis klonuojamas,
     * keičiamas indėlio dydis. Spausdinant nagrinėjame pasikeitimus.
     */
    void met3GeneruojamasKlientųMasyvas() {
        System.out.println("===== metodas3 Generuojamas Klijentų Masyvas ");
        Klientas[] a = new Klientas[10];
        // Generuojame 10 klijentų masyvą
        // Dirbtinis kodas didėja, amžius mažėja, indėlis didėja +11.25
        for (int i = 0; i < 10; i++) {
            a[i] = new Klientas("NORD" + i, 34 - i, 120 + i * 11.25);
        }
        // Masyvą B klonuojame, tuo tarpu rodyklės į objektus lieka tos pačios
        Klientas[] b = a.clone();
        // Todėl keičiant vieną, keičiasi ir kitas
        for (Klientas a1 : a) {
            a1.keistiIndėlį(100);
        }
        for (Klientas a1 : a) {
            System.out.println("Masyvas A : " + a1);
        }
        System.out.println("===================");
        for (Klientas a1 : b) {
            System.out.println("Masyvas B : " + a1);
        }
        double sumaIndėlių = 0;
        int sumaAmžiaus = 0;
        for (Klientas a1 : a) {
            sumaIndėlių += a1.getIndėlis();
            sumaAmžiaus += a1.getAmžius();
        }
        System.out.println("Visų klijentų indėlių suma = " + sumaIndėlių);
        System.out.println("Klijentų amžiaus vidurkis = "
                + (sumaAmžiaus / a.length));
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------

    /**
     * Suformuokite papildomus metodus, panaudojant klasės Klijentas objektus.
     */
    void met4() {
        System.out.println("===== metodas4 ... ");
        Klientas donatas = new Klientas("MRDONHOF69", 20, 0);
        System.out.println("Donatėlis: " + donatas);
        donatas.keistiIndėlį(100);
        System.out.println("Donatėlis po parčio: " + donatas);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }

    public static void main(String[] args) {
        new KlientuRinkinys().metodoParinkimas();
    }
}
