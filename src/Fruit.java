package Snake;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by dpMelian on 07/12/2016.
 */
public class Fruit {
    private int scale = 10;
    int tamX = 10;
    int tamY = 10;

    int cols = ThreadLocalRandom.current().nextInt(1, ((Init.prefSize/10)-2) + 1) * scale;
    int rows = ThreadLocalRandom.current().nextInt(1, ((Init.prefSize/10)-2) + 1) * scale;

    public Fruit(){
    }
    public void paint(Graphics2D g){
        g.setColor(Color.RED);
        g.fillRect(cols, rows, tamX, tamY);
    }
    public Rectangle getBounds() {
        return new Rectangle(cols, rows, tamX, tamY);
    }
}