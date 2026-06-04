package def;
//PhoneBookDemo.java
public class PhoneBookDemo {
 public static void main(String[] args) {
     System.out.println("=== Лабораторная работа №3. Вариант 6 ===");
     System.out.println("=== Телефонная книга (хэш-таблица) ===\n");

     // Создаем хэш-таблицу для хранения контактов
     // Ключ: String (номер телефона), Значение: Contact
     HashTable<String, Contact> phoneBook = new HashTable<>();

     // ===== 1. Демонстрация операции вставки =====
     System.out.println("--- 1. Добавление контактов ---");
     
     Contact contact1 = new Contact("Иван Петров", "ivan@example.com", "Рабочий телефон: 123-45-67");
     Contact contact2 = new Contact("Мария Сидорова", "maria@example.com", "День рождения: 15.03");
     Contact contact3 = new Contact("Алексей Смирнов", "alex@example.com", "Telegram: @alex_smirnov");
     Contact contact4 = new Contact("Елена Козлова", "elena@example.com", "Skype: elena.kozlova");

     phoneBook.put("+7-911-123-45-67", contact1);
     phoneBook.put("+7-922-234-56-78", contact2);
     phoneBook.put("+7-933-345-67-89", contact3);
     phoneBook.put("+7-944-456-78-90", contact4);

     System.out.println("Добавлено 4 контакта");
     System.out.println("Текущий размер таблицы: " + phoneBook.size());

     // ===== 2. Демонстрация операции поиска =====
     System.out.println("\n--- 2. Поиск контактов по номеру телефона ---");
     
     String searchNumber = "+7-911-123-45-67";
     Contact found = phoneBook.get(searchNumber);
     if (found != null) {
         System.out.println("Найден контакт по номеру " + searchNumber + ":");
         System.out.println("  " + found);
     } else {
         System.out.println("Контакт с номером " + searchNumber + " не найден");
     }

     searchNumber = "+7-999-999-99-99";
     found = phoneBook.get(searchNumber);
     if (found != null) {
         System.out.println("Найден контакт по номеру " + searchNumber + ":");
         System.out.println("  " + found);
     } else {
         System.out.println("Контакт с номером " + searchNumber + " не найден");
     }

     // ===== 3. Демонстрация обновления контакта =====
     System.out.println("\n--- 3. Обновление контакта ---");
     Contact updatedContact = new Contact("Иван Петров (обновлен)", "ivan.new@example.com", "Домашний телефон: 987-65-43");
     phoneBook.put("+7-911-123-45-67", updatedContact);
     
     Contact checkUpdate = phoneBook.get("+7-911-123-45-67");
     System.out.println("После обновления:");
     System.out.println("  " + checkUpdate);

     // ===== 4. Демонстрация удаления контакта =====
     System.out.println("\n--- 4. Удаление контакта ---");
     System.out.println("Размер до удаления: " + phoneBook.size());
     
     Contact removed = phoneBook.remove("+7-933-345-67-89");
     if (removed != null) {
         System.out.println("Удален контакт:");
         System.out.println("  " + removed);
     }
     
     System.out.println("Размер после удаления: " + phoneBook.size());
     
     // Проверяем, что контакт действительно удален
     Contact checkRemoved = phoneBook.get("+7-933-345-67-89");
     if (checkRemoved == null) {
         System.out.println("Контакт с номером +7-933-345-67-89 больше не существует в телефонной книге");
     }

     // ===== 5. Демонстрация работы методов size и isEmpty =====
     System.out.println("\n--- 5. Методы size и isEmpty ---");
     System.out.println("Размер телефонной книги: " + phoneBook.size());
     System.out.println("Телефонная книга пуста? " + phoneBook.isEmpty());
  // ===== 6. Добавление еще одного контакта =====
     System.out.println("\n--- 6. Добавление нового контакта ---");
     Contact contact5 = new Contact("Дмитрий Иванов", "dmitry@example.com", "WhatsApp: +7-911-111-11-11");
     phoneBook.put("+7-955-567-89-01", contact5);
     System.out.println("Добавлен новый контакт: +7-955-567-89-01 -> " + contact5.getName());
     System.out.println("Новый размер: " + phoneBook.size());

     // ===== 7. Вывод всех ключей =====
     System.out.println("\n--- 7. Все номера телефонов в книге ---");
     for (String phoneNumber : phoneBook.keys()) {
         System.out.println("  Номер: " + phoneNumber);
     }

     // ===== 8. Демонстрация containsKey =====
     System.out.println("\n--- 8. Проверка наличия ключей ---");
     System.out.println("Есть номер +7-911-123-45-67? " + phoneBook.containsKey("+7-911-123-45-67"));
     System.out.println("Есть номер +7-933-345-67-89? " + phoneBook.containsKey("+7-933-345-67-89"));

     // ===== 9. Полный вывод таблицы (для демонстрации хэш-функции) =====
     phoneBook.printAll();

     // ===== 10. Демонстрация работы с null-ключом =====
     System.out.println("--- 9. Обработка исключений ---");
     try {
         phoneBook.put(null, contact1);
     } catch (IllegalArgumentException e) {
         System.out.println("Ошибка: " + e.getMessage());
     }

     // ===== 11. Демонстрация clear =====
     System.out.println("\n--- 10. Очистка телефонной книги ---");
     phoneBook.clear();
     System.out.println("После очистки - размер: " + phoneBook.size());
     System.out.println("Телефонная книга пуста? " + phoneBook.isEmpty());
 }
}