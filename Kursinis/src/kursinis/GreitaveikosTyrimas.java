package kursinis;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.IntStream;

public class GreitaveikosTyrimas {

    public static final String FINISH_COMMAND = "finish";

    private static final String[] TYRIMU_VARDAI = {"addLin", "addUn", "containsLin", "containsUn", "getLin", "getUn"};
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

            tk.seriesFinish();

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
