package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import model.ScoreManager;
import model.SnakeModel;
import view.GamePanel;

public class GameController extends KeyAdapter {
    private final SnakeModel model;
    private final GamePanel view;
    private final Timer timer;
    private boolean scoreSaved = false;

    public GameController(SnakeModel model, GamePanel view) {
        this.model = model;
        this.view = view;

        this.timer = new Timer(120, e -> update());
        this.timer.start();
    }

    private void update() {
        if (!model.isGameOver()) {
            // Ajustar velocidad del Timer según los Power-ups dinámicamente
            if (model.getSpeedModifier() > 0) {
                timer.setDelay(60);  // Más rápido
            } else if (model.getSpeedModifier() < 0) {
                timer.setDelay(180); // Más lento
            } else {
                timer.setDelay(120); // Normal
            }

            model.move();
            scoreSaved = false;
        } else if (!scoreSaved) {
            String name = JOptionPane.showInputDialog(view, 
                    "¡Juego Terminado! Tu puntaje fue: " + model.getScore() + "\nIngresa tu nombre:", 
                    "Nuevo Record", JOptionPane.PLAIN_MESSAGE);
            
            if (name != null && !name.trim().isEmpty()) {
                ScoreManager.saveScore(name.trim(), model.getScore());
            } else {
                ScoreManager.saveScore("Jugador", model.getScore());
            }
            scoreSaved = true;
        }
        view.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) model.setDirection('U');
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) model.setDirection('D');
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) model.setDirection('L');
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) model.setDirection('R');

        if (key == KeyEvent.VK_R && model.isGameOver()) {
            model.reset();
        }
    }
}