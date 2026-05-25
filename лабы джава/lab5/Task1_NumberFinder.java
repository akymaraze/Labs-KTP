package lab5;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;

/**
 * Задание 1: Поиск всех чисел в тексте
 * Программа ищет все числа (целые и десятичные) в заданном тексте
 */
public class Task1_NumberFinder {
    public static void main(String[] args) {
        String text = "The price of the product is $19.99, and the discount is 5. " +
                      "You save 10 dollars. Total: 123.456 and 7890. Version 2.0";
        
        System.out.println("Исходный текст: " + text);
        
        // Регулярное выражение для поиска чисел (целые и десятичные)
        // \\d+ - одна или более цифр
        // (?:\\.\\d+)? - опционально точка и ещё цифры (для десятичных чисел)
        Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);
        
        List<String> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(matcher.group());
        }
        
        System.out.println("Найденные числа: " + String.join(", ", numbers));
    }
}