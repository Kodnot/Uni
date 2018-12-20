package lab3liuberskis;

import java.util.Arrays;
import laborai.studijosktu.HashType;
import laborai.studijosktu.MapADT;
import laborai.studijosktu.MapADTp;

//TODO: Add tests
public class MapKTUOA<K, V> implements MapADT<K, V> {

    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public static final HashType DEFAULT_HASH_TYPE = HashType.DIVISION;

    protected final Entry SENTINEL = new Entry();

    // Maišos lentelė
    protected Entry<K, V>[] table;
    // Lentelėje esančių raktas-reikšmė porų kiekis
    protected int size = 0;
    // Apkrovimo faktorius
    protected float loadFactor;
    // Maišos metodas
    protected HashType ht;
    //--------------------------------------------------------------------------
    //  Maišos lentelės įvertinimo parametrai
    //--------------------------------------------------------------------------
    // Permaišymų kiekis
    protected int rehashesCounter = 0;
    // Einamas poros indeksas maišos lentelėje
    protected int index = 0;

    /* Klasėje sukurti 4 perkloti konstruktoriai, nustatantys atskirus maišos 
     * lentelės parametrus. Jei kuris nors parametras nėra nustatomas - 
     * priskiriama standartinė reikšmė.
     */
    public MapKTUOA() {
        this(DEFAULT_HASH_TYPE);
    }

    public MapKTUOA(HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, ht);
    }

    public MapKTUOA(int initialCapacity, HashType ht) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR, ht);
    }

    public MapKTUOA(float loadFactor, HashType ht) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor, ht);
    }

    public MapKTUOA(int initialCapacity, float loadFactor, HashType ht) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }

        if ((loadFactor <= 0.0) || (loadFactor > 1.0)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }

        this.table = new Entry[initialCapacity];
        this.loadFactor = loadFactor;
        this.ht = ht;
    }

    /**
     * Patikrinama ar atvaizdis yra tuščias.
     *
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Grąžinamas atvaizdyje esančių porų kiekis.
     *
     * @return Grąžinamas atvaizdyje esančių porų kiekis.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Išvalomas atvaizdis.
     */
    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
        index = 0;
        rehashesCounter = 0;
    }

    /**
     * Patikrinama ar pora egzistuoja atvaizdyje.
     *
     * @param key raktas.
     * @return Patikrinama ar pora egzistuoja atvaizdyje.
     */
    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < table.length; ++i) {
            Entry<K, V> cur = table[i];
            if (cur != null && cur.value.equals(value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Atvaizdis papildomas nauja pora.
     *
     * @param key raktas,
     * @param value reikšmė.
     * @return Atvaizdis papildomas nauja pora.
     */
    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value is null in put(Key key, Value value)");
        }

        int index = findPosition(key);
        if (index == -1) {
            rehash();
            put(key, value);
            return value;
        }

        if (table[index] == null) {
            table[index] = new Entry(key, value);
            size++;
            if (size > table.length * loadFactor) {
                rehash();
            }
        } else {
            table[index].value = value;
        }

        return value;
    }

    /**
     * Grąžinama atvaizdžio poros reikšmė.
     *
     * @return Grąžinama atvaizdžio poros reikšmė.
     *
     * @param key raktas.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in get(Key key)");
        }

        index = findPosition(key);
        return (index == -1 || table[index] == null || table[index] == SENTINEL ? null : table[index].value);
    }

    /**
     * Pora pašalinama iš atvaizdžio.
     *
     * @param key Pora pašalinama iš atvaizdžio.
     * @return key raktas.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in remove(Key key)");
        }

        int index = findPosition(key);
        if (index == -1 || table[index] == null) {
            return null;
        }

        size--;
        V rez = table[index].value;
        table[index] = SENTINEL;
        return rez;
    }

    private int hash(K key, HashType hashType) {
        int h = key.hashCode();
        switch (hashType) {
            case DIVISION:
                return Math.abs(h) % table.length;
            case MULTIPLICATION:
                double k = (Math.sqrt(5) - 1) / 2;
                return (int) (((k * Math.abs(h)) % 1) * table.length);
            case JCF7:
                h ^= (h >>> 20) ^ (h >>> 12);
                h = h ^ (h >>> 7) ^ (h >>> 4);
                return h & (table.length - 1);
            case JCF8:
                h = h ^ (h >>> 16);
                return h & (table.length - 1);
            default:
                return Math.abs(h) % table.length;
        }
    }

    /**
     * Permaišymas
     */
    private void rehash() {
        MapKTUOA mapKTU = new MapKTUOA(table.length * 2, loadFactor, ht);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                mapKTU.put(table[i].key, table[i].value);
            }
        }
        table = mapKTU.table;
        rehashesCounter++;
    }

    private int findPosition(K key) {
        int index = hash(key, ht);
        int index0 = index;
        int i = 0;
        for (int j = 0; j < table.length; ++j) {
            if (table[index] != SENTINEL && (table[index] == null || table[index].key.equals(key))) {
                return index;
            }
            i++;
            index = (index0 + i * i) % table.length;
        }
        return -1;
    }

    // TODO: Should this really be two-dimensional with this impl?
    @Override
    public String[][] toArray() {
        String[][] result = new String[table.length][];
        int count = 0;
        for (Entry<K, V> n : table) {
            result[count++] = new String[]{
                n.toString()
            };
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Entry<K, V> node : table) {
            if (node != null) {
                result.append(node.toString()).append(System.lineSeparator());
            }
        }
        return result.toString();
    }

    public int getEmptyElementCount() {
        int count = 0;
        for (int i = 0; i < table.length; ++i) {
            if (table[i] == null) {
                count++;
            }
        }
        return count;
    }

    protected class Entry<K, V> {

        // Raktas        
        protected K key;
        // Reikšmė
        protected V value;

        protected Entry() {
        }

        protected Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
