package def;
//Contact.java
public class Contact {
 private String name;
 private String email;
 private String additionalInfo; // дополнительные контактные данные

 // Конструктор по умолчанию
 public Contact() {
     this("Unknown", "unknown@example.com", "Нет дополнительной информации");
 }

 // Конструктор с параметрами
 public Contact(String name, String email, String additionalInfo) {
     this.name = name;
     this.email = email;
     this.additionalInfo = additionalInfo;
 }

 // Геттеры и сеттеры
 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getEmail() {
     return email;
 }

 public void setEmail(String email) {
     this.email = email;
 }

 public String getAdditionalInfo() {
     return additionalInfo;
 }

 public void setAdditionalInfo(String additionalInfo) {
     this.additionalInfo = additionalInfo;
 }

 // Переопределение toString для удобного вывода
 @Override
 public String toString() {
     return "Contact{" +
             "name='" + name + '\'' +
             ", email='" + email + '\'' +
             ", additionalInfo='" + additionalInfo + '\'' +
             '}';
 }
}


