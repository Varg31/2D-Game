package FirstPackage;

import java.awt.*;

public class Player {
    private double x, y;
    private int radius;

    private double dx;
    private double dy;

    private int speed;



    private Color color1;

    public static double healht = 3;
    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    public static boolean isFiring;

    public Player () {
        x = GamePanel.WIDTH/2;
        y = GamePanel.HEIGHT/2;

        radius = 5;

        speed = 5;

        dx = 0;
        dy = 0;

        color1 = Color.WHITE;

        up =false;
        down = false;
        left = false;
        right = false;

        isFiring = false;
    }

    public boolean remove () {
        if (healht <=0) {
            return true;
        }
        return false;
    }

        public double getX() { return x; }

        public double getY() { return y; }

        public int getRadius() {return radius;}

    public void hit () {
        healht--;
        System.out.println(healht);
    }

    public void update() {
        if (up && y > radius)
            dy = -speed;
        if (down && y < GamePanel.HEIGHT - radius)
            dy = speed;
        if (left && x > radius)
            dx = -speed;
        if (right && x < GamePanel.WIDTH - radius)
            dx = speed;

        if ((up && right) || (up && left) || (down && left) || (down && right)) {
            double angle = Math.toRadians(45);
          dy = dy * Math.sin(angle);
          dx = dx * Math.cos(angle);
        }

        y += dy;
        x += dx;

        dy = 0;
        dx = 0;

        if (isFiring) {
            GamePanel.bullets.add(new Bullets());
        }
    }

    public void draw (Graphics2D graphics2D) {
        graphics2D.setColor(color1);
        graphics2D.fillOval((int)(x - radius), (int) (y - radius), 2*radius, 2*radius);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(color1.darker());
        graphics2D.drawOval((int) (x - radius), (int) (y - radius), 2*radius, 2*radius);
        graphics2D.setStroke(new BasicStroke(1));
    }
}