package Snake;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

/**
 * Created by dpMelian on 05/01/2017.
 */
public class DeluxeFruit extends JPanel {
    private int scale = 10;
    int tamX = 10;
    int tamY = 10;

    int cols = ThreadLocalRandom.current().nextInt(1, ((Init.prefSize/10)-2) + 1) * scale;
    int rows = ThreadLocalRandom.current().nextInt(1, ((Init.prefSize/10)-2) + 1) * scale;

    public DeluxeFruit(){

    }
    public void paint(Graphics2D g){
        g.setColor(Color.MAGENTA);
        g.fillRect(cols, rows, tamX, tamY);
    }
    public Rectangle getBounds() {
        return new Rectangle(cols, rows, tamX, tamY);
    }
}