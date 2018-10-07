/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Liuberskis;

import java.util.Locale;
import java.util.Random;
import studijosKTU.Ks;
import studijosKTU.ListKTU;
import studijosKTU.Timekeeper;

/**
 *
 * @author Kodnot
 */
public class PerformanceAnalysis1 {

    Random random = new Random();
    static int[] batchSizes = {1_000_000, 2_000_000, 4_000_000, 8_000_000};

    void runSimpleAnalysis(int count) {
        // Preparations
        long t0 = System.nanoTime();
        double sum = 0;
        for (int i = 0; i < count; ++i) {
            Double num = random.nextDouble() * 1000;
            sum += Math.sqrt(num);
        }
        long t1 = System.nanoTime();
        for (int i = 0; i < count; ++i) {
            Double num = random.nextDouble() * 1000;
            sum += Math.sin(num);
        }
        long t2 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f\n", count, (t1 - t0) / 1e9, (t2 - t1) / 1e9);
        Ks.oun("Šaknų ir sinusų suma: " + sum);
    }

    void runPerformanceAnalysisWithoutGeneration() {
        // Preparations
        Timekeeper tk = new Timekeeper(batchSizes);
        double sum = 0;
        for (int size : batchSizes) {
            // Analysis
            tk.start();
            for (int i = 0; i < size; ++i) {
                Double num = random.nextDouble() * 1000;
                Double tmp = Math.sqrt(num);
                sum += tmp;
            }
            tk.finish("SysSqrt");

            for (int i = 0; i < size; ++i) {
                Double num = random.nextDouble() * 1000;
                Double tmp = Math.sin(num);
                sum += tmp;
            }
            tk.finish("SysSin");
            tk.seriesFinish();
        }
        Ks.oun("Šaknų ir sinusų suma: " + sum);
    }

    void executeLogic() {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= " + memTotal);

        Ks.oun("1 - Sqrt");
        Ks.oun("2 - Sin");
        Ks.ouf("%6d %7d %7d\n", 0, 1, 2);
        for (int n : batchSizes) {
            runSimpleAnalysis(n);
        }
        runPerformanceAnalysisWithoutGeneration();
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("LT"));
        new PerformanceAnalysis1().executeLogic();
    }
}
