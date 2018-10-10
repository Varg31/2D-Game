package FirstPackage;

import java.awt.*;

/**
 * Created by Varg on 30.10.2017.
 */
public class GameBack {
    //Fields
    private Color color;

    public GameBack() {
        color = Color.BLACK;
    }
    public void update() {

    }

    public void draw (Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fillRect(0,0,GamePanel.WIDTH, GamePanel.HEIGHT);
    }
}
