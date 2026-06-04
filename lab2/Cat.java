package def;
// Cat.java
public class Cat extends Animal {
    private String breed; // Порода (дополнительное поле)

    // Конструктор по умолчанию
    public Cat() {
        super();
        this.breed = "Unknown";
    }

    // Конструктор с параметрами
    public Cat(String name, int age, double weight, String breed) {
        super(name, age, weight); // Вызов конструктора родителя
        this.breed = breed;
    }

    // Геттер и сеттер
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    // Реализация абстрактных методов (переопределение)
    @Override
    public void makeSound() {
        System.out.println(getName() + (" мяукает: Мяу-мяу!"));
    }

    @Override
    public void move() {
        System.out.println(getName() + " тихо крадется на мягких лапах.");
    }

    // Перегрузка метода eat (пример полиморфизма через перегрузку)
    public void eat(String food) {
        System.out.println(getName() + " (кошка) ест " + food + " с удовольствием.");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", weight=" + getWeight() +
                ", breed='" + breed + '\'' +
                '}';
    }
}