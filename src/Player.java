import javax.swing.*;
import java.awt.*;

public class Player {
    private int x;
    private int y;
 //   public static final int SIZE = 50;
    private boolean alive;
    private static final int life1 = 1;
    private static final int life2 = 2;
    public  int life3 = 3;
    public int life = life3;
    public int width;
    public int height;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.alive = true;
    }

    public void ChangeLife() {
        if (life == life3 ) {
            life = life2;

        } else if (life == life2 ) {
            life = life1;

        } else if (life == life1 ) {
            life = 0;
        }
    }

    public void paint(Graphics graphics) {
        if (this.alive) {
            ImageIcon imageIcon=new ImageIcon("C:\\Users\\מנדי\\Desktop\\gcusv\\Space11.png");
            imageIcon.paintIcon(null, graphics, this.x, this.y);
         width=   imageIcon.getIconWidth();
            height=imageIcon.getIconHeight();
           /*
*/
        }
    }

    public void kill() {
        this.alive = false;

    }

    public void revive() {
        this.x = 0;
        this.y = 0;
        this.alive = true;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Rectangle calculateRectangle() {
        return new Rectangle(this.x, this.y, width, height);
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void overStepsTheBounds() {
        if (x > Window.WINDOW_WIDTH) {
            kill();
            revive();
            Obstacle.boolSpeed = true;
            GameScene.counter = 3000;
            if (GameScene.wine == GameScene.wine1) {
                GameScene.wine = GameScene.wine2;
            } else if (GameScene.wine == GameScene.wine2) {
                GameScene.wine = GameScene.wine3;
            } else if (GameScene.wine == GameScene.wine3) {
                GameScene.wine = GameScene.wine4;
            } else if (GameScene.wine == GameScene.wine4) {
                GameScene.wine = 20;
            }
        }
    }


    }


