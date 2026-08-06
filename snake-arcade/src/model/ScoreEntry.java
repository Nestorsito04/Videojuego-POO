package model;

import java.io.Serializable;

public class ScoreEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String player;
    private final int score;

    public ScoreEntry(String player, int score) {
        this.player = player;
        this.score = score;
    }

    public String getPlayer() { return player; }
    public int getScore() { return score; }
}