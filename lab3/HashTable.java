package def;
//HashTable.java
import java.util.LinkedList;

public class HashTable<K, V> {
 private LinkedList<Entry<K, V>>[] table;
 private int size;          // количество элементов в таблице
 private static final int DEFAULT_CAPACITY = 10;

 // Конструктор по умолчанию
 public HashTable() {
     this(DEFAULT_CAPACITY);
 }

 // Конструктор с указанием начальной емкости
 @SuppressWarnings("unchecked")
 public HashTable(int capacity) {
     table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
     size = 0;
 }

 // Хэш-функция (простая, на основе hashCode)
 private int hash(K key) {
     if (key == null) {
         return 0;
     }
     return Math.abs(key.hashCode()) % table.length;
 }

 // Метод put - добавление или обновление пары ключ-значение
 public void put(K key, V value) {
     if (key == null) {
         throw new IllegalArgumentException("Ключ не может быть null");
     }

     int index = hash(key);
     
     // Если в индексе еще нет списка, создаем его
     if (table[index] == null) {
         table[index] = new LinkedList<Entry<K, V>>();
     }
     
     // Проверяем, существует ли уже такой ключ
     for (Entry<K, V> entry : table[index]) {
         if (entry.getKey().equals(key)) {
             // Ключ найден - обновляем значение
             entry.setValue(value);
             return;
         }
     }
     
     // Ключ не найден - добавляем новую запись
     table[index].add(new Entry<K, V>(key, value));
     size++;
 }

 // Метод get - получение значения по ключу
 public V get(K key) {
     if (key == null) {
         throw new IllegalArgumentException("Ключ не может быть null");
     }

     int index = hash(key);
     
     if (table[index] == null) {
         return null;
     }
     
     for (Entry<K, V> entry : table[index]) {
         if (entry.getKey().equals(key)) {
             return entry.getValue();
         }
     }
     
     return null; // ключ не найден
 }

 // Метод remove - удаление пары по ключу
 public V remove(K key) {
     if (key == null) {
         throw new IllegalArgumentException("Ключ не может быть null");
     }

     int index = hash(key);
     
     if (table[index] == null) {
         return null;
     }
     
     for (Entry<K, V> entry : table[index]) {
         if (entry.getKey().equals(key)) {
             V removedValue = entry.getValue();
             table[index].remove(entry);
             size--;
             
             // Если список стал пустым, удаляем его для оптимизации
             if (table[index].isEmpty()) {
                 table[index] = null;
             }
             return removedValue;
         }
     }
     
     return null; // ключ не найден
 }

 // Метод size - возвращает количество элементов
 public int size() {
     return size;
 }

 // Метод isEmpty - проверяет, пуста ли таблица
 public boolean isEmpty() {
     return size == 0;
 }

 // Метод containsKey - проверяет наличие ключа
 public boolean containsKey(K key) {
     return get(key) != null;
 }

 // Метод clear - очищает всю таблицу
 @SuppressWarnings("unchecked")
 public void clear() {
     table = (LinkedList<Entry<K, V>>[]) new LinkedList[table.length];
     size = 0;
 }

 // Метод для вывода всех элементов (для отладки)
 public void printAll() {
     System.out.println("\n=== Содержимое хэш-таблицы ===");
     System.out.println("Всего элементов: " + size);
     for (int i = 0; i < table.length; i++) {
         if (table[i] != null && !table[i].isEmpty()) {
             System.out.println("Индекс " + i + ": " + table[i]);
         } else {
             System.out.println("Индекс " + i + ": null");
         }
     }
     System.out.println("===============================\n");
 }
//Метод для получения всех ключей (в виде списка)
 public LinkedList<K> keys() {
     LinkedList<K> keys = new LinkedList<>();
     for (int i = 0; i < table.length; i++) {
         if (table[i] != null) {
             for (Entry<K, V> entry : table[i]) {
                 keys.add(entry.getKey());
             }
         }
     }
     return keys;
 }
}
