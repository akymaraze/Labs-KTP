package def;
//Fish.java
public class Fish extends Animal {
 private String waterType; // Тип воды: "freshwater" или "saltwater"

 public Fish() {
     super();
     this.waterType = "freshwater";
 }

 public Fish(String name, int age, double weight, String waterType) {
     super(name, age, weight);
     this.waterType = waterType;
 }

 public String getWaterType() {
     return waterType;
 }

 public void setWaterType(String waterType) {
     this.waterType = waterType;
 }

 @Override
 public void makeSound() {
     System.out.println(getName() + " молчит (пускает пузырьки)...");
 }

 @Override
 public void move() {
     System.out.println(getName() + " плавает в аквариуме.");
 }

 // Специфический метод для рыбок
 public void hide() {
     System.out.println(getName() + " спряталась за камнем!");
 }

 @Override
 public String toString() {
     return "Fish{" +
             "name='" + getName() + '\'' +
             ", age=" + getAge() +
             ", weight=" + getWeight() +
             ", waterType='" + waterType + '\'' +
             '}';
 }
}
