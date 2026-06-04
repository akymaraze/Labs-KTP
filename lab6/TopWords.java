package lab6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TopWords {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\andre\\Desktop\\text.txt"; 
        File file = new File(filePath);
        
        // 1. Создаем множество стоп-слов для фильтрации (в нижнем регистре)
        Set<String> stopWords = new HashSet<>(Arrays.asList(
            "и", "в", "на", "с", "со", "к", "о", "об", "обо", "не", "у", "за", "по", "из", "от", "до", 
            "для", "без", "перед", "под", "над", "при", "про", "а", "но", "да", "или", "что", "чтобы",
            "the", "a", "an", "in", "on", "at", "to", "for", "with", "by", "and", "but", "or"
        ));

        Map<String, Integer> wordCounts = new HashMap<>();
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase().replaceAll("[^a-zA-Zа-яА-Я0-9]", "");
                
                // 2. Добавлено условие: игнорируем пустые строки И стоп-слова
                if (!word.isEmpty() && !stopWords.contains(word)) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден по указанному пути: " + filePath);
            e.printStackTrace();
            return;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCounts.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        System.out.println("--- ТОП-10 самых частых слов в файле (без стоп-слов) ---");
        int count = 0;
        for (Map.Entry<String, Integer> entry : list) {
            if (count >= 10) break;
            System.out.println((count + 1) + ". " + entry.getKey() + " : " + entry.getValue());
            count++;
        }
        
        if (count == 0) {
            System.out.println("Файл оказался пустым или не содержит значимых слов.");
        }
    }
}
