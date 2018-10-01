/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 02
 *
 * Tai yra demonstracinė sveikintojo klasė, kuri turi savo laukus - 
 * sveikintojo vardą ir jam žinomą kalbą.
 * Klasė turi metodus, kurių vykdymas inicijuojamas main metode.
   *  IŠBANDYKITE pilnai objektinę Java programą.
   *  RASKITE klasės kode esančią klaidą.
   *  PAPILDYKITE pasisveikinimo metodą kitomis kalbomis.
   ****************************************************************************/

public class C_Sveikintojas {
    String vardas;   // sveikintojo vardas
    String kalba;    // jam žinoma kalba

    public C_Sveikintojas(String vardas, String kalba) {
        this.vardas = vardas;
        this.kalba = kalba;
    }
    void sveikintis(){
        String ats ;
        switch(kalba){
            case "LTU": ats = "Labas pasauli, aš esu " + vardas; break;
            case "ENG": ats = "Hello world, I am "     + vardas; break;
            case "SWE": ats = "Hallå världen, jag är " + vardas; break;
            case "GRE": ats = "Γεια κόσμος, είμαι ο "  + vardas; break;
            case "RUS": ats = "Привет мир, Я "         + vardas; break;
            case "AUE": ats = "Oy mate! Peeps call me " + vardas; break;
            default:    ats = "Atsiprašau - nesupratau";
        }   
        System.out.println(ats);
    }
    public static void main(String[] args) {
        C_Sveikintojas s0 = new C_Sveikintojas("Jonas",  "LTU");
        C_Sveikintojas s1 = new C_Sveikintojas("John",   "ENG");
        C_Sveikintojas s2 = new C_Sveikintojas("Gustav", "SWE");
        C_Sveikintojas s3 = new C_Sveikintojas("Eradonus", "GRE");
        C_Sveikintojas s4 = new C_Sveikintojas("Иван", "RUS");
        C_Sveikintojas s5 = new C_Sveikintojas("Steve", "AUE");
        s0.sveikintis();
        s1.sveikintis();
        s2.sveikintis();
        s3.sveikintis();
        s4.sveikintis();
        s5.sveikintis();
    }    
}
