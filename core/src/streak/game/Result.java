package streak.game;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class Result {
    private final String age;
    private final String education;
    private final String gameActivity;
    private final String memoryActivity;
    private final String configuration;

    private int bestScore;

    private String id;

    public Result(String age, String education, String gameActivity, String memoryActivity, String configuration) {
        this.age = age;
        this.education = education;
        this.gameActivity = gameActivity;
        this.memoryActivity = memoryActivity;
        this.configuration = configuration;

        id = UUID.randomUUID().toString();

        bestScore = 0;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public String toCSV() {
        @SuppressWarnings("DefaultLocale") String format = String.format("%s,%s,%s,%s,%s,%d,%s", id, age, education, gameActivity, memoryActivity, bestScore, configuration);
        return format;
    }

}
