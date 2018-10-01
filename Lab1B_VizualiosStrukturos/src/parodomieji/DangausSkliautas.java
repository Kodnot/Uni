/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 09
   *
   *  Tai yra demonstracinė ekrano valdymo klasė, 
   *  kurioje modeliuojamas atsistiktinis simbolių atsiradimas ekrane ScreenKTU.
   *  Šioje klasėje taip naudojamas gijų valdymas.
   *  EKSPERIMENTUOKITE su spalvomis ir ekrano formavimo algoritmais.
   *  PANAUDOKITE daugiau žvaigždžių simbolių (paimkite iš klasės UnicodePages).
   ****************************************************************************/
package parodomieji;
import java.awt.Color;
import static java.awt.Color.*;
import java.util.Random;
import studijosKTU.ScreenKTU;

public class DangausSkliautas extends ScreenKTU {
    char[] simboliai = {'*','&','$','@','O','o','0','Q',
                        '\u263a','\u263b','\u25cf'};
    Color[] spalvos = {black, red, yellow, green, magenta, orange, pink};
    static Random rnd = new Random();
    
    public DangausSkliautas(){
        super(18, 18, 16, 30);
    }
    public void uždegtiŽvaigždę(){
        Color spalva  = spalvos  [rnd.nextInt(spalvos.length)];
        setFontColor( spalva);
        char žvaigždė = simboliai[rnd.nextInt(simboliai.length)];
        int eil = rnd.nextInt(screenH);
        int stu = rnd.nextInt(screenW);
        print(eil, stu, žvaigždė);
    }
    public void ilgasNaktiesCiklas(){
        setColors(spalvos[0], spalvos[0]);
        clearAll(spalvos[0]);
        for(int i=0; i<Integer.MAX_VALUE; i++){
            uždegtiŽvaigždę();
            refresh();
        }
    }
    public void run(){
        ilgasNaktiesCiklas();
    }
    public static void main(String[] args) {
//        new DangausSkliautas().run();
//        new DangausSkliautas().run();
//        new DangausSkliautas().run();
        new Thread(new DangausSkliautas()).start();
        DangausSkliautas ds1 = new DangausSkliautas();
        ds1.spalvos[0] = blue;
        new Thread(ds1).start();
        DangausSkliautas ds2 = new DangausSkliautas();
        ds2.spalvos[0] = gray;
        new Thread(ds2).start();
    }
}