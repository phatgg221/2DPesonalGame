import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame windown= new JFrame();
        windown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windown.setResizable(false);
        windown.setTitle("2D adventure");

        GamePanel gamePanel= new GamePanel();

        windown.add(gamePanel);

        windown.pack();

        windown.setLocationRelativeTo(null);
        windown.setVisible(true);
        gamePanel.startGameThread();
    }
}