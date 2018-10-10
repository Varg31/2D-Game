package FirstPackage;

import java.awt.*;

public class Enemy {
    private double x, y;
    private int radius;

    private double speed;

    private double dx;
    private double dy;

    private double health;

    private Color color;

    private int type;
    private int rank;

    public double getX() { return x; }

    public double getY() { return y; }

    public int getRadius() {return radius;}

    public Enemy (int type, int rank) {
        this.type = type;
        this.rank = rank;

        switch (type)  {
            case(1): color = Color.GREEN;
                switch (rank) {
                case(1): x = Math.random() * GamePanel.WIDTH;
                y = 0;

                radius = 6;

                speed = 30;

                health = 4;

                double angle = Math.toRadians(Math.random() * 360);

                dx = Math.sin(angle) * speed;
                dy = Math.cos(angle) * speed;
            }

        }
        switch (type) {
            case (2):
                color = Color.RED;
                switch (rank) {
                    case (2):
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;

                        radius = 7;

                        speed = 45;

                        health = 8;

                        double angle = Math.toRadians(Math.random() * 360);

                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                }
        }
    }

    public boolean remove () {
        if (health <=0) {
            return true;
        }
        return false;
    }

    public void hit () {
        health--;
    }

    public void update () {
        x += dx;
        y += dy;

        if (x < 0 && dx < 0) dx = -dx;
        if (x > GamePanel.WIDTH && dx > 0) dx = -dx;

        if (y < 0 && dy < 0) dy = - dy;
        if (y > GamePanel.HEIGHT && dy > 0) dy = -dy;
    }

    public void draw (Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fillOval((int)(x - radius), (int)(y - radius), 2 * radius, 2 * radius);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(color.darker());

        graphics2D.drawOval((int)(x - radius),(int)(y - radius), 2 * radius, 2 * radius);
        graphics2D.setStroke(new BasicStroke(2));
    }
}