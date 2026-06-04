package lab7;
import java.util.*;
import java.util.concurrent.*;

class Item {
    private final String name;
    private final int weight;

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}

class Warehouse {
    private final Queue<Item> items = new LinkedList<>();

    public Warehouse(List<Item> items) {
        this.items.addAll(items);
    }

    public synchronized List<Item> loadUpTo(int maxWeight) {
        List<Item> loaded = new ArrayList<>();
        int currentWeight = 0;
        while (!items.isEmpty() && currentWeight + items.peek().getWeight() <= maxWeight) {
            Item item = items.poll();
            loaded.add(item);
            currentWeight += item.getWeight();
        }
        return loaded;
    }
}

public class LoaderCompletableFuture {

    public static void main(String[] args) {
        List<Item> stock = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            stock.add(new Item("Товар" + i, 30 + (i % 5) * 10));
        }

        Warehouse warehouse = new Warehouse(stock);
        int maxWeight = 150;

        CompletableFuture<Void> loader1 = CompletableFuture.runAsync(() -> doWork(warehouse, maxWeight, "Грузчик-1"));
        CompletableFuture<Void> loader2 = CompletableFuture.runAsync(() -> doWork(warehouse, maxWeight, "Грузчик-2"));
        CompletableFuture<Void> loader3 = CompletableFuture.runAsync(() -> doWork(warehouse, maxWeight, "Грузчик-3"));

        CompletableFuture.allOf(loader1, loader2, loader3).join();
        System.out.println("Все товары перенесены.");
    }

    private static void doWork(Warehouse warehouse, int maxWeight, String name) {
        while (true) {
            List<Item> load = warehouse.loadUpTo(maxWeight);
            if (load.isEmpty()) {
                break;
            }
            int totalWeight = load.stream().mapToInt(Item::getWeight).sum();
            System.out.println(name + " переносит: " + load.size() + " товаров, вес: " + totalWeight + " кг");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            System.out.println(name + " разгрузил товары на другом складе.");
        }
    }
}