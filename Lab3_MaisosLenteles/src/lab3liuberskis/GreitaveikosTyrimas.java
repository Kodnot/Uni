package lab3liuberskis;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class GreitaveikosTyrimas {

    public static final String FINISH_COMMAND = "finishCommand";
    private static final float loadFactor = 0.75f;

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;

    private final String[] TYRIMU_VARDAI = {"addKTU", "addCust", "remKTU", "remCust", "getKTU", "getCust"};
    private final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000, -1};

    private final MapKTUx<String, Student> studMap
            = new MapKTUx(new String(), new Student(), 10, loadFactor, HashType.DIVISION);
    private final MapKTUOA<String, Student> studMapCustom = new MapKTUOA<>(10, loadFactor, HashType.DIVISION);

    public GreitaveikosTyrimas() {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
    }

    public void pradetiTyrima() {
        try {
            SisteminisTyrimas();
            TyrimasSuZodynu();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void TyrimasSuZodynu() throws InterruptedException, IOException {
        try {
            Path path = Paths.get(".").resolve("zodynas.txt");
            Charset charset = Charset.defaultCharset();
            List<String> lines = Files.readAllLines(path, charset);

            MapKTUx<String, String> ktuMap = new MapKTUx<>(new String(), new String(), 10, loadFactor, HashType.DIVISION);
            MapKTUOA<String, String> myMap = new MapKTUOA<>(10, loadFactor, HashType.DIVISION);
            tk.startAfterPause();
            tk.start();

            for (int i = 0; i < lines.size(); i++) {
                ktuMap.put(lines.get(i), lines.get(i));
            }
            tk.finish(TYRIMU_VARDAI[0]);

            for (int i = 0; i < lines.size(); i++) {
                myMap.put(lines.get(i), lines.get(i));
            }
            tk.finish(TYRIMU_VARDAI[1]);

            for (String s : lines) {
                ktuMap.remove(s);
            }
            tk.finish(TYRIMU_VARDAI[2]);

            for (String s : lines) {
                myMap.remove(s);
            }
            tk.finish(TYRIMU_VARDAI[3]);

            for (String s : lines) {
                ktuMap.get(s);
            }
            tk.finish(TYRIMU_VARDAI[4]);

            for (String s : lines) {
                myMap.get(s);
            }
            tk.finish(TYRIMU_VARDAI[5]);
            tk.seriesFinish();

        } catch (MyException e) {
            tk.logResult(e.getMessage());
        }
    }

    public void SisteminisTyrimas() throws InterruptedException {
        try {
            for (int k : TIRIAMI_KIEKIAI) {
                if (k == -1) {
                    TyrimasSuZodynu();
                    continue;
                }
                Student[] studArray = StudentGenerator.generateStudents(k);
                String[] studIdArray = StudentGenerator.generateStudentIds(k);
                studMap.clear();
                studMapCustom.clear();
                tk.startAfterPause();
                tk.start();

                for (int i = 0; i < k; i++) {
                    studMap.put(studIdArray[i], studArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[0]);

                for (int i = 0; i < k; i++) {
                    studMapCustom.put(studIdArray[i], studArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[1]);

                for (String s : studIdArray) {
                    studMap.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[2]);

                for (String s : studIdArray) {
                    studMapCustom.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[3]);

                for (String s : studIdArray) {
                    studMapCustom.get(s);
                }
                tk.finish(TYRIMU_VARDAI[4]);

                for (String s : studIdArray) {
                    studMapCustom.get(s);
                }
                tk.finish(TYRIMU_VARDAI[5]);
                tk.seriesFinish();
            }

            tk.logResult(FINISH_COMMAND);
        } catch (MyException e) {
            tk.logResult(e.getMessage());
        } catch (IOException e) {
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
