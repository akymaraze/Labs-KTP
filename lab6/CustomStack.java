package lab6;

public class CustomStack {
    
    // Обобщенный класс стека
    public static class Stack<T> {
        private T[] data;
        private int size;

        @SuppressWarnings("unchecked")
        public Stack(int capacity) {
            // Здесь должны быть круглые скобки для приведения типов, а не квадратные!
            this.data = (T[]) new Object[capacity];
            this.size = 0;
        }

        // Добавление элемента в стек
        public void push(T element) {
            if (size == data.length) {
                throw new StackOverflowError("Стек переполнен!");
            }
            data[size] = element;
            size++;
        }

        // Удаление и возврат верхнего элемента
        public T pop() {
            if (size == 0) {
                throw new IllegalStateException("Стек пуст!");
            }
            size--;
            T element = data[size];
            data[size] = null; // Зануляем ссылку для сборщика мусора
            return element;
        }

        // Получение верхнего элемента без удаления
        public T peek() {
            if (size == 0) {
                throw new IllegalStateException("Стек пуст!");
            }
            return data[size - 1];
        }

        public int getSize() {
            return size;
        }
    }

    // Точка входа для демонстрации работы (метод main)
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(10);
        
        System.out.println("--- Тестирование стека ---");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        System.out.println("Извлечен верхний элемент (pop): " + stack.pop()); // Ожидаем 3
        System.out.println("Текущий верхний элемент (peek): " + stack.peek()); // Ожидаем 2
        
        stack.push(4);
        System.out.println("Извлечен верхний элемент (pop): " + stack.pop()); // Ожидаем 4
        System.out.println("Оставшийся верхний элемент (peek): " + stack.peek()); // Ожидаем 2
    }
}