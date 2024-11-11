import java.util.Random;

public abstract class Mastermind extends GuessingGame {
    protected static final int CODE_SIZE = 4;
    protected static final String COLORS = "RGBYOP";
    protected String secretCode;

    public Mastermind(int maxAttempts) {
        super(maxAttempts);
    }

    protected void generateSecretCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < CODE_SIZE; i++) {
            codeBuilder.append(COLORS.charAt(random.nextInt(COLORS.length())));
        }
        this.secretCode = codeBuilder.toString();
    }

    protected int checkExacts(String guess) {
        int exactMatches = 0;
        for (int i = 0; i < CODE_SIZE; i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                exactMatches++;
            }
        }
        return exactMatches;
    }

    protected int checkPartials(String guess) {
        int partialMatches = 0;
        boolean[] secretUsed = new boolean[CODE_SIZE];
        boolean[] guessUsed = new boolean[CODE_SIZE];

        for (int i = 0; i < CODE_SIZE; i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                secretUsed[i] = true;
                guessUsed[i] = true;
            }
        }

        for (int i = 0; i < CODE_SIZE; i++) {
            if (!guessUsed[i]) {
                for (int j = 0; j < CODE_SIZE; j++) {
                    if (!secretUsed[j] && guess.charAt(i) == secretCode.charAt(j)) {
                        partialMatches++;
                        secretUsed[j] = true;
                        guessUsed[i] = true;
                        break;
                    }
                }
            }
        }

        return partialMatches;
    }

    protected abstract String getUserGuess();

    @Override
    protected void initializeGame() {
        generateSecretCode();
    }

    @Override
    protected boolean isGameOver() {
        return attempts >= maxAttempts;
    }

    @Override
    protected GameRecord recordGameResult() {
        int score = (attempts <= maxAttempts && checkExacts(secretCode) == CODE_SIZE) ? 100 : 0;
        return new GameRecord(score, "MastermindPlayer");
    }

    @Override
    protected char getNextGuess() {
        return 0;
    }
}
