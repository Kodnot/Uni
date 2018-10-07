/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Liuberskis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import studijosKTU.Ks;
import studijosKTU.Timekeeper;

/**
 *
 * @author Kodnot
 */
public class PerformanceAnalysis2 {

    Random random = new Random();
    static int[] batchSizes = {10_000, 20_000, 40_000, 80_000};
    ArrayList<Integer> arrayListData = new ArrayList<>();
    LinkedList<Integer> linkedListData = new LinkedList<>();

    void generateData(int count) {
        arrayListData.clear();
        linkedListData.clear();
        for (int i = 0; i < count; ++i) {
            arrayListData.add(i);
            linkedListData.add(i);
        }
        Collections.shuffle(arrayListData);
        Collections.shuffle(linkedListData);
    }

    void runSimpleAnalysis(int count) {
        // Preparations
        long t0 = System.nanoTime();
        generateData(count);
        long sum = 0;
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();

        // Analysis
        for (int i = 0; i < count; ++i) {
            int num = random.nextInt(count);
            sum += arrayListData.lastIndexOf(num);
        }
        long t3 = System.nanoTime();
        for (int i = 0; i < count; ++i) {
            int num = random.nextInt(count);
            sum += linkedListData.lastIndexOf(num);
        }
        long t4 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f\n", count, (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9, (t4 - t3) / 1e9);
        Ks.oun("Elementų suma: " + sum);
    }

    void runSystematicAnalysis() {
        // Preparations
        Timekeeper tk = new Timekeeper(batchSizes);
        long sum = 0;
        for (int size : batchSizes) {
            generateData(size);
            // Analysis
            tk.start();
            for (int i = 0; i < size; ++i) {
                int num = random.nextInt(size);
                sum += arrayListData.lastIndexOf(num);
            }
            tk.finish("ArrayListIndexOf");

            for (int i = 0; i < size; ++i) {
                int num = random.nextInt(size);
                sum += linkedListData.lastIndexOf(num);
            }
            tk.finish("LinkedListIndexOf");
            tk.seriesFinish();
        }
        Ks.oun("Elementų suma: " + sum);
    }

    void executeLogic() {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= " + memTotal);
        generateData(20);
        for (int a : arrayListData) {
            Ks.oun(a);
        }
        for (int a : linkedListData) {
            Ks.oun(a);
        }
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - ArrayList LastIndedOf");
        Ks.oun("4 - LinkedList LastIndexOf");
        Ks.ouf("%6d %7d %7d %7d %7d\n", 0, 1, 2, 3, 4);
        for (int n : batchSizes) {
            runSimpleAnalysis(n);
        }
        runSystematicAnalysis();
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("LT"));
        new PerformanceAnalysis2().executeLogic();
    }
}
