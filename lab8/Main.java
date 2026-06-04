package lab8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// 1. Создание аннотации @DataProcessor
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface DataProcessor {
}

// 3. Классы-обработчики данных (Stateless — не имеют состояния, поэтому потокобезопасны)
class FilterProcessor {
    @DataProcessor
    public String filterEmpty(String data) {
        // Фильтруем пустые строки или строки, состоящие только из пробелов
        return (data == null || data.trim().isEmpty()) ? null : data;
    }
}

class TransformProcessor {
    @DataProcessor
    public String toUpperCase(String data) {
        // Трансформируем текст в верхний регистр
        return data != null ? data.toUpperCase() : null;
    }
}

class DecorateProcessor {
    @DataProcessor
    public String decorate(String data) {
        // Агрегация/модификация: добавляем символы к строке
        return data != null ? "[" + data + "]" : null;
    }
}

// 2. Класс DataManager
class DataManager {
    private final List<Object> processors = new ArrayList<>();
    private List<String> data = new ArrayList<>();

    // Регистрирует объект-обработчик данных
    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    // Загружает данные из исходного источника (в данном случае из текстового файла)
    public void loadData(String source) {
        try {
            data = Files.readAllLines(Paths.get(source));
            System.out.println("Данные успешно загружены из " + source);
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }

    // Запускает многопоточную обработку данных через Parallel Stream API
    public void processData() {
        List<Method> annotatedMethods = new ArrayList<>();
        List<Object> targetProcessors = new ArrayList<>();

        // Собираем все методы с аннотацией @DataProcessor
        for (Object processor : processors) {
            for (Method method : processor.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    annotatedMethods.add(method);
                    targetProcessors.add(processor);
                }
            }
        }

        // Многопоточная обработка данных встроенным пулом ForkJoinPool
        this.data = data.parallelStream()
                .map(item -> {
                    String current = item;
                    try {
                        // Последовательно применяем цепочку рефлексивных методов к строке
                        for (int i = 0; i < annotatedMethods.size(); i++) {
                            if (current == null) break;
                            current = (String) annotatedMethods.get(i).invoke(targetProcessors.get(i), current);
                        }
                    } catch (Exception e) {
                        System.err.println("Ошибка при вызове метода обработчика: " + e.getMessage());
                        return null;
                    }
                    return current;
                })
                .filter(Objects::nonNull) // Исключаем из итогового списка отфильтрованные (null) элементы
                .collect(Collectors.toList());

        System.out.println("Данные успешно обработаны.");
    }

    // Сохраняет обработанные данные в новый источник
    public void saveData(String destination) {
        try {
            Files.write(Paths.get(destination), data);
            System.out.println("Результат сохранен в " + destination);
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }
}

// 5. Тестирование приложения
public class Main {
    public static void main(String[] args) {
        String sourceFile = "input.txt";
        String destFile = "output.txt";

        // Подготавливаем тестовые данные (создаем исходный файл)
        try {
            List<String> rawData = Arrays.asList(
                    "первая строка",
                    "   ", // Эта строка должна отфильтроваться
                    "java stream api",
                    "многопоточность",
                    ""
            );
            Files.write(Paths.get(sourceFile), rawData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataManager dataManager = new DataManager();

        // Регистрируем обработчики в конвейер
        dataManager.registerDataProcessor(new FilterProcessor());
        dataManager.registerDataProcessor(new TransformProcessor());
        dataManager.registerDataProcessor(new DecorateProcessor());

        // Запускаем конвейер
        dataManager.loadData(sourceFile);
        dataManager.processData();
        dataManager.saveData(destFile);
    }
}
