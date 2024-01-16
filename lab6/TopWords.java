import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TopWords {
    public static void main(String[] args) {
        // Укажите путь к вашему текстовому файлу
        String filePath = "/Users/velimirhlebnikov/МТУСИ/ИТ/labs/lab6/text.txt";

        try {
            // Создаем BufferedReader для чтения файла
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Инициализируем Map для хранения слов и их частоты
            Map<String, Integer> wordFrequencyMap = new HashMap<>();

            String line;
            while ((line = reader.readLine()) != null) {
                // Разделяем строку на слова, используя пробел как разделитель
                String[] words = line.split(" ");

                // Обработка каждого слова
                for (String word : words) {
                    // Удаляем знаки препинания и приводим к нижнему регистру
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

                    // Пропускаем пустые слова
                    if (word.isEmpty()) {
                        continue;
                    }

                    // Обновляем частоту слова в Map
                    wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                }
            }

            // Закрываем BufferedReader
            reader.close();

            // Преобразуем Map в список пар (слово, частота)
            List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordFrequencyMap.entrySet());

            // Сортируем список в убывающем порядке по частоте и выводим топ-10 слов
            wordList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            for (int i = 0; i < Math.min(10, wordList.size()); i++) {
                System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
