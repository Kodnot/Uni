package lab3liuberskis;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import laborai.studijosktu.HashType;
import laborai.studijosktu.MapKTUx;
import laborai.gui.MyException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * @author eimutis
 */
public class IndividualiGreitaveika {

    public static final String FINISH_COMMAND = "finishCommand";
    private static final float loadFactor = 0.75f;

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;

    private final String[] TYRIMU_VARDAI = {"putKtu", "putHashSet", "containsKtuOA", "containsKtu"};
    private final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000};

    private final MapKTUx<String, String> map
            = new MapKTUx(new String(), new String(), 10, loadFactor, HashType.DIVISION);
    private final MapKTUOA<String, String> customMap = new MapKTUOA<>(10, loadFactor, HashType.DIVISION);
    private final HashMap<String, String> hashSet = new HashMap<>(10, loadFactor);

    public IndividualiGreitaveika() {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
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
            boolean controlVal = true;
            for (int k : TIRIAMI_KIEKIAI) {
                String[] studIdArray = StudentGenerator.generateStudentIds(k);
                map.clear();
                customMap.clear();
                hashSet.clear();
                tk.startAfterPause();
                tk.start();

                for (int i = 0; i < k; i++) {
                    map.put(studIdArray[i], studIdArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[0]);

                for (int i = 0; i < k; i++) {
                    hashSet.put(studIdArray[i], studIdArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[1]);

                for (String s : studIdArray) {
                    controlVal &= customMap.contains(s);
                }
                tk.finish(TYRIMU_VARDAI[2]);

                for (String s : studIdArray) {
                    controlVal &= map.contains(s);
                }
                tk.finish(TYRIMU_VARDAI[3]);
                tk.seriesFinish();
            }

            tk.logResult(FINISH_COMMAND);
            assert (controlVal == true);
        } catch (MyException e) {
            tk.logResult(e.getMessage());
        }
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
