public class GuessingGame extends Game {
    protected int maxAttempts;
    protected int attempts;

    public GuessingGame(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    protected void initializeGame() {
        attempts = 0;
    }

    protected boolean processGuess(String phrase, char guess) {
        return phrase.indexOf(guess) >= 0;
    }

    protected GameRecord recordGameResult() {
        return new GameRecord(attempts * 10, "DefaultPlayer");
    }

    protected char getNextGuess() {
        return 'A';
    }

    @Override
    protected GameRecord play() {
        initializeGame();
        while (!isGameOver()) {
            char guess = getNextGuess();
            processGuess("example", guess);
            attempts++;
        }
        return recordGameResult();
    }

    @Override
    protected boolean playNext() {
        return attempts < maxAttempts;
    }

    protected boolean isGameOver() {
        return attempts >= maxAttempts;
    }
}
