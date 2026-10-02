package lab4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) throws IOException {
        
        System.out.println("Чамкин Антон; 25ВП3; Лабараторная работа №4; Вариант 15");
        System.out.println("Программа подсчета частоты слов и словосочетаний в тексте.");

        var textBuilder = new StringBuilder();
        
        try {
        List<String> lines = Files.readAllLines(Path.of("src/main/java/lab4/TEXT"), StandardCharsets.UTF_8);

        for (String line : lines) 
            textBuilder.append(line).append(" ");

        } 
        catch (IOException e) {
                System.err.println("Ошибка чтения файла: " + e.getMessage());
                return;
        }   

        var text = textBuilder.toString();

        final Map<String, Integer> frequency = new HashMap<>();

        text = text.replaceAll("[\\n\\r]", " ");

        for (var word : text.split(" "))
            frequency.put(word.trim(), frequency.getOrDefault(word, 0) + 1);

        text = text.replaceAll("[,.?!:;()\\[\\]{}\\—]", "\\.");

        for (var sentence : text.split("\\.")) {
            final var words = sentence.trim().split(" ");
            
            for (var word : words){
                var currentSentence = new StringBuilder();
                currentSentence.append(word);
                for (var nextWord : words){
                    var result = currentSentence.append(" ").append(nextWord).toString().trim();
                    frequency.put(result, frequency.getOrDefault(result, 0) + 1);
                }
            }
        }

        frequency.remove("");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(frequency);

        Files.writeString(Path.of("src/main/java/lab4/RESULT.json"),json);

        System.out.println("Результаты подсчета частоты слов и словосочетаний сохранены в файл RESULT.json");
    }

}