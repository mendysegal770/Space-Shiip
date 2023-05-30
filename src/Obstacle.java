import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Obstacle extends Thread {
    private int x;
    private int y;
    public static final int SIZE = 30;
    public static boolean boolSpeed = false;
    public int width;
    public int height;
    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean getBoolSpeed() {
        return this.boolSpeed;
    }
    public void run() {
        int speed = 0;
        int speed1 = 50;
        int speed2 = 100;
        int s1 = 10;
        int s2 = 5;
        Random random = new Random();
            speed = random.nextInt(speed1, speed2);
            boolean goingUp = random.nextBoolean();
            int r = random.nextInt(1, 3);
            while (true) {
                if (!goingUp && r == 1) {
                    this.x++;
                    this.y++;
                } else if (goingUp && r == 1) {
                    this.x--;
                    this.y--;
                }
                if (!goingUp && r == 2) {
                    this.y++;
                } else if (goingUp && r == 2) {
                    this.y--;
                }

                if (this.x + SIZE + SIZE > Window.WINDOW_WIDTH || this.y + SIZE + SIZE > Window.WINDOW_HEIGHT) {
                    goingUp = true;
                }
                if (this.x < 0 || this.y < 0) {
                    goingUp = false;
                }
                if (boolSpeed && speed >= 20) {
                    speed = speed - 15;
                    boolSpeed = false;
                } else if (boolSpeed && speed < 20 && speed > 10) {
                    speed = speed - 10;
                    boolSpeed = false;
                }
                if (GameScene.hearts.size()==0 || GameScene.wine == 20) {
                    speed = random.nextInt(speed1, speed2);
                }
                Utils.sleep(speed);
            }

        }
    public void paint(Graphics graphics) {
        ImageIcon imageIcon=new ImageIcon("Image\\abstacle.png");
        imageIcon.paintIcon(null, graphics, this.x, this.y);
        width=   imageIcon.getIconWidth();
        height=imageIcon.getIconHeight();
    }

    public Rectangle calculateRectangle() {
        return new Rectangle(this.x, this.y, width,height);
    }

}

