package FirstPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Varg on 30.10.2017.
 */
public class GamePanel extends JPanel implements Runnable {
    //Field
    static int WIDTH = 1000;
    static int HEIGHT = 600;

    public static int mouseX;
    public static int mouseY;

    private Thread thread;

    private BufferedImage image;
    private Graphics2D graphics2D;

    private int FPS;
    private long timerFPS;
    private double millisecToFPS;
    private int sleepTime;

    private JLabel label;

    static enum STATES  {MENU, PLAY}
    static STATES states = STATES.MENU;

    private static GameBack background;

    static Player player;

    static ArrayList<Bullets> bullets;
    static ArrayList<Enemy> enemies;
    static ArrayList<Health> healthCircles;
    static Wave wave;

    static Menu menu;

    static boolean leftMouse;

    //Constructor
    GamePanel () {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true); // виділення вікна (активний)
        requestFocus();

        addKeyListener(new Listeners());
        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());
    }

     void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        FPS = 30;
        millisecToFPS = 1000 / FPS;
        sleepTime = 0;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // image color
        graphics2D = (Graphics2D) image.getGraphics(); 
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        leftMouse = false;
        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullets>();
        enemies = new ArrayList<Enemy>();
        healthCircles = new ArrayList<Health>();
        wave = new Wave();
        menu = new Menu();
        label = new JLabel("Game Over");
        label.setSize(WIDTH / 2, HEIGHT / 2);

        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage bufferedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) bufferedImage.getGraphics();
        g3.setColor(Color.WHITE);
        g3.drawOval(0, 0, 4,4);
        g3.drawLine(2, 0, 2, 4);
        g3.drawLine(0, 2, 4, 2);
        Cursor cursor = kit.createCustomCursor(bufferedImage, new Point(10, 10), "newCursor");
        g3.dispose();

        while (true) {
            this.setCursor(cursor);

            timerFPS = System.nanoTime();

            if (states.equals(STATES.MENU)) {
                background.update();
                background.draw(graphics2D);
                
                menu.update();
                menu.draw(graphics2D);
                
                gameDraw();
           }
            if (states.equals(STATES.PLAY)) {
                gameUpdate();
                gameRender();
                gameDraw();
            }
            

            timerFPS = (System.nanoTime() - timerFPS) / 1000000;
            if (millisecToFPS > timerFPS) {
                sleepTime = (int)millisecToFPS - (int)timerFPS;
            }
            else sleepTime = 1;

            try {
                Thread.sleep(sleepTime);
                //System.out.println(sleepTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timerFPS = 0;
            sleepTime = 1;
        }
    }

    private void gameUpdate() {
        // Background update
        background.update();

        //Player update
        player.update();

        //Bullets update
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            boolean remove = bullets.get(i).remove();
            if (remove) {
                bullets.remove(i);
                i--;
            }
        }

        //Enemies update
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }

        //Bullets - enemies collide
        for (int i =0; i < enemies.size(); i++) {
                Enemy e = enemies.get(i);
                double ex = e.getX();
                double ey = e.getY();

            for (int j = 0; j < bullets.size(); j ++) {
                Bullets b = bullets.get(j);
                double bx = b.getX();
                double by = b.getY();

                double dx = ex - bx;
                double dy = ey - by;

                double dist = Math.sqrt(dx*dx + dy*dy);

                //
                if ((int)dist <= e.getRadius() + e.getRadius()) { //видалення кулі
                    e.hit();
                    bullets.remove(j);
                    j--;

                    boolean remove = e.remove();// перевірка хіли ворога
                    if (remove) {
                        enemies.remove(i);
                        i--;
                    }
                    break;
                }
            }

        }

        //Player enemy collides
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);

            double ex = e.getX();
            double ey = e.getY();

            double px = player.getX();
            double py = player.getY();

            double dx = ex - px;
            double dy = ey - py;

            double dist = Math.sqrt(dx*dx + dy*dy);

            if ((int)dist <= e.getRadius() + player.getRadius()) {
                e.hit();
                player.hit();

                boolean remove = e.remove();
                if (remove) {
                    enemies.remove(i);
                    i--;
                }
                break;
            }

            if (Player.healht <= 0) {
                /*try {
                    Runnable r = (() -> {    JOptionPane.showMessageDialog(null, "Quit program?");
                                             System.exit(0);
                                             } );
                    Thread t = new Thread(r);
                    t.start();
                    thread.join();


                } catch (Exception e1) {
                    e1.printStackTrace();
                }*/
            }
        }
        //Wave update
        wave.update();
    }

    private void gameRender () {
        // Background draw
        background.draw(graphics2D);

        player.draw(graphics2D);

        for (Bullets bullet : bullets) {
            bullet.draw(graphics2D);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(graphics2D);
        }

        for (Health health : healthCircles) {
            health.draw(graphics2D);
        }

        if (wave.showWave()) {
            wave.draw(graphics2D);
        }

    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        g2.setPaintMode();
        g2.drawString("Game Over", WIDTH / 2, HEIGHT / 2);
    }
}