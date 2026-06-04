package def;
//Main.java
public class Main {
 public static void main(String[] args) {
     System.out.println("=== Лабораторная работа №2. Вариант 1 (Животные) ===\n");

     // Демонстрация создания объектов и счетчика
     System.out.println("--- Создание объектов ---");
     Cat cat1 = new Cat("Мурка", 3, 4.5, "Сиамская");
     Parrot parrot1 = new Parrot("Кеша", 2, 0.5, true);
     Fish fish1 = new Fish("Золотая", 1, 0.1, "freshwater");
     Cat cat2 = new Cat(); // Используем конструктор по умолчанию
     cat2.setName("Барсик");
     cat2.setAge(5);
     cat2.setWeight(6.0);
     cat2.setBreed("Дворняжка");

     System.out.println("\nВсего создано животных: " + Animal.getTotalAnimalsCreated());

     // Демонстрация работы методов (полиморфизм)
     System.out.println("\n--- Демонстрация полиморфизма (массив Animal) ---");
     Animal[] animals = {cat1, parrot1, fish1, cat2};

     for (Animal animal : animals) {
         System.out.println("\nИнформация: " + animal);
         animal.makeSound();   // Вызов переопределенного метода
         animal.move();        // Вызов переопределенного метода
         animal.eat();         // Вызов унаследованного метода

         if (animal instanceof Cat) {
             Cat tempCat = (Cat) animal;
             tempCat.eat("рыбу"); // Вызов перегруженного метода
         } else if (animal instanceof Parrot) {
             Parrot tempParrot = (Parrot) animal;
             tempParrot.move(true); // Вызов перегруженного метода
         } else if (animal instanceof Fish) {
             Fish tempFish = (Fish) animal;
             tempFish.hide();
         }
     }

     // Демонстрация работы сеттеров и геттеров
     System.out.println("\n--- Работа с геттерами и сеттерами ---");
     cat1.setAge(4);
     System.out.println("Новый возраст " + cat1.getName() + ": " + cat1.getAge());
     parrot1.setCanTalk(false);
     System.out.println("Умеет ли " + parrot1.getName() + " говорить? " + parrot1.isCanTalk());

     // Вывод информации об объектах через toString
     System.out.println("\n--- Финальная информация об объектах ---");
     System.out.println(cat1);
     System.out.println(parrot1);
     System.out.println(fish1);
     System.out.println(cat2);
 }
}