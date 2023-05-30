import javax.swing.*;
import java.awt.*;

public class Heart {
    public int x;
    public int y;

    public Heart(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void paint(Graphics graphics){
        ImageIcon imageIcon=new ImageIcon("Image\\Heart.png");
        imageIcon.paintIcon(null, graphics, this.x, this.y);
    }



}
