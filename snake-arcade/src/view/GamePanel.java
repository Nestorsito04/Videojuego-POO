package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.ScoreEntry;
import model.ScoreManager;
import model.SnakeModel;

public class GamePanel extends JPanel {
    private final SnakeModel model;

    public GamePanel(SnakeModel model) {
        this.model = model;
        setPreferredSize(new Dimension(SnakeModel.GRID_SIZE * SnakeModel.TILE_SIZE, 
                                       SnakeModel.GRID_SIZE * SnakeModel.TILE_SIZE + 40));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar Comida según su tipo (Normal: Roja, Speed: Dorada, Veneno: Morada)
        switch (model.getCurrentFoodType()) {
            case NORMAL -> g.setColor(Color.RED);
            case SPEED_BOOST -> g.setColor(Color.ORANGE);
            case POISON -> g.setColor(new Color(138, 43, 226)); // Morado
        }
        
        g.fillOval(model.getFood().x * SnakeModel.TILE_SIZE, 
                   model.getFood().y * SnakeModel.TILE_SIZE, 
                   SnakeModel.TILE_SIZE, SnakeModel.TILE_SIZE);

        // Dibujar Culebra
        for (int i = 0; i < model.getSnake().size(); i++) {
            g.setColor(i == 0 ? Color.GREEN : new Color(45, 180, 0));
            Point p = model.getSnake().get(i);
            g.fillRect(p.x * SnakeModel.TILE_SIZE, p.y * SnakeModel.TILE_SIZE, 
                       SnakeModel.TILE_SIZE - 1, SnakeModel.TILE_SIZE - 1);
        }

        // Dibujar Puntaje y Estado de Efectos
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        String statusText = "Score: " + model.getScore();
        
        if (model.getSpeedModifier() > 0) statusText += " | SPEED BOOST (x2 Puntos)";
        else if (model.getSpeedModifier() < 0) statusText += " | ENVENENADO (Lento)";

        g.drawString(statusText, 10, SnakeModel.GRID_SIZE * SnakeModel.TILE_SIZE + 25);

        // Game Over & Tabla de Puntuaciones
        if (model.isGameOver()) {
            g.setColor(new Color(0, 0, 0, 200));
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER", 160, 100);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Top High Scores:", 180, 150);

            List<ScoreEntry> topScores = ScoreManager.loadScores();
            int y = 180;
            for (int i = 0; i < topScores.size(); i++) {
                ScoreEntry e = topScores.get(i);
                g.drawString((i + 1) + ". " + e.getPlayer() + " - " + e.getScore(), 180, y);
                y += 25;
            }

            g.setColor(Color.YELLOW);
            g.drawString("Presiona R para Reiniciar", 150, y + 30);
        }
    }
}