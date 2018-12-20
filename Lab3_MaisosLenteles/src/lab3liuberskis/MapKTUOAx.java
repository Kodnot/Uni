package lab3liuberskis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import laborai.studijosktu.HashType;
import laborai.studijosktu.Ks;
import laborai.studijosktu.MapADTx;
import laborai.studijosktu.MapKTU;
import static lab3liuberskis.MapKTUOA.DEFAULT_HASH_TYPE;
import static lab3liuberskis.MapKTUOA.DEFAULT_INITIAL_CAPACITY;
import static lab3liuberskis.MapKTUOA.DEFAULT_LOAD_FACTOR;

/**
 *
 * @author Kodnot
 */
public class MapKTUOAx<K, V> extends MapKTUOA<K, V> implements MapADTx<K, V> {

    private K baseKey;       // Bazinis objektas skirtas naujų kūrimui
    private V baseObj;       // Bazinis objektas skirtas naujų kūrimui

    /**
     * Konstruktorius su bazinio objekto fiksacija
     *
     * @param baseKey
     * @param baseObj
     */
    public MapKTUOAx(K baseKey, V baseObj) {
        this(baseKey, baseObj, DEFAULT_HASH_TYPE);
    }

    /**
     * Konstruktorius su bazinio objekto fiksacija
     *
     * @param baseKey
     * @param baseObj
     * @param ht
     */
    public MapKTUOAx(K baseKey, V baseObj, HashType ht) {
        this(baseKey, baseObj, DEFAULT_INITIAL_CAPACITY, ht);
    }

    /**
     * Konstruktorius su bazinio objekto fiksacija
     *
     * @param baseKey
     * @param baseObj
     * @param initialCapacity
     * @param ht
     */
    public MapKTUOAx(K baseKey, V baseObj, int initialCapacity, HashType ht) {
        this(baseKey, baseObj, initialCapacity, DEFAULT_LOAD_FACTOR, ht);
    }

    /**
     * Konstruktorius su bazinio objekto fiksacija
     *
     * @param baseKey
     * @param baseObj
     * @param initialCapacity
     * @param loadFactor
     * @param ht
     */
    public MapKTUOAx(K baseKey, V baseObj, int initialCapacity, float loadFactor, HashType ht) {
        super(initialCapacity, loadFactor, ht);
        this.baseKey = baseKey;     // fiksacija dėl naujų elementų kūrimo
        this.baseObj = baseObj;     // fiksacija dėl naujų elementų kūrimo
    }

    /**
     * Sukuria elementą iš String. Jei turime failą, kuriame saugomos
     * raktas-reikšmė poros, šį metodą reikėtų atitinkamai modifikuoti
     *
     * @param dataString
     * @return
     */
    @Override
    public V put(String dataString) {
        return put((K) dataString, (V) dataString);
    }

    /**
     * Suformuoja atvaizį iš filePath failo
     *
     * @param filePath
     */
    @Override
    public void load(String filePath) {
        clear();
        if (filePath == null || filePath.length() == 0) {
            return;
        }
        if ((baseKey == null) || (baseObj == null)) { // Elementų kūrimui reikalingas baseObj
            Ks.ern("Naudojant load-metodą, "
                    + "reikia taikyti konstruktorių = new MapKTU(new Data(),new Data())");
        }
        try {
            try (BufferedReader fReader = Files.newBufferedReader(Paths.get(filePath), Charset.defaultCharset())) {
                fReader.lines().filter(line -> line != null && !line.isEmpty()).forEach(line -> put(line));
            }
        } catch (FileNotFoundException e) {
            Ks.ern("Tinkamas duomenų failas " + filePath + " nerastas");
        } catch (IOException | UncheckedIOException e) {
            Ks.ern("Failo " + filePath + " skaitymo klaida:" + e.getLocalizedMessage());
        }
    }

    /**
     * Išsaugoja sąrašą faile fName tekstiniu formatu tinkamu vėlesniam
     * skaitymui
     *
     * @param filePath
     */
    @Override
    public void save(String filePath) {
        throw new UnsupportedOperationException("save nepalaikomas");
    }

    /**
     * Atvaizdis spausdinamas į Ks.ouf("");
     */
    @Override
    public void println() {
        if (super.isEmpty()) {
            Ks.oun("Atvaizdis yra tuščias");
        } else {
            String[][] data = getModelList("=");
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    String format = (j == 0 | j % 2 == 1) ? "%7s" : "%15s";
                    Object value = data[i][j];
                    Ks.ouf(format, (value == null ? "" : value));
                }
                Ks.oufln("");
            }
        }

        Ks.oufln("****** Bendras porų kiekis yra " + super.size());
    }

    /**
     * Spausdinant galima nurodyti antraštę
     *
     * @param title
     */
    @Override
    public void println(String title) {
        Ks.ounn("========" + title + "=======");
        println();
        Ks.ounn("======== Atvaizdžio pabaiga =======");
    }

    @Override
    public String[][] getModelList(String delimiter) {

        String[][] result = new String[table.length][];
        int count = 0;
        for (Entry<K, V> n : table) {
            List<String> list = new ArrayList();
            list.add("[ " + count + " ]");
            if (n != null) {
                list.add("-->");
                list.add(split(n.toString(), delimiter));
            }
            result[count] = list.toArray(new String[0]);
            count++;
        }
        return result;
    }

    private String split(String s, String delimiter) {
        int k = s.indexOf(delimiter);
        if (k <= 0) {
            return s;
        }
        return s.substring(0, k);
    }

    @Override
    public int getMaxChainSize() {
        return 1;
    }

    @Override
    public int getRehashesCounter() {
        return rehashesCounter;
    }

    @Override
    public int getTableCapacity() {
        return table.length;
    }

    @Override
    public int getLastUpdatedChain() {
        return -1;
    }

    @Override
    public int getChainsCounter() {
        return size;
    }
}
