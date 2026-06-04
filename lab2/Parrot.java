package def;
//Parrot.java
public class Parrot extends Animal {
 private boolean canTalk;

 public Parrot() {
     super();
     this.canTalk = false;
 }

 public Parrot(String name, int age, double weight, boolean canTalk) {
     super(name, age, weight);
     this.canTalk = canTalk;
 }

 public boolean isCanTalk() {
     return canTalk;
 }

 public void setCanTalk(boolean canTalk) {
     this.canTalk = canTalk;
 }

 @Override
 public void makeSound() {
     if (canTalk) {
         System.out.println(getName() + " говорит: Привет! Как дела?");
     } else {
         System.out.println(getName() + " чирикает: Чик-чирик!");
     }
 }

 @Override
 public void move() {
     System.out.println(getName() + " летает по комнате.");
 }

 // Перегрузка метода move (другой способ передвижения)
 public void move(boolean isWalk) {
     if (isWalk) {
         System.out.println(getName() + " перебирает лапками по жердочке.");
     } else {
         move(); // Вызов стандартного метода
     }
 }

 @Override
 public String toString() {
     return "Parrot{" +
             "name='" + getName() + '\'' +
             ", age=" + getAge() +
             ", weight=" + getWeight() +
             ", canTalk=" + canTalk +
             '}';
 }
}
