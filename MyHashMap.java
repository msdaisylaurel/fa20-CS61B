import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Iterator;


public class MyHashMap<K, V> implements Map61B<K, V> {
    private static int INITIAL_SIZE = 16;
    private static double INITIAL_LOAD = 0.75;

    private int size;
    private double loadFactor;
    private ArrayList<Listings> directory;
    private HashSet<K> dirContents;

    MyHashMap() {
        this.size = INITIAL_SIZE;
        this.loadFactor = INITIAL_LOAD;
        this.directory = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            directory.add(new Listings());
        }
        this.dirContents = new HashSet<>(size);
    }


    MyHashMap(int initialSize) {
        this.size = initialSize;
        this.loadFactor = INITIAL_LOAD;
        this.directory = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            directory.add(new Listings());
        }
        this.dirContents = new HashSet<>(size);
    }


    MyHashMap(int initialSize, double initialLoad) {
        this.size = initialSize;
        this.loadFactor = initialLoad;
        this.directory = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            directory.add(new Listings());
        }
        this.dirContents = new HashSet<>(size);
    }


    private class Listings extends LinkedList {
        private V thing;

        Listings() {
            thing = null;
        }

        Listings(K key, V value) {
            this.addFirst(key);
            thing = value;
        }

        public V getThing() {
            return this.thing;
        }

        public void setThing(V value) {
            this.thing = value;
        }
    }

    private class HashMapIter implements Iterator<K> {
        private K cur;

        HashMapIter() {
            cur = dirContents.iterator().next();
        }

        @Override
        public boolean hasNext() { return !dirContents.isEmpty(); }

        @Override
        public K next() {
            K ret = cur;
            cur = dirContents.iterator().next();
            return ret;
        }
    }



    @Override
    public void clear() {
        /** Removes all of the mappings from this map. */
        //this.directory.clear();
        //this.dirContents.clear();

    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (dirContents.contains(key)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {

        if (containsKey(key)) {
            return pull(key, directory.get(negToPoz(key.hashCode() % size)));
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() { return 1; }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        int keyHash = negToPoz(key.hashCode() % size);

        if (containsKey(key)) {     //key exists already; change value
            while (directory.get(keyHash).listIterator().hasNext()) {
                if (directory.get(keyHash).peek() == key) {
                    directory.get(keyHash).setThing(value);
                }
                directory.get(keyHash).listIterator().next();
            }
        } else {                    //key does not exist; but directory location has Listings





            if (directory.get(keyHash) != null) {
                categorizer(key, value, directory.get(keyHash));
            } else {                //key does not exist; directory location has no current Listings
                directory.set(keyHash, new Listings(key, value));
            }
            this.dirContents.add(key);
        }

        if (size() / size > loadFactor) {
            MyHashMap tempHashMap = new MyHashMap(size * size, loadFactor);

            while (dirContents.iterator().hasNext()) {
                tempHashMap.put(dirContents.iterator().next(), this.get(dirContents.iterator().next()));
            }
            this.size = size * size;
            this.directory = tempHashMap.directory;
            tempHashMap.clear();
        }

    }

    public V pull(K key, Listings index) {
        V temp;
        if (key == index.peek()) {
            temp = index.thing;
        } else {
            temp = pull(key, (Listings) index.peek());
        }
        return temp;
    }

    public void categorizer(K k, V v, Listings nl) {
        if (nl.peek() == null) {
            nl.push(new Listings(k, v));
        } else {
            categorizer(k, v, (Listings) nl.peek());
        }
    }

    public int negToPoz(int i) {
        if (i < 0) {
            i = i * -1;
            return i;
        }
        return i;
    }





    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        //Set<K> listOfKeys = dirContents;
        return null;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Iterator<K> iterator() { return new HashMapIter(); }


}
