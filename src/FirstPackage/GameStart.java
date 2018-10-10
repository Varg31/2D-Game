package FirstPackage;

import javax.swing.*;

/**
 * Created by Varg on 30.10.2017.
 */
public class GameStart {
    public static void main(String[] args) {
        GamePanel panel = new GamePanel();

        JFrame window = new JFrame("BubbleShooter");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setContentPane(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        panel.start();
    }
}
