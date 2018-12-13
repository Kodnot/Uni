/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kursinis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author audri
 */
public class KursinisTrial {

    private final static int unrolledArraySize = 128;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        MemoryConsumptionTests(1000000);
//        TimeConsumptionTests();
        TimeConsumptionTestsExtra(10000000);
    }

    private static void TimeConsumptionTestsExtra(int dataSize) {
        // Preparations
        List<Integer> linkedList = new LinkedList<>();
        UnrolledLinkedListADT<Integer> unrolledList = new UnrolledLinkedList<>(unrolledArraySize);
        int[] testVals = IntStream.range(0, dataSize).unordered().toArray();
        for (int a : testVals) {
            linkedList.add(a);
        }
        for (int a : testVals) {
            unrolledList.add(a);
        }
        int sum = 0;

        long t0 = System.nanoTime();
        for (Integer val : linkedList) {
            sum += val;
        }
        long t1 = System.nanoTime();
        for (Integer val : unrolledList) {
            sum += val;
        }
        long t2 = System.nanoTime();

        System.out.printf("Data: %7d, Linked iteration time: %7.4f, Unrolled iteration time: %7.4f, Sum: %7d\n",
                dataSize, (t1 - t0) / 1e9, (t2 - t1) / 1e9, sum);
    }

    private static void TimeConsumptionTests() {
        GreitaveikosTyrimas gt = new GreitaveikosTyrimas(unrolledArraySize);

        // Si gija paima rezultatus is greitaveikos tyrimo gijos ir isveda 
        // juos i taOutput. Gija baigia darbą kai gaunama FINISH_COMMAND     
        new Thread(() -> {
            try {
                String result;
                while (!(result = gt.getResultsLogger().take())
                        .equals(GreitaveikosTyrimas.FINISH_COMMAND)) {
                    System.out.print(result);
                    gt.getSemaphore().release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            gt.getSemaphore().release();
        }, "Greitaveikos_rezultatu_gija").start();

        //Sioje gijoje atliekamas greitaveikos tyrimas
        new Thread(() -> gt.pradetiTyrima(), "Greitaveikos_tyrimo_gija").start();
    }

    private static void MemoryConsumptionTests(int dataSize) {
        double sideMemoryConsumedMegabytes = MemStats.getUsedMegabytes();
        // You can see the memory consumption of unrolled list becomes less with array size
        // Set to ~6

        // TODO: Check with random insertion, because now only half of all the arrays in unrolled list 
        // are utilized.
        // Unrolled linked list
        UnrolledLinkedListADT<Integer> unrolledList = new UnrolledLinkedList<>(unrolledArraySize);
        IntStream.range(0, dataSize).forEach(i -> unrolledList.add(i));
        System.out.println(String.format("UnrolledLinkedList naudota atminties: (%1.2f MB)\n",
                MemStats.getUsedMegabytes() - sideMemoryConsumedMegabytes));
        unrolledList.clear();

        // Java linked list
        List<Integer> javaLinkedList = new LinkedList<Integer>();
        IntStream.range(0, dataSize).forEach(i -> javaLinkedList.add(i));
        System.out.println(String.format("Java linked list naudota atminties: (%1.2f MB)\n",
                MemStats.getUsedMegabytes() - sideMemoryConsumedMegabytes));
        javaLinkedList.clear();

        System.out.println(MemStats.getUsedMegabytes());
    }

    static class MemStats {

        public static long getUsedBytes() {
            System.gc();
            System.gc();
            System.gc();
            return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        }

        public static double getUsedMegabytes() {
            return getUsedBytes() / 1000000f;
        }
    }
}
