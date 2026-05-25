package lab7;

public class ArraySumThreadPerElement {

    static class SumThread extends Thread {
        private final int[] array;
        private final int startIndex;
        private final int endIndex;
        private int sum;

        // Поток принимает весь массив и диапазон индексов для обработки
        public SumThread(int[] array, int startIndex, int endIndex) {
            this.array = array;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.sum = 0;
        }

        @Override
        public void run() {
            // Поток считает сумму только на своем участке массива
            for (int i = startIndex; i < endIndex; i++) {
                sum += array[i];
            }
        }

        public int getSum() {
            return sum;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        // Создаем массив ровно из двух потоков
        SumThread[] threads = new SumThread[2];
        int mid = array.length / 2; // Точка разделения массива (индекс 50)

        // Первый поток считает элементы от 0 до 50 (не включая 50)
        threads[0] = new SumThread(array, 0, mid);
        // Второй поток считает элементы от 50 до 100 (не включая 100)
        threads[1] = new SumThread(array, mid, array.length);

        // Запускаем оба потока параллельно
        threads[0].start();
        threads[1].start();

        // Ждем завершения работы обоих потоков
        threads[0].join();
        threads[1].join();

        // Складываем результаты работы двух потоков
        int totalSum = threads[0].getSum() + threads[1].getSum();

        System.out.println("Сумма элементов массива: " + totalSum);
    }
}
