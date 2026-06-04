package lab5;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Задание 3: Поиск заглавной буквы после строчной
 * Программа находит все случаи, когда сразу после строчной буквы
 * идет заглавная, и выделяет их знаками «!» с двух сторон
 */
public class Task3_UppercaseAfterLowercase {
    public static void main(String[] args) {
        String text = "привет World, как Hello дела? aBc dEf xYz. примерТекста";
        
        System.out.println("Исходный текст: " + text);
        
        // Регулярное выражение для поиска строчной буквы, за которой следует заглавная
        // ([a-zа-я]) - строчная буква (латиница или кириллица)
        // ([A-ZА-Я]) - заглавная буква
        Pattern pattern = Pattern.compile("([a-zа-я])([A-ZА-Я])");
        Matcher matcher = pattern.matcher(text);
        
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            // Заменяем найденную пару на "!строчнаяЗаглавная!"
            matcher.appendReplacement(result, "!" + matcher.group(1) + matcher.group(2) + "!");
        }
        matcher.appendTail(result);
        
        System.out.println("Результат: " + result.toString());
    }
}