package FirstPackage;

import java.awt.*;

public class Health {
        private double x, y;
        private int radius;

        private double health;

        private Color color;

        private int type;

        public double getX() { return x; }

        public double getY() { return y; }

        public int getRadius() { return radius; }

        public Health (int type) {
            this.type =type;

            switch (type) {
                case (1): color = Color.orange;
                x = (int)Math.random() * GamePanel.WIDTH;
                y = (int)Math.random() * GamePanel.HEIGHT;

                radius = 3;

                health = 2;
            }
        }

        public void draw(Graphics2D graphics2D) {
            graphics2D.setColor(color);

            graphics2D.fillOval((int)(x - radius), (int)(y - radius), 2 * radius, 2 * radius);
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.setColor(color.darker());

            graphics2D.drawOval((int)(x - radius),(int)(y - radius), 2 * radius, 2 * radius);
            graphics2D.setStroke(new BasicStroke(2));
        }
}
