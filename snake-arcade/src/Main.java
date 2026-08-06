import controller.GameController;
import javax.swing.*;
import model.SnakeModel;
import view.GamePanel;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Arcade Classic");
            SnakeModel model = new SnakeModel();
            GamePanel view = new GamePanel(model);
            GameController controller = new GameController(model, view);

            frame.addKeyListener(controller);
            frame.add(view);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            
            // Asegurar que la ventana capture el foco del teclado al abrirse
            frame.setFocusable(true);
            frame.requestFocusInWindow();
            
            frame.setVisible(true);
        });
    }
}