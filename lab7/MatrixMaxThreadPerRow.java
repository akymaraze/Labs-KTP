package lab7;
public class MatrixMaxThreadPerRow {

    static class RowMaxThread extends Thread {
        private final int[] row;
        private int max;

        public RowMaxThread(int[] row) {
            this.row = row;
            this.max = Integer.MIN_VALUE;
        }

        @Override
        public void run() {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }

        public int getMax() {
            return max;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int rows = 10;
        int cols = 10;
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }

        RowMaxThread[] threads = new RowMaxThread[rows];
        for (int i = 0; i < rows; i++) {
            threads[i] = new RowMaxThread(matrix[i]);
            threads[i].start();
        }

        for (RowMaxThread thread : threads) {
            thread.join();
        }

        int globalMax = Integer.MIN_VALUE;
        for (RowMaxThread thread : threads) {
            if (thread.getMax() > globalMax) {
                globalMax = thread.getMax();
            }
        }

        System.out.println("Максимальный элемент в матрице: " + globalMax);
    }
}