
/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2013 09 02
 *                                                        modifikuota 2014 09 03
 * Tai demonstracinė Kasos klasė, kurioje imituojamas prekybos centro darbas.
 * Ji turi sumavimo metodus vienai prekei ir kelioms prekėms.
 *  IŠBANDYKITE pilnai objektinę Java programą.
 *  PAKEISKITE ir PAPILDYKITE duomenis.
 *  IŠBANDYKITE veikimą su reset metodu.
 *  TEISINGAI SUFORMUOKITE laukus prekiųKiekis ir pirkimųKiekis.
 *************************************************************************** */
public class D_Kasa {

    static int bendrasKasųKiekis;
    int kasosNr;        // identifikacinis kasos nr.
    double kaupiamaSuma;
    int prekiųKiekis;   // tolimesniuose metoduose užtikrinkite jų skaičiavimus
    int pirkimųKiekis;  // tolimesniuose metoduose užtikrinkite jų skaičiavimus

    public D_Kasa() {
        kasosNr = bendrasKasųKiekis++;
    }

    void sumuoti(double kaina) {  // perkama viena prekė
        kaupiamaSuma += kaina;
        prekiųKiekis++;
        pirkimųKiekis++;
        registruoti();
    }

    void sumuoti(double... kainos) { // metodas su kintamu argumentų skaičiumi
        for (double kaina : kainos) {
            kaupiamaSuma += kaina;
            prekiųKiekis++;
        }
        pirkimųKiekis++;
        registruoti();
    }

    void sumuoti(int kiekis, double kaina) { // perkamas kiekis tos pačios prekės
        kaupiamaSuma += kiekis * kaina;
        prekiųKiekis += kiekis;
        pirkimųKiekis++;
        registruoti();
    }

    void reset() {
        kaupiamaSuma = 0;
        prekiųKiekis = 0;
        pirkimųKiekis = 0;
    }

    void registruoti() {
        System.out.println("Kasa nr." + kasosNr + ": " + kaupiamaSuma + ". Prekių kiekis: " + prekiųKiekis + ", pirkimų kiekis: " + pirkimųKiekis);
    }

    static void demo1() {
        D_Kasa s0 = new D_Kasa();
        D_Kasa s1 = new D_Kasa();
        D_Kasa s2 = new D_Kasa();
        s0.sumuoti(11.99);
        s0.sumuoti(1.00, 3.00, 4.00, 2.00);
        s1.sumuoti(2, 0.91);
        s2.sumuoti(35.68);
        s0.sumuoti(20.00);
        s2.sumuoti(40.00);
        s0.sumuoti(5, 40.0);
        s1.sumuoti(6, 50.0);
        // Simbolis _ naudojamas didelių skaičių aiškumui pagerinti
        s2.sumuoti(100_000_000, 0.03);
        System.out.println("Iš viso sukaupta "
                + (s0.kaupiamaSuma + s1.kaupiamaSuma + s2.kaupiamaSuma));
    }

    static void demoSuMasyvu() {
        D_Kasa[] norfa = {
            new D_Kasa(), new D_Kasa(), new D_Kasa()};
        System.out.println("Pradeda veikti NORFOS prekybos centras");
        norfa[0].sumuoti(11.99);
        norfa[0].sumuoti(1.00, 3.00, 4.00, 2.00);
        norfa[1].sumuoti(2, 0.91);
        norfa[2].sumuoti(35.68);
        norfa[0].sumuoti(20.00);
        norfa[2].sumuoti(40.00);
        norfa[0].sumuoti(5, 40.0);
        norfa[1].sumuoti(6, 50.0);
        norfa[2].sumuoti(100_000, 0.03);
        norfa[2].reset();
        norfa[1].reset();
        norfa[2].sumuoti(100_000, 20);

        double išViso = 0.0;
        for (D_Kasa s : norfa) {
            išViso += s.kaupiamaSuma;
        }
        System.out.println("Norfoje sukaupta iš viso: " + išViso);
    }

    public static void main(String[] args) {
        demo1();
        demoSuMasyvu();
    }
}
