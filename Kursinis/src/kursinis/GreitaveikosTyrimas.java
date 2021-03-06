package kursinis;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.IntStream;

public class GreitaveikosTyrimas {

    public static final String FINISH_COMMAND = "finish";

    private static final String[] TYRIMU_VARDAI = {"addLin", "addUn", "contLin", "contUn", "getLin", "getUn", "insLin", "insUn", "iterLin", "iterUn"};
    private static final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000};

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;

    private final List<Integer> linkedList = new LinkedList<>();
    private final UnrolledLinkedListADT<Integer> unrolledList;

    public GreitaveikosTyrimas(int unrolledListArraySize) {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
        unrolledList = new UnrolledLinkedList<>(unrolledListArraySize);
    }

    public void pradetiTyrima() {
        try {
            SisteminisTyrimas();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void SisteminisTyrimas() throws InterruptedException {
        for (int k : TIRIAMI_KIEKIAI) {
            int[] testVals = IntStream.range(0, k).unordered().toArray();
            linkedList.clear();
            unrolledList.clear();

            boolean tempVal = true;

            tk.startAfterPause();
            tk.start();
            for (int a : testVals) {
                linkedList.add(a);
            }
            tk.finish(TYRIMU_VARDAI[0]);
            for (int a : testVals) {
                unrolledList.add(a);
            }
            tk.finish(TYRIMU_VARDAI[1]);
            for (int a : testVals) {
                tempVal &= linkedList.contains(a);
            }
            tk.finish(TYRIMU_VARDAI[2]);
            for (int a : testVals) {
                tempVal &= unrolledList.contains(a);
            }
            tk.finish(TYRIMU_VARDAI[3]);
            for (int a : testVals) {
                tempVal &= (linkedList.get(a) > 0);
            }
            tk.finish(TYRIMU_VARDAI[4]);
            for (int a : testVals) {
                tempVal &= (unrolledList.get(a) > 0);
            }
            tk.finish(TYRIMU_VARDAI[5]);
            for (int a : testVals) {
                linkedList.add(a, 1);
            }
            tk.finish(TYRIMU_VARDAI[6]);
            for (int a : testVals) {
                unrolledList.add(a, 1);
            }
            tk.finish(TYRIMU_VARDAI[7]);
            for (Integer a : linkedList) {
                tempVal &= (a > 0);
            }
            tk.finish(TYRIMU_VARDAI[8]);
            for (Integer a : unrolledList) {
                tempVal &= (a > 0);
            }
            tk.finish(TYRIMU_VARDAI[9]);
            tk.seriesFinish();

            // Sanity Check
            Integer[] rez1 = linkedList.toArray(new Integer[0]);
            Integer[] rez2 = unrolledList.toArray(Integer.class);
            for (int i = 0; i < rez1.length; ++i) {
                Integer val1 = rez1[i];
                Integer val2 = rez2[i];
                if (!val1.equals(val2)) {
                    Integer tmp2 = val1 - val2;
                    throw new InternalError();
                }
            }

            System.out.println("Ignore: " + tempVal);
        }
        tk.logResult(FINISH_COMMAND);
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
