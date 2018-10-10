package FirstPackage;

import java.awt.*;

public class Bullets {
    private double x, y;
    private double bulletDX, bulletDY;
    private double distX, distY;
    private double dist;
    private int radius;

    private double bulletspeed;

    private Color color;

    public double getX() {return x;}

    public double getY() {return y;}

    //public int getRadius() {return radius;}

    public Bullets () {
        x = GamePanel.player.getX();
        y = GamePanel.player.getY();
        radius = 2;

        bulletspeed = 10;

        distX = GamePanel.mouseX - x;
        distY = GamePanel.mouseY - y;
        dist = Math.sqrt(distX * distX + distY * distY);

        bulletDX = (distX / dist) * bulletspeed;
        bulletDY = (distY / dist) * bulletspeed;

        color = Color.RED;
    }

    public boolean remove() {
        if (y < 0 || y > GamePanel.HEIGHT || x < 0 || x > GamePanel.WIDTH) {
           return true;
        }
        return false;
    }

    public void update () {
        x += bulletDX;
        y += bulletDY;
    }

    public void draw (Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fillOval((int) x , (int) y, radius, 2*radius);
    }
}