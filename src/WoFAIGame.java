import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WoFAIGame extends WheelOfFortune {
    private List<WheelOfFortunePlayer> players;
    private WheelOfFortunePlayer currentPlayer;
    private List<String> randomizedPhrases;

    public WoFAIGame(int maxAttempts, List<WheelOfFortunePlayer> players) {
        super(maxAttempts);
        loadAndShufflePhrases();
        this.players = players;
    }


    private void loadAndShufflePhrases() {
        randomizedPhrases = getPhrases();
        Collections.shuffle(randomizedPhrases);
    }

    public AllGamesRecord playAllForAllPlayers() {

        for (WheelOfFortunePlayer player : players) {
            this.currentPlayer = player;
            player.reset();

            for (String phrase : randomizedPhrases) {
                GameRecord record = playSingleGame(phrase);
                allGamesRecord.add(record);
            }
        }

        return allGamesRecord;
    }

    private GameRecord playSingleGame(String phrase) {
        int score = 0;
        int attempts = 0;
        StringBuilder hiddenPhrase = new StringBuilder();


        for (char c : phrase.toCharArray()) {
            hiddenPhrase.append(c == ' ' ? ' ' : '*');
        }


        while (attempts < maxAttempts && hiddenPhrase.indexOf("*") != -1) {
            char guess = currentPlayer.nextGuess();
            boolean correctGuess = false;


            for (int i = 0; i < phrase.length(); i++) {
                if (Character.toUpperCase(phrase.charAt(i)) == Character.toUpperCase(guess)) {
                    hiddenPhrase.setCharAt(i, phrase.charAt(i));
                    correctGuess = true;
                }
            }


            if (correctGuess) {
                score += 10;
            } else {
                attempts++;
            }
        }

        System.out.println("Player: " + currentPlayer.playerId() + ", Phrase: " + hiddenPhrase + ", Score: " + score);
        return new GameRecord(score, currentPlayer.playerId());
    }

    @Override
    protected boolean playNext() {
        return true;
    }

    @Override
    protected boolean processGuess(String phrase, char guess) {
        return phrase.indexOf(guess) >= 0;
    }

    @Override
    protected GameRecord recordGameResult() {
        return null;
    }

    @Override
    protected char getNextGuess() {
        return currentPlayer.nextGuess();
    }

    public static void main(String[] args) {

        List<WheelOfFortunePlayer> bots = List.of(new BotPlayerOne(), new BotPlayerTwo(), new BotPlayerThree());


        WoFAIGame game = new WoFAIGame(11, bots);
        AllGamesRecord records = game.playAllForAllPlayers();

        System.out.println("\nAI Game Records:");
        System.out.println(records);
    }
}
