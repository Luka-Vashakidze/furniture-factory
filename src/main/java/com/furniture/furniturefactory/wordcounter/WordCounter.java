    package com.furniture.furniturefactory.wordcounter;

    import org.apache.commons.io.FileUtils;
    import org.apache.commons.lang3.StringUtils;

    import java.io.File;
    import java.io.IOException;
    import java.util.HashSet;
    import java.util.Set;

    public class WordCounter {

        public static void processBook(String inputFile, String outputFile) throws IOException {

            String content = FileUtils.readFileToString(new File(inputFile), "UTF-8");
            String[] words = content.split("\\W+");

            Set<String> uniqueWords = new HashSet<>();

            for (String word : words) {
                if (StringUtils.isNotBlank(word)) {
                    uniqueWords.add(word.toLowerCase());
                }
            }

            String result = " unique words: " + uniqueWords.size();
            result += "Unique words:\n";

            for (String word : uniqueWords) {
                result += word + "\n";
            }
            FileUtils.writeStringToFile(new File(outputFile), result, "UTF-8");
        }

        public static void main(String[] args) throws IOException {
            processBook("src/main/resources/book.txt", "src/main/resources/words.txt");
        }
    }