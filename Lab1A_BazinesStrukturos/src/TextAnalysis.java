
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kodnot
 */
public class TextAnalysis {

    private String text;

    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the value of text
     *
     * @param text new value of text
     */
    public void setText(String text) {
        this.text = text;
    }

    public void initializeFromInput() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        System.out.println("Please enter a line of text: ");
        String tempText = null;
        try {
            tempText = reader.readLine();
            text = tempText;
        } catch (Exception e) {
            System.out.println("Well f**k you too.");
        }
    }

    public void printCharacterFrequencies() {
        Map<Character, Integer> counts = new TreeMap<>();
        text.chars().map(x -> Character.toLowerCase(x)).forEach(x -> counts.merge((char) x, 1, Integer::sum));
        System.out.println("Character frequencies: ");
        counts.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("Sorted by frequency: ");
        // Seriously, is there no elegant way to do this...
        TreeMap<Integer, String> inverted = new TreeMap<>(Comparator.reverseOrder());
        counts.forEach((k, v) -> inverted.merge(v, k.toString(), (s1, s2) -> s1 + "|" + s2));
        inverted.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public void doStuff() throws IOException {
        initializeFromInput();
        printCharacterFrequencies();
    }

    public static void main(String[] args) throws IOException {
        new TextAnalysis().doStuff();
    }

}
