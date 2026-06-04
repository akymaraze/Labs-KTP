package lab5;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * Задание 2: Проверка корректности ввода пароля
 * Требования:
 * - Только латинские буквы и цифры
 * - Длина от 8 до 16 символов
 * - Хотя бы одна заглавная буква
 * - Хотя бы одна цифра
 */
public class Task2_PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Проверка корректности пароля ===");
        System.out.println("Требования:");
        System.out.println("- Только латинские буквы и цифры");
        System.out.println("- Длина от 8 до 16 символов");
        System.out.println("- Хотя бы одна заглавная буква");
        System.out.println("- Хотя бы одна цифра");
        System.out.println();
        
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        
        // Регулярное выражение:
        // ^ - начало строки
        // (?=.*[A-Z]) - должен быть хотя бы один символ A-Z
        // (?=.*\\d) - должна быть хотя бы одна цифра
        // [A-Za-z\\d] - только латинские буквы и цифры
        // {8,16} - длина от 8 до 16
        // $ - конец строки
        String regex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
        
        boolean isValid = Pattern.matches(regex, password);
        
        if (isValid) {
            System.out.println("✓ Пароль корректный!");
        } else {
            System.out.println("✗ Пароль НЕ соответствует требованиям!");
        }
        
        scanner.close();
    }
}