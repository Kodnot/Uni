/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2013 09 02
 *
 * Tai yra demonstracinės pasisveikinimo klasės sekantis etapas.
 * Atsisakoma static metodų, todėl reikia sukurti vieną klasės objektą,
 * kas yra atliekama privalomame statiniame metode main.
 * Į tokio stiliaus programas galima tiesiog perkelti C programų tekstus.
   *  IŠBANDYKITE Java programą, kai sukuriamas naujas demonstracinis objektas.
   *  SURAŠYKITE reikiamus veiksmus neužbaigtame metode ir išbandykite.
   *  PAPILDYKITE naujais skaičiavimo metodais, 
   *              tiesiog perkeldami tekstą iš C kalbos pavyzdžių.
   ****************************************************************************/

public class B_Labas {
    void sveikintis(){
        System.out.println("LABAS pasauli ");
    }
    void sveikintis(String kalba){
        String atsakymas = null;
        switch(kalba){
            case "LTU": atsakymas = "Labas pasauli"; break;
            case "ENG": atsakymas = "Hello WORLD";   break;
            case "SWE": atsakymas = "Hallå världen"; break;
            case "GRE": atsakymas = "Γεια κόσμος";   break;
            case "FRA": atsakymas = "Bonjour tout le monde"; break;    
            case "RUS": atsakymas = "привет мир";    break;
            default:    atsakymas = "Atsiprašau - nesupratau";
        }   
        System.out.println("Kalba " + kalba + ":  " + atsakymas);
    }
    double skaičiuotiKambarioTūrį(double ilgis, double plotis, double aukštis){
        return ilgis * plotis * aukštis;
    }
    double atstumasTarpTaškų(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    int fibonačiSkaičius(int nr) {
        if (nr < 1) return -1;
        if (nr == 1 || nr == 2) return 1;
        int a = 1, b = 1;
        for (int i = 2; i < nr; ++i) {
            int c = a + b;
            b = a;
            a = c;
        }
        return a;
    }
    void demo(){
        sveikintis();
        sveikintis("SWE");
        sveikintis("GRE");
        sveikintis("LAT");
        sveikintis("FRA");
    }
    public static void main(String[] args) {
        B_Labas obj = new B_Labas();
        obj.demo();
        System.out.printf("10x5x6 kambario tūris: %.3f\n", obj.skaičiuotiKambarioTūrį(10, 5, 6));
        System.out.printf("Atstumas tarp taškų (3, 4) ir (19, 27): %.4f\n", obj.atstumasTarpTaškų(3, 4, 19, 27));
        System.out.println("Pirmi 10 fibonačio skaičių:");
        for (int i = 1; i <= 10; ++i) {
            System.out.println(obj.fibonačiSkaičius(i));
        }
    }    
}
