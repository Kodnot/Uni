/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2liuberskis;


import java.util.Locale;
import javafx.application.Application;
import javafx.stage.Stage;
import lab2liuberskis.gui.fx.Lab2WindowFX;

/**
 *
 * @author Kodnot
 */
public class ExecutionModuleFx extends Application {
        public static void main(String [] args) {
        ExecutionModuleFx.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus 
        StudentTests.setTest();
        Lab2WindowFX.createAndShowFXGUI(primaryStage);
    }
}
