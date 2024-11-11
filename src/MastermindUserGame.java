import java.util.Scanner;

public class MastermindUserGame extends Mastermind {
    private Scanner scanner = new Scanner(System.in);

    public MastermindUserGame(int maxAttempts) {
        super(maxAttempts);
    }

    @Override
    protected String getUserGuess() {
        System.out.print("Enter your guess (4 characters, using R, G, B, Y, O, P): ");
        String guess = scanner.next().toUpperCase();

        while (guess.length() != CODE_SIZE || !guess.matches("[" + COLORS + "]+")) {
            System.out.print("Invalid input. Please enter 4 characters using R, G, B, Y, O, P: ");
            guess = scanner.next().toUpperCase();
        }
        return guess;
    }

    @Override
    protected GameRecord play() {
        initializeGame();
        System.out.println("Welcome to Mastermind! Try to guess the secret code.");

        while (attempts < maxAttempts) {
            String guess = getUserGuess();
            int exactMatches = checkExacts(guess);
            int partialMatches = checkPartials(guess);

            System.out.println("Exact Matches: " + exactMatches + ", Partial Matches: " + partialMatches);
            attempts++;

            if (exactMatches == CODE_SIZE) {
                System.out.println("Congratulations! You've guessed the code!");
                return new GameRecord(100, "User");
            }
        }

        System.out.println("Game over! You've used all attempts. The secret code was: " + secretCode);
        return new GameRecord(0, "User");
    }

    @Override
    protected boolean playNext() {
        System.out.print("Do you want to play another Mastermind game? (y/n): ");
        return scanner.next().equalsIgnoreCase("y");
    }

    @Override
    protected boolean processGuess(String phrase, char guess) {
        return false;
    }

    public static void main(String[] args) {
        MastermindUserGame game = new MastermindUserGame(10);
        AllGamesRecord records = game.playAll();
        System.out.println("Mastermind Game Records:");
        System.out.println(records);
    }
}
