package lab2liuberskis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import laborai.studijosktu.BstSetKTUx2;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.SortedSetADTx;
import laborai.studijosktu.BstSetKTUx;
import laborai.gui.MyException;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import laborai.studijosktu.Ks;

public class IndividualiuGreitaveikosTyrimas {

    public static final String FINISH_COMMAND = "finish";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("laborai.gui.messages");

    private static final String[] TYRIMU_VARDAI = {"TreeSetContains", "HashSetContains", "TreeSetRemove", "HashSetRemove"};
    private static final int[] TIRIAMI_KIEKIAI = {100000, 200000, 400000, 800000};

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;
    private final String[] errors;

    private final TreeSet<Integer> aSeries = new TreeSet<>();
    private final HashSet<Integer> bSeries = new HashSet<>();

    public IndividualiuGreitaveikosTyrimas() {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
        errors = new String[]{
            MESSAGES.getString("error1"),
            MESSAGES.getString("error2"),
            MESSAGES.getString("error3"),
            MESSAGES.getString("error4")
        };
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
        try {
            boolean sumVar = false;
            for (int k : TIRIAMI_KIEKIAI) {
                System.gc();
                System.gc();
                System.gc();
                long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                List<Integer> intMas = ThreadLocalRandom.current().ints(k).boxed().collect(Collectors.toList());
                int[] testCases = ThreadLocalRandom.current().ints(k).toArray();
                aSeries.clear();
                bSeries.clear();
                aSeries.addAll(intMas);
                bSeries.addAll(intMas);
                tk.startAfterPause();
                tk.start();

                for (int a : testCases) {
                    boolean contains = aSeries.contains(a);
                    sumVar &= contains;
                }
                tk.finish(TYRIMU_VARDAI[0]);
                for (int a : testCases) {
                    boolean contains = bSeries.contains(a);
                    sumVar &= contains;
                }
                tk.finish(TYRIMU_VARDAI[1]);
                for (int a : testCases) {
                    aSeries.remove(a);
                }
                tk.finish(TYRIMU_VARDAI[2]);
                for (int a : testCases) {
                    bSeries.remove(a);
                }
                tk.finish(TYRIMU_VARDAI[3]);
                tk.logResult(String.format("Naudota atminties: %d (%1.2f MB)\n", usedMemory, usedMemory / 1000000f));
                tk.seriesFinish();
                
            }
            tk.logResult(FINISH_COMMAND);
            Ks.oun("Discard value: " + sumVar);
        } catch (MyException e) {
            if (e.getCode() >= 0 && e.getCode() <= 3) {
                tk.logResult(errors[e.getCode()] + ": " + e.getMessage());
            } else if (e.getCode() == 4) {
                tk.logResult(MESSAGES.getString("msg3"));
            } else {
                tk.logResult(e.getMessage());
            }
        }
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
