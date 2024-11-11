import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortune implements AutoCloseable {
    private Scanner scanner = new Scanner(System.in);
    private int score = 0;
    private int attempts = 0;
    private String phrase;
    private StringBuilder hiddenPhrase;

    public WheelOfFortuneUserGame(int maxAttempts) {
        super(maxAttempts);
    }

    @Override
    protected void initializeGame() {
        score = 0;
        attempts = 0;
        phrase = getNextPhrase();
        if (phrase == null) {
            System.out.println("No more phrases available.");
            return;
        }
        hiddenPhrase = new StringBuilder();
        for (char c : phrase.toCharArray()) {
            if (c == ' ') {
                hiddenPhrase.append(' ');
            } else {
                hiddenPhrase.append('*');
            }
        }
    }

    @Override
    protected char getNextGuess() {
        while (true) {
            System.out.print("Enter your guess: ");
            String input = scanner.next().toUpperCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Error: Please enter a single alphabetic character.");
            } else {
                return input.charAt(0);
            }
        }
    }

    @Override
    protected boolean playNext() {
        System.out.print("Do you want to play another game? (y/n): ");
        return scanner.next().equalsIgnoreCase("y");
    }

    @Override
    protected GameRecord play() {
        initializeGame();
        if (phrase == null) {
            return null;
        }

        while (!isGameOver()) {
            System.out.println("Current phrase: " + hiddenPhrase);
            char guess = getNextGuess();

            if (processGuess(phrase, guess)) {
                score += 10;
                System.out.println("Correct guess!");
            } else {
                attempts++;  // Increment attempts only on an incorrect guess
                System.out.println("Incorrect guess.");
            }
            System.out.println("Attempts left: " + (maxAttempts - attempts));
        }

        System.out.println("The phrase was: " + phrase);
        return recordGameResult();
    }

    @Override
    protected boolean processGuess(String phrase, char guess) {
        boolean isCorrect = false;
        char normalizedGuess = Character.toUpperCase(guess);  // Normalize guessed character to uppercase

        // Loop through each character in the phrase to reveal all occurrences of the guessed letter (ignoring case)
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toUpperCase(phrase.charAt(i)) == normalizedGuess) {
                hiddenPhrase.setCharAt(i, phrase.charAt(i));  // Reveal the letter in its original case
                isCorrect = true;
            }
        }
        return isCorrect;
    }

    @Override
    protected GameRecord recordGameResult() {
        return new GameRecord(score, "User");
    }

    @Override
    protected boolean isGameOver() {
        return attempts >= maxAttempts || hiddenPhrase.indexOf("*") == -1;
    }

    @Override
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        try (WheelOfFortuneUserGame game = new WheelOfFortuneUserGame(11)) {
            AllGamesRecord records = game.playAll();
            System.out.println("User Game Records:");
            System.out.println(records);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
