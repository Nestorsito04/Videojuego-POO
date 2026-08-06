package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeModel {
    public static final int GRID_SIZE = 20;
    public static final int TILE_SIZE = 25;

    // Tipos de Comida / Loot
    public enum FoodType { NORMAL, SPEED_BOOST, POISON }

    private final List<Point> snake = new ArrayList<>();
    private Point food;
    private FoodType currentFoodType = FoodType.NORMAL;
    
    private char direction = 'R';
    private boolean gameOver = false;
    private int score = 0;
    private int speedModifier = 0; // Negative = Slower, Positive = Faster

    public SnakeModel() {
        reset();
    }

    public void reset() {
        snake.clear();
        snake.add(new Point(5, 5));
        snake.add(new Point(4, 5));
        snake.add(new Point(3, 5));
        direction = 'R';
        score = 0;
        speedModifier = 0;
        gameOver = false;
        spawnFood();
    }

    public void spawnFood() {
        Random rand = new Random();
        food = new Point(rand.nextInt(GRID_SIZE), rand.nextInt(GRID_SIZE));

        // Probabilidades: 65% Normal, 20% Dorada (Speed/Bonus), 15% Morada (Veneno)
        int chance = rand.nextInt(100);
        if (chance < 65) {
            currentFoodType = FoodType.NORMAL;
        } else if (chance < 85) {
            currentFoodType = FoodType.SPEED_BOOST;
        } else {
            currentFoodType = FoodType.POISON;
        }
    }

    public void move() {
        if (gameOver) return;

        Point head = new Point(snake.get(0));
        switch (direction) {
            case 'U' -> head.y--;
            case 'D' -> head.y++;
            case 'L' -> head.x--;
            case 'R' -> head.x++;
        }

        if (head.x < 0 || head.x >= GRID_SIZE || head.y < 0 || head.y >= GRID_SIZE || snake.contains(head)) {
            gameOver = true;
            return;
        }

        snake.add(0, head);

        // Comer fruta / loot
        if (head.equals(food)) {
            applyFoodEffect();
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private void applyFoodEffect() {
        switch (currentFoodType) {
            case NORMAL -> {
                score += 10;
                speedModifier = 0;
            }
            case SPEED_BOOST -> {
                score += 30;
                speedModifier = 1; // Más rápido
            }
            case POISON -> {
                score = Math.max(0, score - 10);
                speedModifier = -1; // Más lento
            }
        }
    }

    public void setDirection(char newDir) {
        if ((newDir == 'L' && direction != 'R') ||
            (newDir == 'R' && direction != 'L') ||
            (newDir == 'U' && direction != 'D') ||
            (newDir == 'D' && direction != 'U')) {
            this.direction = newDir;
        }
    }

    public List<Point> getSnake() { return snake; }
    public Point getFood() { return food; }
    public FoodType getCurrentFoodType() { return currentFoodType; }
    public boolean isGameOver() { return gameOver; }
    public int getScore() { return score; }
    public int getSpeedModifier() { return speedModifier; }
}