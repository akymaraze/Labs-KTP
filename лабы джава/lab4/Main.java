package lab4;
//Main.java
import java.util.Scanner;

public class Main {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     System.out.print("Введите email для проверки: ");
     String email = scanner.nextLine();

     try {
         EmailValidator.validate(email);
         System.out.println("✓ Email корректен: " + email);
     } catch (CustomEmailFormatException e) {
         System.out.println("✗ Ошибка: " + e.getMessage());
         ExceptionLogger.log(e);
     }

     // Дополнительные тесты
     System.out.println("\n--- Дополнительные тесты ---");
     String[] testEmails = {
         "user@example.com",
         "ivan.ivanov@mail.ru",
         "invalid-email",
         "test@test",
         "missing@domain.",
         "john.doe@sub.domain.co"
     };

     for (String testEmail : testEmails) {
         try {
             EmailValidator.validate(testEmail);
             System.out.println("✓ " + testEmail);
         } catch (CustomEmailFormatException e) {
             System.out.println("✗ " + testEmail + " -> " + e.getMessage());
             ExceptionLogger.log(e);
         }
     }

     scanner.close();
 }
}