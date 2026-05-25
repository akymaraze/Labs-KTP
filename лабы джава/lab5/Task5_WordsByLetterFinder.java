package lab5;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Задание 5: Поиск всех слов, начинающихся с заданной буквы
 * Программа ищет все слова в тексте, начинающиеся с указанной буквы
 */
public class Task5_WordsByLetterFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Поиск слов по начальной букве ===");
        
        System.out.print("Введите букву для поиска: ");
        String letter = scanner.nextLine();
        
        System.out.print("Введите текст для поиска: ");
        String text = scanner.nextLine();
        
        // Регулярное выражение для поиска слов, начинающихся с заданной буквы
        // \\b - граница слова
        // letter + [A-Za-zА-Яа-я]* - буква и любые последующие буквы
        String regex = "\\b" + Pattern.quote(letter) + "[A-Za-zА-Яа-я]*\\b";
        
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        
        List<String> foundWords = new ArrayList<>();
        while (matcher.find()) {
            foundWords.add(matcher.group());
        }
        
        System.out.println();
        System.out.println("Результаты поиска:");
        System.out.println("Текст: \"" + text + "\"");
        System.out.println("Буква: '" + letter + "'");
        
        if (foundWords.isEmpty()) {
            System.out.println("Слова, начинающиеся с буквы '" + letter + "', не найдены.");
        } else {
            System.out.println("Найденные слова: " + String.join(", ", foundWords));
        }
        
        scanner.close();
    }
}