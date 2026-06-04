package lab4;
//ExceptionLogger.java
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionLogger {
 private static final String LOG_FILE = "exceptions.log";

 public static void log(Exception e) {
     try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         writer.println(LocalDateTime.now().format(formatter) + " - " + e.toString());
         for (StackTraceElement element : e.getStackTrace()) {
             writer.println("\tat " + element);
         }
         writer.println();
     } catch (IOException ioException) {
         System.err.println("Не удалось записать в лог-файл: " + ioException.getMessage());
     }
 }
}