import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class WheelOfFortune extends GuessingGame {
    private List<String> phrases;
    private int currentPhraseIndex;

    public WheelOfFortune(int maxAttempts) {
        super(maxAttempts);
        loadPhrases("WheelOfFortunePhrases.txt");
    }


    private void loadPhrases(String filename) {
        phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                phrases.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error loading phrases: " + e.getMessage());
        }
        shufflePhrases();
    }


    private void shufflePhrases() {
        Collections.shuffle(phrases);
        currentPhraseIndex = 0;
    }


    protected String getNextPhrase() {
        if (phrases.isEmpty()) {
            System.out.println("No phrases available.");
            return null;
        }


        if (currentPhraseIndex >= phrases.size()) {
            shufflePhrases();
        }


        String phrase = phrases.get(currentPhraseIndex);
        currentPhraseIndex++;
        return phrase;
    }

    protected void resetBuffer() {
        shufflePhrases();
    }

    protected List<String> getPhrases() {
        return new ArrayList<>(phrases); 
    }

    protected abstract boolean processGuess(String phrase, char guess);
}
