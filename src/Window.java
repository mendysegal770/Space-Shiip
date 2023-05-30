import javax.swing.*;
import java.awt.*;


public class Window extends JFrame {

    public static final int WINDOW_HEIGHT = 800;
    public static final int WINDOW_WIDTH = 1300;




    public void shoWindow() {
        this.setVisible(true);
    }

    public Window() {
        this.setResizable(true);

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
       this.setLayout(null);
        GameScene gameSCen = new GameScene();
        gameSCen.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(gameSCen);
        }
    }




