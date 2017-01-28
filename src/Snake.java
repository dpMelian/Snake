package Snake;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import java.lang.reflect.Field;

/**
 * Created by dpMelian on 07/12/2016.
 */

public class Snake extends JPanel{
    private int scale = 10;
    int x;
    int y;
    private int xSpeed;
    private int ySpeed;
    private int tamX = 10;
    private int tamY = 10;
    private Init Main2;
    int random = 0;
    static boolean boolD = false;
    int sc = 0;

    private LinkedList<Point> snakeParts;

    public Snake(Init Main){
        this.Main2 = Main;

        this.x = 20;
        this.y = 20;

        this.xSpeed = 1;
        this.ySpeed = 0;

        snakeParts = new LinkedList<>();
        snakeParts.add(new Point(this.x, this.y));
    }
    public void paint(Graphics2D g) {
        for(Point p : snakeParts){
            if(p == snakeParts.peekFirst()){
                g.setColor(Color.BLACK);
            } else {
                try{
                    Color color;
                    Field field = Class.forName("java.awt.Color").getField(Init.color.toLowerCase());
                    color = (Color)field.get(null);
                    g.setColor(color);
                } catch(Exception e){

                }
            }
            g.fillRect(p.x, p.y, tamX, tamY);
        }

        System.out.println(this.x + ", " + this.y);
    }
    public void move() throws InterruptedException{
        if(this.x < (Init.prefSize-10) && this.x > 0 && this.y < (Init.prefSize-10) && this.y > 0) {
            this.x = this.x + this.xSpeed * scale;
            this.y = this.y + this.ySpeed * scale;
            Point p = new Point(this.x, this.y);
            snakeParts.push(p);
            snakeParts.remove(snakeParts.peekLast());
        } else{
            if(Score.vidas > 0){
                launchGameOver();
            } else{
                JOptionPane.showMessageDialog(this, "Game Over",
                        "Game Over", JOptionPane.YES_NO_OPTION);
                System.exit(ABORT);
            }
        }
        if(snakeParts.size() > 0){
            for(int i = 1; i < snakeParts.size(); i++){
                if(this.x == snakeParts.get(i).x && this.y == snakeParts.get(i).y){
                    launchGameOver();
                }
            }
        }
        if(collision()){
            eatSound();
            random = (int)(Math.random() * 40) + 1;

            if(random == 20){
                boolD = true;

                Main2.d.cols = ThreadLocalRandom.current().nextInt(1,
                        ((Init.prefSize/10)-2) + 1) * scale;
                Main2.d.rows = ThreadLocalRandom.current().nextInt(1,
                        ((Init.prefSize/10)-2) + 1) * scale;
                for(Point p : snakeParts){
                    if(p.x == Main2.d.cols || p.y == Main2.d.rows){
                        Main2.d.cols = ThreadLocalRandom.current().nextInt(1,
                                ((Init.prefSize/10)-2) + 1) * scale;
                        Main2.d.rows = ThreadLocalRandom.current().nextInt(1,
                                ((Init.prefSize/10)-2) + 1) * scale;
                    }
                }
            }
            switch(Init.difficulty){
                case 60:
                    sc += 20;
                    break;
                case 120:
                    sc += 10;
                    break;
                case 140:
                    sc += 5;
                    break;
            }
            Score.puntuacion.setText(String.valueOf(sc));
            System.out.println("Eating!!");

            Main2.f.cols = ThreadLocalRandom.current().nextInt(1, ((Init.prefSize/10)-2) + 1) * scale;
            Main2.f.rows = ThreadLocalRandom.current().nextInt(1, ((Init.prefSize/10)-2) + 1) * scale;

            for(Point p : snakeParts){
                if(p.x == Main2.f.cols || p.y == Main2.f.rows){
                    Main2.f.cols = ThreadLocalRandom.current().nextInt(1,
                            ((Init.prefSize/10)-2) + 1) * scale;
                    Main2.f.rows = ThreadLocalRandom.current().nextInt(1,
                            ((Init.prefSize/10)-2) + 1) * scale;
                }
            }
            snakeParts.push(new Point(this.x, this.y));
        }
        if(collisionDeluxe()){
            sc += 100;
            boolD = false;
            Score.puntuacion.setText(String.valueOf(sc));
            eatSound();
            bonusSound();
            Main2.d.cols = -100;
            Main2.d.rows = -100;
            snakeParts.push(new Point(this.x, this.y));
        }
    }
    public void launchGameOver(){
        loseSound();
        int check = JOptionPane.showConfirmDialog(this, "¿Seguir jugando?",
                "Game Over", JOptionPane.YES_NO_OPTION);
        if(check == 1){
            System.exit(ABORT);
        } else if(check == 0){
            reset();
        }
    }
    public void reset(){
        Main2.f.cols = ThreadLocalRandom.current().nextInt(1, ((Init.prefSize/10)-2) + 1) * scale;
        Main2.f.rows = ThreadLocalRandom.current().nextInt(1, ((Init.prefSize/10)-2) + 1) * scale;

        snakeParts.clear();
        this.x = 20;
        this.y = 20;

        this.xSpeed = 1;
        this.ySpeed = 0;

        switch(Score.vidas){
            case 3:
                Score.img1.setIcon(null);
                Score.vidas--;
                break;
            case 2:
                Score.img2.setIcon(null);
                Score.vidas--;
                break;
            case 1:
                Score.img3.setIcon(null);
                Score.vidas--;
                break;
        }
        snakeParts.add(new Point(this.x, this.y));
    }
    private boolean collision() {
        return Main2.f.getBounds().intersects(getBounds());
    }
    private boolean collisionDeluxe(){
        return Main2.d.getBounds().intersects(getBounds());
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_UP:
                this.xSpeed=0;
                this.ySpeed=-1;
                System.out.println("UP");
                break;
            case KeyEvent.VK_DOWN:
                this.xSpeed=0;
                this.ySpeed=1;
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                this.xSpeed=-1;
                this.ySpeed=0;
                System.out.println("LEFT");
                break;
            case KeyEvent.VK_RIGHT :
                this.xSpeed=1;
                this.ySpeed=0;
                System.out.println("RIGHT");
                break;
        }
    }
    public void eatSound(){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Snake/eat.wav"));

            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public void loseSound(){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Snake/lose.wav"));

            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public void bonusSound(){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Snake/bonus.wav"));

            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}