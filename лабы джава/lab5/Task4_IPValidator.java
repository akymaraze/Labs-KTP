package lab5;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * Задание 4: Проверка корректности ввода IP-адреса
 * Требования:
 * - 4 числа, разделенные точками
 * - Каждое число от 0 до 255
 */
public class Task4_IPValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Проверка корректности IP-адреса ===");
        System.out.println("Требования: 4 числа от 0 до 255, разделенные точками");
        System.out.println("Пример: 192.168.1.1");
        System.out.println();
        
        System.out.print("Введите IP-адрес: ");
        String ip = scanner.nextLine();
        
        // Регулярное выражение для числа от 0 до 255:
        // 25[0-5]   - 250-255
        // 2[0-4][0-9] - 200-249
        // 1[0-9]{2}  - 100-199
        // [1-9][0-9]? - 1-99
        // 0          - 0
        String octetRegex = "(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?|0)";
        String fullRegex = "^" + octetRegex + "\\." + octetRegex + "\\." + 
                          octetRegex + "\\." + octetRegex + "$";
        
        boolean isValid = Pattern.matches(fullRegex, ip);
        
        if (isValid) {
            System.out.println("✓ IP-адрес корректный!");
        } else {
            System.out.println("✗ IP-адрес НЕ корректен!");
            System.out.println("Убедитесь, что адрес содержит 4 числа от 0 до 255, разделенных точками.");
        }
        
        scanner.close();
    }
}
