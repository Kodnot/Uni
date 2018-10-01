/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 09
   *
   *  Tai yra krepšinio rungtynių tablo demonstracinė klasė, 
   *    kurioje demonstruojami įvairūs įvykių fiksavimo metodai.
   *    
   *  Atkreipkite dėmesį į metodą mouseClicked(MouseEvent e), kurio pagalba
   *  yra reaguojama į pelės paspaudimą ant pasirinkto langelio.
   *  Galima fiksuoti įvykius eilutėje atitinkančiam žaidėjui,
   *  o pelnytus taškus nurodo pasirinktas stulpelis 1 ar 2 ar 3.
   *  Dešinys mygtukas nurodo nepataikytą metimą.
   * 
   *  IŠBANDYKITE įvairius metodus, modifikuokite juos.
   *  IŠBANDYKITE sukurtos žaidybinės situacijos atspausdinimą.
   *  EKSPERIMENTUOKITE su spalvomis ir ekrano formavimo algoritmais.
   *  SUKURKITE kitus praktiškai aktualius metodus.
   ****************************************************************************/
package proj_basketball;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class BasketballTablo extends studijosKTU.ScreenKTU {
    final static private int žaidėjųKiekis = 12;
    String komandosVardas;
    Zaidejas[] žaidėjai = new Zaidejas[žaidėjųKiekis];
    StringBuffer eiga;
    
    BasketballTablo(String[] data){
//        super(20, 14, 20, 50, Fonts.plainB, Grid.OFF);
        super(18, 12, 20, 60, Fonts.boldA, Grid.OFF);
        setTitle("\u00a9 KTU IF \u00a9 krepšinio įvykių registracijos tablo");
        formuotiKomandą(data);
        inicijuotiLentelę();
        refresh();
    }
    
    int[] įvykiųKiekiai = new int[žaidėjųKiekis];
    void įvykioRegistracija(int žInd, int metimas){
        Zaidejas ž1 = žaidėjai[žInd];
        switch(metimas){
            case -1: ž1.n1++; break;
            case -2: ž1.n2++; break;
            case -3: ž1.n3++; break;
            case  1: ž1.t1++; break;
            case  2: ž1.t2++; break;
            case  3: ž1.t3++; break;
        };
        setBackColor(metimas>0? Color.green: Color.lightGray);
        setFontColor(metimas>0? Color.white: Color.black);
        print(4+žInd, 21+įvykiųKiekiai[žInd]++, (char)(Math.abs(metimas)+'0') );        
        setColors(Color.blue, Color.red);
        print(4+žInd, 21+įvykiųKiekiai[žInd], Integer.toString(ž1.taškai()) ); 
    }
    void papildytiEigą(int žaidėjoInd, int metimas){
        eiga.append("#"+žaidėjai[žaidėjoInd].nr);
        if(metimas>0) eiga.append('+');
        eiga.append(metimas);
    }
    void formuotiIĮvykiusPagalDuomenis(){
        Arrays.fill(įvykiųKiekiai, 0);
        for(Zaidejas ž1: žaidėjai){
            ž1.n1=0; ž1.n2=0; ž1.n3=0; ž1.t1=0; ž1.t2=0; ž1.t3=0; 
        }
        for(int i=0; i<eiga.length();){
            int žnr = eiga.charAt(++i) - '0';
            char e = eiga.charAt(++i);
            if(e>='0' && e<='9'){
                žnr = žnr*10 + e-'0';
                e = eiga.charAt(++i);
            }
            int metimas = eiga.charAt(++i) - '0';
            if(e == '-') metimas = -metimas;
            int pInd = 0;
            while(pInd<žaidėjųKiekis && žaidėjai[pInd].nr != žnr) pInd++;
            if(pInd == žaidėjųKiekis){
                System.out.println(komandosVardas + " eigoje nerastas #"+žnr);
                pInd--;
            }
            įvykioRegistracija(pInd, metimas);
            i++;
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int r = e.getY()/cellH, c = e.getX()/cellW;
//        System.out.println("row= " + r +"  col= " + c ); // pagalbinė info
//        print(r, c, Color.red);            // skirta koordinatėms sužinoti
        if(r>=4 && r<4+žaidėjųKiekis && c>=17 && c<=19){
            int taškai = c-16;
            if(e.getButton() != 1) // jei 1 - tai tikslus metimas
                taškai = -taškai;  // jei ne kairys mygtukas, tai netikslus
            įvykioRegistracija(r-4, taškai);
            papildytiEigą(r-4, taškai);
        } else if(r == screenH-1){ // veiksmai nurodomi apatinėje eilutėje
            if(c>=2 && c<=6){         // veiksmas CLEAR
                eiga.delete(0, eiga.length());
                inicijuotiLentelę();
            } else if(c>=9 && c<=12)   // veiksmas SAVE
                System.out.println("\"" + eiga + "\"");
            else if(c>=15 && c<=21)    // veiksmas SUMMARY
                pateiktiAtaskaitą();
            else if(c>=24 && c<=27)    // veiksmas GAME
                inicijuotiLentelę();
        }
        refresh();
    }
    void pateiktiAtaskaitą(){
        Arrays.sort(žaidėjai, Zaidejas.pagalTaškus);
        setBackColor(Color.blue);
        fillRect(4, 21, žaidėjųKiekis, screenW-22);
        int eil = 4;
        for(Zaidejas ž: žaidėjai){
            setFontColor(Color.white);
            print(eil,  1, String.format("%12s", ž.name));
            print(eil, 14, String.format("%2d",  ž.nr));
            setFontColor(Color.yellow);
            String s1 = (ž.n1+ž.t1)==0? "": String.format("%d/%d", ž.t1, ž.n1+ž.t1);
            String s2 = (ž.n2+ž.t2)==0? "": String.format("%d/%d", ž.t2, ž.n2+ž.t2);
            String s3 = (ž.n3+ž.t3)==0? "": String.format("%d/%d", ž.t3, ž.n3+ž.t3);
            print(eil++, 21, String.format("%6s%6s%6s = %2d", 
                                            s1, s2, s3, ž.taškai()));
        }
    }
    final void formuotiKomandą(String[] data){
        komandosVardas = data[0];
        for(int i=0; i<žaidėjųKiekis; i++)
            žaidėjai[i] = new Zaidejas(data[i+1]);
        eiga = new StringBuffer(data[žaidėjųKiekis+1]);
    }    
    final void inicijuotiLentelę(){
        Arrays.sort(žaidėjai);
        clearAll(Color.blue);
        for(int i=0; i<žaidėjųKiekis; i++){
            setFontColor(Color.white);
            print(i+4, 1, String.format("%12s%3d", žaidėjai[i].name, žaidėjai[i].nr));
            setFontColor(Color.magenta);
            print(i+4, 17, "123");
        }
        setFontColor(Color.green);
        print(1, 22, komandosVardas);
        setFontColor(Color.yellow);
        int lineType = 2;
        lineBorder(3,  0, 14, screenW, lineType);
        lineType = 1;
        drawLine  (3, 13, 14, 0, lineType);
        drawLine  (3, 16, 14, 0, lineType);
        drawLine  (3, 20, 14, 0, lineType);
        fillRect(4, 21, 12, screenW-22, Color.blue);
        setColors(Color.cyan, Color.magenta);
        print(screenH-1, 2, "CLEAR");
        print(screenH-1, 9, "SAVE");
        print(screenH-1, 15, "SUMMARY");
        print(screenH-1, 24, "GAME");

        formuotiIĮvykiusPagalDuomenis();
    }

    public static void main(String[] args) {
        new BasketballTablo(Duomenys.data[0]);
        new BasketballTablo(Duomenys.data[1]);
        new BasketballTablo(Duomenys.data[2]);
    }
}