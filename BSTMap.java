import java.util.Iterator;
import java.util.Set;

/**
 *
 * @param <K>
 * @param <V>
 */



public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private V root;
    private K key;
    private BSTMap leftNode, rightNode;
    private int size;

    public BSTMap() {

    }


    @Override
    public void clear() {
        root = null;
        key = null;
        size = 0;
    }


    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (this.root == null) {
            return null;
        }
        if (key.compareTo(this.key) != 0) {
            int testSub = key.compareTo(this.key);

            if (testSub < 0) {
                return (V)this.leftNode.get(key);
            }
            if (testSub > 0) {
               return (V)this.rightNode.get(key);
            }
        } else {
            if (this.root == null) {
                return null;
            }
        }
        return this.root;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        if (this.root == null) return 0;
        else return this.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (this.root != null) {
            int testSub = key.compareTo(this.key);

            if (testSub < 0) {
                if (this.leftNode != null) {
                    this.leftNode.put(key, value);
                    if (this.rightNode != null) {
                        this.size = 1 + this.leftNode.size() + this.rightNode.size();
                    } else {
                        this.size = 1 + this.leftNode.size();
                    }
                } else {
                    BSTMap<K, V> newNode = new BSTMap<K, V>();
                    newNode.put(key, value);
                    this.leftNode = newNode;
                    this.size = 1 + this.leftNode.size();
                }
            }
            if (testSub > 0) {
                if (this.rightNode != null) {
                    this.rightNode.put(key, value);
                    if (this.leftNode != null) {
                        this.size = 1 + this.leftNode.size() + this.rightNode.size();
                    } else {
                        this.size = 1 + this.rightNode.size();
                    }
                } else {
                    BSTMap<K, V> newNode = new BSTMap<K, V>();
                    newNode.put(key, value);
                    this.rightNode = newNode;
                    this.size = 1 + this.rightNode.size();
                }
            }
        } else {
            this.key = key;
            this.root = value;
            this.size = 1;
        }
    }


    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet()  {
        throw new UnsupportedOperationException();
    }


    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() { throw new UnsupportedOperationException();}
}
