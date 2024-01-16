import java.util.LinkedList;

public class HashTable<K, V> {
    private LinkedList<Entry<K, V>>[] table;
    private int size;
    private int capacity;

    public HashTable() {
        this.capacity = 11;
        this.size = 0;
        this.table = new LinkedList[capacity];

    }
    // Generate index for object 
    private int hash(K key) {
        return key.hashCode() > 0 ? key.hashCode() % capacity: key.hashCode() * -1 % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null ) {
            table[index] = new LinkedList<Entry<K,V>>();
        }
        // If the table already contains the key -> reset the value 
        for (Entry<K,V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }   
            
        }
        table[index].add(new Entry<K, V>(key, value)); 
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        // If HashTable contains more than one entry -> go through all entries
        // If HashTable contains current entry -> return current entry
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        // Else -> return null
        return null;
    }


    public void remove(K key) {
        int index = hash(key);
        Entry<K, V> toRemove = null;
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                toRemove = entry;
                break;
            }
        }

        if (toRemove != null) {
            table[index].remove(toRemove);
        }
        size --;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // Object that contains one pair "{key: value}"
    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("banana", 7);
        hashTable.put("banana", 7);
        System.out.println(hashTable.size()); // Выведет 2
        hashTable.put("apple", 5);

        System.out.println(hashTable.get("apple")); // Выведет 5
        System.out.println(hashTable.size()); // Выведет 2
        System.out.println(hashTable.isEmpty()); // Выведет false

        hashTable.remove("apple");
        System.out.println(hashTable.get("apple")); // Выведет null, так как ключ "apple" удален
        hashTable.put("apple", 5);
        System.out.println(hashTable.get("apple")); // Выведет 5
        System.out.println(hashTable.size()); // Выведет 2

    }
}
