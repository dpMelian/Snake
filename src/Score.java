package Snake;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dpMelian on 05/01/2017.
 */
public class Score extends JFrame {
    private JLabel texto;
    static JLabel puntuacion;
    static JLabel img1, img2, img3;
    static int vidas = 3;

    public Score(){
        JFrame score = new JFrame("");
        score.setSize(new Dimension(Init.prefSize, 50));
        score.setLocation(Init.w.getX(), Init.w.getY() + Init.w.getWidth());
        score.setUndecorated(true);
        score.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        texto = new JLabel("Puntuación: ");
        puntuacion = new JLabel("0");

        ImageIcon corazon = new ImageIcon(getClass().getResource("/Snake/heart.png"));
        ImageIcon corazon2 = new ImageIcon(getClass().getResource("/Snake/heart.png"));
        ImageIcon corazon3 = new ImageIcon(getClass().getResource("/Snake/heart.png"));

        img1 = new JLabel(corazon);
        img2 = new JLabel(corazon2);
        img3 = new JLabel(corazon3);

        JLabel text = new JLabel("Vidas: ");
        JPanel subPane = new JPanel();

        subPane.add(texto, BorderLayout.WEST);
        subPane.add(puntuacion, BorderLayout.CENTER);

        subPane.add(text);
        subPane.add(img1);
        subPane.add(img2);
        subPane.add(img3);

        score.add(subPane, BorderLayout.CENTER);
        score.setVisible(true);
    }
}