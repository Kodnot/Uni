package laborai.demo;

import laborai.gui.swing.Lab2Window;
import java.util.Locale;

/*
 * Darbo atlikimo tvarka - čia yra pradinė klasė.
 */
public class VykdymoModulis {

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        AutoTestai.aibėsTestas();
        Lab2Window.createAndShowGUI();
    }
}
