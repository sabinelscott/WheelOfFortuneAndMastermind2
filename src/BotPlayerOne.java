import java.util.Random;

public class BotPlayerOne implements WheelOfFortunePlayer {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random random = new Random();
    private String playerId = "BotPlayerOne";

    @Override
    public char nextGuess() {
        return ALPHABET.charAt(random.nextInt(ALPHABET.length()));  // Random letter
    }

    @Override
    public String playerId() {
        return playerId;
    }

    @Override
    public void reset() {
        // No state to reset for random guessing
    }
}
