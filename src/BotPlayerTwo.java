public class BotPlayerTwo implements WheelOfFortunePlayer {
    private static final String FREQUENCY_ORDER = "ETAOINSHRDLCUMWFGYPBVKJXQZ";
    private int currentIndex = 0;
    private String playerId = "BotPlayerTwo";

    @Override
    public char nextGuess() {
        if (currentIndex < FREQUENCY_ORDER.length()) {
            return FREQUENCY_ORDER.charAt(currentIndex++);
        } else {
            return FREQUENCY_ORDER.charAt(FREQUENCY_ORDER.length() - 1);
        }
    }

    @Override
    public String playerId() {
        return playerId;
    }

    @Override
    public void reset() {
        currentIndex = 0;  // Reset to start guessing from the most frequent letter
    }
}
