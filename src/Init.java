package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by dpMelian on 07/12/2016.
 */

public class Init extends JPanel implements  Runnable {
    Snake s = new Snake(this);
    Fruit f = new Fruit();
    DeluxeFruit d = new DeluxeFruit();
    static JFrame w;
    static JFrame menu;
    private static JButton play, options, exit;

    public static String color = "GRAY";
    public static int difficulty = 120;
    public static int prefSize = 300;

    Thread t;
    boolean alreadyExecuted = false;

    public Init(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                s.keyPressed(e);
            }
        });
        setFocusable(true);

        if(!alreadyExecuted) {
            w = new JFrame("Snake");
            w.setSize(Init.prefSize, Init.prefSize);
            w.setUndecorated(true);
            System.out.println(w.getWidth());
            w.setResizable(false);
            w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            w.setLocationRelativeTo(null);
            w.add(this);
            w.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

            ImageIcon favicon = new ImageIcon(getClass().getResource("/Snake/favicon.png"));
            w.setIconImage(favicon.getImage());

            Score s = new Score();
            w.setVisible(true);

            t = new Thread(this, "Admin Thread");
            t.start();
            alreadyExecuted = true;
        }
    }
    public static void main(String[] args)  throws InterruptedException{
        menu = new JFrame("Menú");
        JPanel p = new JPanel(new BorderLayout(5,5));
        JPanel buttons = new JPanel(new GridLayout(3,0));

        play = new JButton("Jugar");
        options = new JButton("Opciones");
        exit = new JButton("Salir");
        play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)  {
                if(e.getSource() == play){
                    try{
                        menu.setVisible(false);
                        new Init();
                    } catch(Exception ex){

                    }
                }
            }
        });
        options.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)  {
                if(e.getSource() == options){
                    try{
                        new Options();
                    } catch(Exception ex){

                    }
                }
            }
        });
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)  {
                if(e.getSource() == exit){
                    try{
                        System.exit(ABORT);
                    } catch(Exception ex){

                    }
                }
            }
        });
        buttons.add(play);
        buttons.add(options);
        buttons.add(exit);

        p.add(buttons);
        menu.add(p, BorderLayout.CENTER);

        menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        menu.setSize(600, 600);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
    public Rectangle getBounds(){
        return w.getContentPane().getBounds();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        s.paint(g2d);
        f.paint(g2d);
        if(Snake.boolD){
            d.paint(g2d);
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(Score.vidas);
                s.move();
                this.repaint();
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
            try {
                Thread.currentThread();
                Thread.sleep(Init.difficulty);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
