/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2010 08 28
 * 
 * Šioje klasėje išbandomi ir demonstruojami String klasės metodai.
 * Jų pagalba greitai ir patogiai galima atlikti įvairius veiksmus su tekstais.
 *
 */

import java.util.Scanner;
import java.util.StringTokenizer;

public class F_SimboliaiTekstai {
    /**
     * Demonstravimui reikalingiems metodams komentarai yra nuimami.
     * Laikinai nereikalingiems - komentarai yra uždedami.
     */
// -----------------------------------------------------------------------------
    void metodoParinkimas(){
        System.out.println("****** Klasė G_SimboliaiTekstai **********");
//        met1Pakeisti_MB_GB();
//        met2ElektroninisPaštas();
//        met3TarpSkliaustų();
//        met4SimboliųKodai();
//        met5StringTokenizer();
//        met6Scanner();
//        met7FormatuotiDuomenis();
        memifyString("Yo mamma is so fat, that she wouldn't fit into a modern PC heap.");
        removeEnglishVowels("Tai yra sakinys su ir lietuviškom, ir angliškom balsėmis");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomas teksto fragmentų pakeitimas.
     */
    void met1Pakeisti_MB_GB(){
        System.out.println("===== metodas3 Pakeisti_MB_GB ");

        String s="2.68MB 59MB 45 mB 99mb 7kb 11MBaitai";
        String t = s.replace("MB", "GB");
        System.out.println("pradinė:  "+s+
                         "\npakeista: "+t);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
    /**
     *  Išskiriamos atskiros elektroninio pašto adreso komponentės.
     */
// -----------------------------------------------------------------------------
    void met2ElektroninisPaštas(){
        System.out.println("===== metodas2 ElektroninisPaštas ");
        String eMail="lauras.lapelis@stud.ktu.lt";
//        String eMail="tarzanas94@yahoo.com"; // varijantas pakeitimui
        int k = eMail.indexOf('@');
        String vardas = eMail.substring(0, k);
        System.out.println(eMail+"\nVardas: "+vardas+"\n(@)indeksas "+k+
            "\nDomenas: "+eMail.substring(eMail.lastIndexOf('.')+1));

        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išskiriami teksto fragmentai, esantys tarp skliaustų.
     */
    void met3TarpSkliaustų(){
        System.out.println("===== metodas4 TarpSkliaustų ");

        String s="svoris(3,65kg)  ūgis(53cm)";
        int k1=s.indexOf('(');
        int k2=s.indexOf(')', k1);
        int k3=s.indexOf('(', k2);
        int k4=s.indexOf(')', k3);
        String t=s.substring(k1+1, k2)+" "+s.substring(k3+1, k4);
        System.out.println("Pradinė: "+s+
                  "\nTarp skliaustų: "+t);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
   /**
    * Demonstruojamas simbolių kodų gavimas ir jų reikšmės.
    * Atkreipkite dėmesį į didžiųjų ir mažųjų bei lietuviškų raidžių ypatybes.
    */
    void met4SimboliųKodai(){
        System.out.println("===== metodas1 - Analizuojame kodavimą");
        String e = "aAbBčČ";
        System.out.println("String e=\"aAbBčČ\";");
        System.out.println("Kodas "+e.substring(0,1)+"="+e.codePointAt(0));
        System.out.println("Kodas "+e.substring(1,2)+"="+e.codePointAt(1));
        System.out.println("Kodas "+e.substring(2,3)+"="+e.codePointAt(2));
        System.out.println("Kodas "+e.substring(3,4)+"="+e.codePointAt(3));
        System.out.println("Kodas "+e.substring(4,5)+"="+e.codePointAt(4));
        System.out.println("Kodas "+e.substring(5,6)+"="+e.codePointAt(5));
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomas teksto skaidymas į fragmentus skyriklių pagalba.
     * Po to teksto fragmentai traktuojami kaip savarankiškos eilutės.
     */
    void met5StringTokenizer(){
        System.out.println("===== metodas5 StringTokenizer ");
        String analizė="Simas-Jonas;  x=600+90, Mėnuo::::Gruodis=24 ++Adios--";
        System.out.println("Tekstas analizei -> "+ analizė);
        // pagal nutylėjimą skyriklis yra tarpas
        StringTokenizer p= new StringTokenizer(analizė);
        String p1=p.nextToken();
        String p2=p.nextToken();
        String p3=p.nextToken();
        System.out.println("Atskira dalelė (tarp tarpų) -> "+p1);
        System.out.println("Atskira dalelė (tarp tarpų) -> "+p2);
        System.out.println("Atskira dalelė (tarp tarpų) -> "+p3);
        String skyrikliai=" :;,";
        System.out.println("Analizuojame su kitais skyrikliais"+skyrikliai);
        StringTokenizer s= new StringTokenizer(analizė,skyrikliai);
        System.out.println("Atskira dalelė -> "+s.nextToken());
        System.out.println("Atskira dalelė -> "+s.nextToken());
        System.out.println("Atskira dalelė -> "+s.nextToken());
        System.out.println("Atskira dalelė -> "+s.nextToken());
        System.out.println("Atskira dalelė -> "+s.nextToken());
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Scanner klasės objektai turi metodus ne tik atskiriems teksto
     * fragmentams išskirti, bet ir tiesiogiai gauti skaičių reikšmes.
     */
    void met6Scanner(){
        System.out.println("===== metodas6 Scanner ");
        // kai kompe yra lietuvybė - dešimtosios dalys skiriamos kableliu
        // kai jos nėra - tada tašku
//        String analizė="Simas Jonaitis 1988 5800,36 246,18";
        String analizė="Simas Jonaitis 1988 5800.36 246.18";
        System.out.println("Tekstas analizei -> " + analizė);
        Scanner sc=new Scanner(analizė);
        String vardas   = sc.next();
        String pavardė  = sc.next();
        int gimMetai    = sc.nextInt();
        double alga     = sc.nextDouble();
        double mokesčiai = sc.nextDouble();
        System.out.println("Rezultatas atvirkščiai -> "+
                mokesčiai+" "+alga+" "+gimMetai+" "+pavardė+" "+vardas);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /*
     * Išbandomi String.format() ir printtf() metodai,
     * kurių pagalba yra tvarkingai pateikiami duomenys ir rezultatai.
     */
    void met7FormatuotiDuomenis(){
        System.out.println("===== metodas7 Formatuoti Duomenis ");
        int a=1234;
        double b=12345.678;
        String e=String.format("Rezultatai: a=%5d ir b=%10.2f", a, b);
        System.out.println(e);
        System.out.printf("Rezultatai: a=%5d ir b=%10.2f\n", a, b);
        String f=String.format("Rezultatai: a=%5d ir b=%10.2f", ++a, ++b);
        System.out.println(f);
        System.out.printf("Rezultatai: a=%5d ir b=%10.2f\n", ++a, ++b);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Atliekate savo pasirinktus demo skaičiavimus su tekstais ir simboliais
     */
    void memifyString(String input){
        System.out.println("===== metodas6 ... ");
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); ++i) {
            if (i % 2 == 0) {
                sb.setCharAt(i, Character.toUpperCase(input.charAt(i)));
            }
        }
        System.out.println(sb);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Atliekate savo pasirinktus demo skaičiavimus su  tekstais ir simboliais
     */
    void removeEnglishVowels(String input){
        System.out.println("===== metodas7 ... ");
        String result = input.replaceAll("[aieou]", "*");
        System.out.println(result);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    public static void main(String[] args) {
        new F_SimboliaiTekstai().metodoParinkimas();
    }         
}
