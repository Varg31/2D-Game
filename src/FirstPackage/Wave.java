package FirstPackage;

import java.awt.*;

public class Wave {
    //Fields
    private String waveText;
    private int waveNumber;

    private long waveTimer;
    private long waveDelay;
    private long waveTimerDiff;

    private int waveMultiplier; //enemies in the next waves

    private int waveHealth;

    //Constructor
    public Wave () {
        waveNumber = 1;
        waveMultiplier = 20;
        waveHealth = 6;

        waveTimer = 0;
        waveDelay = 5000;
        waveTimerDiff = 0;

        waveText = " Х В И Л Я  ";
    }
    //Functions
    private void createEnemies() {
        int enemyCount = waveNumber * waveMultiplier;
        if (waveNumber < 4) {
            while (enemyCount > 0) {
                int rank = 1;
                int type = 1;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * 3 *rank;
            }
        }
        if (waveNumber >= 4 && waveNumber < 10) {
            while (enemyCount > 0) {
                int rank = 2;
                int type = 2;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * 3 * rank;
            }
        }
        waveNumber++;
    }

    private void createHealth() {
        int healthCount = waveNumber * waveHealth;

        if (waveNumber < 4) {
            while (healthCount > 0) {
                int type = 1;
                GamePanel.healthCircles.add(new Health(type));
                healthCount -= type * 2;
            }
        }
        waveNumber++;
    }

    public void update() {
        if (GamePanel.enemies.size() == 0 && waveTimer == 0) {
            waveTimer = System.nanoTime();
        }
        if (waveTimer > 0) {
            waveTimerDiff += (System.nanoTime() - waveTimer) / 1000000;
            waveTimer = System.nanoTime();
        }
        if (waveTimerDiff > waveDelay) {
            createEnemies();
            waveTimer = 0;
            waveTimerDiff = 0;
        }
    }

    public boolean showWave() {
        return waveTimer != 0;
    }

    public void draw(Graphics2D graphics2D) {
        double divider = waveDelay / 180;
        double alpha = waveTimerDiff / divider;

        alpha = 255 * Math.sin(Math.toRadians(alpha));
        if (alpha < 0) alpha = 0;
        if (alpha > 255) alpha = 255;

        graphics2D.setFont(new Font("consolas", Font.PLAIN, 20));
        graphics2D.setColor(new Color (255,255,0, (int)alpha));
        String S = " - " + waveText + waveNumber + " -";
        long length = (int)graphics2D.getFontMetrics().getStringBounds(S, graphics2D).getWidth();
        graphics2D.drawString(S, GamePanel.WIDTH/2 - (int)(length/2), GamePanel.HEIGHT/2);
    }
}