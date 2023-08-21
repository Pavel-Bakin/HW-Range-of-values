import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }

        long startTs = System.currentTimeMillis(); // start time

        List<Thread> threads = new ArrayList<>();

        for (String text : texts) {
            Thread thread = new Thread(() -> {
                processText(text);
            });
            threads.add(thread);
            thread.start();
        }


        long endTs = System.currentTimeMillis(); // end time

        System.out.println("Time: " + (endTs - startTs) + "ms");

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void processText(String text) {
        int maxSize = 0;
        int currentSize = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'a') {
                currentSize++;
                maxSize = Math.max(maxSize, currentSize);
            } else {
                currentSize = 0;
            }
        }

        System.out.println(text.substring(0, Math.min(100, text.length())) + " -> " + maxSize);
    }
}
