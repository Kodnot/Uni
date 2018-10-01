/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 09
   *
   *  Tai yra Unicodo simbolių demonstracinė klasė, 
   *    kurioje tiesiog pateikiama Unicodo simbolių įvairovė.
   *    Pasinaudojant demonstruojamomis lentelėmis, 
   *    galima susirasti reikalingų simbolių kodus.
   *    PASPAUDUS pelę yra pateikiami padidinti pasirinktų simbolių vaizdai
   ****************************************************************************/
package parodomieji;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import studijosKTU.ScreenKTU;

public class UnicodePages extends ScreenKTU{
    static private final Fonts demoFont =  Fonts.boldC;
    ScreenKTU sc ;
    int extInd ;
    public UnicodePages() {
        super(20, 20, 35, 72, demoFont, Grid.ON);
        setTitle("\u00a9 KTU IF \u00a9 Unicodo simbolių lentelių tyrimas");
        for (int i = 0; i < pageBegin.length; i++)
            onePage16x16(i/4*18, i%4*18+1, pageBegin[i]);
        refresh();    
    }
final void onePage16x16(int e, int s, char pr){ 
    char tit[] = "0123456789abcdef".toCharArray();               
    setColors(Color.GREEN, Color.WHITE);                  
    print(e, s-1,   (char)('0'+pr/16));                   
    print(e, s, (char)('0'+pr%16));                       
                                                          
    setBackColor(Color.BLUE);                             
    print(e, s+1, (char)0x25e2);                          
    for(int k=0; k<16; k++){      
        print(e+k+1, s, tit[k]);       
        print(e, s+k+1, tit[k]); 
    }    
    setColors(Color.YELLOW, Color.BLACK);                 
    char b = (char)(pr<<8);                               
    for(int i=0; i<16; i++){
        for(int j=0; j<16; j++){
            print(e+i+1, s+j+1, b++); 
        } 
    }
}
    @Override
    public void mouseClicked(MouseEvent e) {
        int symRow = e.getY() / cellH % 18-1;
        int symCol = e.getX() / cellW % 18-2;
        if (symRow>=0 && symRow<=15 && symCol>=0){ // ar geltonoje zonoje
            int pageRow = e.getY() / (18 * cellH);
            int pageCol = e.getX() / (18 * cellW);
            char selChar = (char)((pageBegin[pageRow*4+pageCol] << 8) + 
                                (symRow<<4) + symCol);
            if (sc == null) {
                sc = new ScreenKTU(64, 64, 2, 8, demoFont);
                sc.clearAll(Color.blue);
                sc.setColors(Color.magenta, Color.black);
            }
            sc.print(extInd/8, extInd%8, selChar);
            sc.setLocation(e.getX()-120, e.getY()+50);
            sc.refresh();
            extInd = (extInd+1)%16;
//            System.out.println("r"+r + "c"+c + "i"+i + "j"+j );
            System.out.print("'"+ selChar + "', ");
        }
    }
    
    static char[] pageBegin = {0, 1, 4, 0x22, 0x21, 0x25, 0x26, 0x27};
    
    static void testFontsForUnicodeSymbols(){
    GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = e.getAllFonts(); // Gauname visus kompiuterio fontus
        for (Font f : fonts) 
            if(f.canDisplay(0x2654) && f.canDisplay(0x2196)) // chess and arrows
                System.out.println(f.getFontName());          
    }
    
    static public void main(String s[]) {
        new UnicodePages();
//        testFontsForUnicodeSymbols();
    }
}
