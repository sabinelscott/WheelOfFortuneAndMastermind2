
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AllGamesRecord {
    private List<GameRecord> records = new ArrayList<>();


    public void add(GameRecord record) {
        records.add(record);
    }


    public double average() {
        return records.stream().mapToInt(GameRecord::getScore).average().orElse(0.0);
    }


    public double average(String playerId) {
        return records.stream()
                .filter(r -> r.getPlayerId().equals(playerId))
                .mapToInt(GameRecord::getScore)
                .average()
                .orElse(0.0);
    }


    public List<GameRecord> highGameList(int n) {
        return records.stream()
                .sorted()
                .limit(n)
                .collect(Collectors.toList());
    }


    public List<GameRecord> highGameList(String playerId, int n) {
        return records.stream()
                .filter(r -> r.getPlayerId().equals(playerId))
                .sorted()
                .limit(n)
                .collect(Collectors.toList());
    }


    public List<GameRecord> getAllRecords() {
        return records;
    }

    @Override
    public String toString() {
        return "All Game Records: " + records;
    }
}
