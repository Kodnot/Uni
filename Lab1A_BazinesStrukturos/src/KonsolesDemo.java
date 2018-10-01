
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class KonsolesDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        Console cns = System.console();
//        String s = cns.readLine();
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String s = reader.readLine();
        int a = Integer.parseInt(s);
        System.out.println("Kvadr=" + a * a);
    }

}
