public abstract class Game {
    protected AllGamesRecord allGamesRecord = new AllGamesRecord();

    public AllGamesRecord playAll() {
        while (playNext()) {
            GameRecord record = play();
            if (record != null) {
                allGamesRecord.add(record);
            }
        }
        return allGamesRecord;
    }

    protected abstract GameRecord play();
    protected abstract boolean playNext();

    public AllGamesRecord getAllGamesRecord() {
        return allGamesRecord;
    }
}
