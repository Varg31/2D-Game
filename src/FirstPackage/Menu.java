package FirstPackage;
import java.awt.*;

public class Menu {
    private int buttonWidth;
    private int buttonHeight;

    private Color color;

    private String buttonText;

    private int transparence;

    public Menu() {
        buttonWidth = 120;
        buttonHeight = 60;

        color = Color.RED;

        buttonText = "PLAY";

        transparence = 0;
    }

    public void update() {
        if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2 &&
                GamePanel.mouseX < GamePanel.WIDTH / 2  + buttonWidth / 2 &&
                GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2  &&
                GamePanel.mouseY < GamePanel.HEIGHT / 2 + buttonHeight / 2) {
            transparence = 60;
            if (GamePanel.leftMouse) {
                GamePanel.states = GamePanel.STATES.PLAY;
            }
        }
        else {
            transparence = 0;
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.drawRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT/2 - buttonHeight / 2,
                buttonWidth, buttonHeight);
        graphics2D.setColor(new Color(255, 255, 255, transparence));
        graphics2D.fillRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2,
                buttonWidth, buttonHeight);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(Color.orange);
        graphics2D.setFont(new Font("Times New Roman", Font.BOLD, 40));
        long length = (int) graphics2D.getFontMetrics().getStringBounds(buttonText, graphics2D).getWidth();
        graphics2D.drawString(buttonText, (int)(GamePanel.WIDTH / 2 - length / 2), (int)(GamePanel.HEIGHT / 2 + buttonHeight / 4));
    }

}
