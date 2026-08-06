package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
    private static final String FILE_NAME = "scores.dat";

    @SuppressWarnings("unchecked")
    public static List<ScoreEntry> loadScores() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<ScoreEntry>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void saveScore(String player, int score) {
        List<ScoreEntry> scores = loadScores();
        scores.add(new ScoreEntry(player, score));
        scores.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

        if (scores.size() > 5) {
            scores = scores.subList(0, 5);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}