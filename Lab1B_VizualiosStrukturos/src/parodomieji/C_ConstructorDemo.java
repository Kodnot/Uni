/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 09
   *
   *  Tai yra demonstracinė klasė, pateikianti įvairių ScreenKTU konstruktorių
   *    panaudojimo pavyzdžius. Ši klasė yra išvestinė iš ScreenKTU.
   *  Atkreipkite dėmesį į laukų cellH, cellW, screenH, screenW panaudojimą.
   ****************************************************************************/
package parodomieji;
import studijosKTU.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class C_ConstructorDemo extends ScreenKTU {
    C_ConstructorDemo(){  
        super();
    }
    public C_ConstructorDemo(int cellSize, int screenSize){
        super(cellSize, screenSize);
    }
    public C_ConstructorDemo(int cellSize, int screenSize, Grid gr){
        super(cellSize, screenSize, gr);
    }
    public C_ConstructorDemo(int ch, int cw, int sh, int sw){
        super(ch, cw, sh, sw);
    }
    public C_ConstructorDemo(int ch, int cw, int sh, int sw, Fonts sf){
        super(ch, cw, sh, sw, sf, Grid.OFF);
    }
    public C_ConstructorDemo(int ch, int cw, int sh, int sw, Grid gr){
        super(ch, cw, sh, sw, gr);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int r = e.getY() / cellH;
        int c = e.getX() / cellW;
        print(r, c, e.getButton()==1? Color.red:
                    e.getButton()==2? Color.green: Color.yellow);
        refresh();
        System.out.println("row= " + r +"  col= " + c ); // pagalbinė info
    }
    void initForm(){
        clearAll(Color.black);
        printRowsColumnsNumbers();
        setFontColor(Color.yellow);
        print(2, 2, "Info apie ScreenKTU:");
        setFontColor(Color.red);
        print(3, 3, " cellH=" + cellH + "  cellW=" + cellW);
        print(4, 3, "screeH=" + screenH + " screenW=" + screenW);
        print(5, 3, "totalH=" + totalH + " totalW=" + totalW);
        refresh();
    }
    public static void main(String[] args) {
        new C_ConstructorDemo(14, 10, 10, 28).initForm();
        new C_ConstructorDemo(14, 10, 10, 28, Grid.ON).initForm();
        new C_ConstructorDemo(18, 14, 10, 28, Fonts.plainB).initForm();
        new C_ConstructorDemo(18, 14, 10, 28, Fonts.boldB).initForm();
        new C_ConstructorDemo(14, 10, 10, 28).initForm();
        new C_ConstructorDemo(14, 10, 10, 28, Grid.ON).initForm();
        new C_ConstructorDemo().initForm(); // gali būti visai be parametrų
        new C_ConstructorDemo(18, 12, 10, 28, Grid.ON).initForm();
        new C_ConstructorDemo(12, 24).initForm();
        new C_ConstructorDemo(12, 24, Grid.ON).initForm();
        new C_ConstructorDemo(30, 30, 8, 28, Grid.ON).initForm();
        new C_ConstructorDemo(30, 30, 20, 30, Grid.ON).initForm();
    }
}