package lab6;
import java.util.TreeSet;

public class SalesTracker {

    // Перенесли класс Product внутрь SalesTracker и сделали его static
    static class Product implements Comparable<Product> {
        private String name;
        private double price;
        private int quantitySold;

        public Product(String name, double price, int quantitySold) {
            this.name = name;
            this.price = price;
            this.quantitySold = quantitySold;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getQuantitySold() { return quantitySold; }

        public void addQuantity(int count) {
            this.quantitySold += count;
        }

        public double getTotalRevenue() {
            return this.price * this.quantitySold;
        }

        @Override
        public int compareTo(Product other) {
            return this.name.compareToIgnoreCase(other.name);
        }

        @Override
        public String toString() {
            return String.format("Товар: '%s' | Цена: %.2f руб. | Продано: %d шт. | Выручка: %.2f руб.", 
                    name, price, quantitySold, getTotalRevenue());
        }
    }

    // Коллекция TreeSet для хранения списка товаров
    private TreeSet<Product> soldProducts = new TreeSet<>();

    // Добавление проданного товара
    public void addSale(String name, double price, int quantity) {
        Product temp = new Product(name, price, quantity);
        
        if (soldProducts.contains(temp)) {
            for (Product p : soldProducts) {
                if (p.compareTo(temp) == 0) {
                    p.addQuantity(quantity);
                    break;
                }
            }
        } else {
            soldProducts.add(temp);
        }
    }

    // Вывод списка всех проданных товаров
    public void printAllSales() {
        System.out.println("\n--- Список проданных товаров (отсортирован по имени) ---");
        if (soldProducts.isEmpty()) {
            System.out.println("Продаж пока нет.");
            return;
        }
        for (Product p : soldProducts) {
            System.out.println(p);
        }
    }

    // Подсчет общей суммы всех продаж
    public double calculateTotalSalesAmount() {
        double total = 0;
        for (Product p : soldProducts) {
            total += p.getTotalRevenue();
        }
        return total;
    }

    // Поиск наиболее популярного товара
    public Product getMostPopularProduct() {
        if (soldProducts.isEmpty()) return null;
        
        Product mostPopular = null;
        for (Product p : soldProducts) {
            if (mostPopular == null || p.getQuantitySold() > mostPopular.getQuantitySold()) {
                mostPopular = p;
            }
        }
        return mostPopular;
    }

    // Демонстрация работы программы
    public static void main(String[] args) {
        SalesTracker tracker = new SalesTracker();

        tracker.addSale("Смартфон", 25000.00, 2);
        tracker.addSale("Ноутбук", 60000.00, 1);
        tracker.addSale("Наушники", 3500.00, 5);
        tracker.addSale("Смартфон", 25000.00, 1); 
        tracker.addSale("Чехол для телефона", 500.00, 10);

        tracker.printAllSales();

        System.out.printf("\nОбщая сумма продаж: %.2f руб.\n", tracker.calculateTotalSalesAmount());

        Product popular = tracker.getMostPopularProduct();
        if (popular != null) {
            System.out.println("Наиболее популярный товар: " + popular.getName() + " (" + popular.getQuantitySold() + " шт.)");
        }
    }
}