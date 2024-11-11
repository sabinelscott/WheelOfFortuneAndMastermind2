public class BotPlayerThree implements WheelOfFortunePlayer {
    private static final char[] VOWELS = {'A', 'E', 'I', 'O', 'U'};
    private static final char[] CONSONANTS = {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'};
    private int vowelIndex = 0;
    private int consonantIndex = 0;
    private boolean guessVowel = true;  // Start with guessing a vowel
    private String playerId = "BotPlayerThree";

    @Override
    public char nextGuess() {
        char guess;
        if (guessVowel) {
            guess = VOWELS[vowelIndex++ % VOWELS.length];
        } else {
            guess = CONSONANTS[consonantIndex++ % CONSONANTS.length];
        }
        guessVowel = !guessVowel;  // Alternate between vowels and consonants
        return guess;
    }

    @Override
    public String playerId() {
        return playerId;
    }

    @Override
    public void reset() {
        vowelIndex = 0;
        consonantIndex = 0;
        guessVowel = true;
    }
}
