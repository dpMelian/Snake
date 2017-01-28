package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dpMelian on 30/12/2016.
 */
public class Options extends JFrame implements ActionListener{
    private JPanel panel1;
    private JRadioButton fácilRadioButton;
    private JRadioButton normalRadioButton;
    private JRadioButton difícilRadioButton;
    private JRadioButton pequeñoRadioButton;
    private JRadioButton normalRadioButton1;
    private JRadioButton grandeRadioButton;
    private JButton aceptarButton;
    private JComboBox comboBox1;

    public Options(){
        super("Opciones");
        setContentPane(panel1);
        setLocationRelativeTo(null);
        pack();
        fácilRadioButton.addActionListener(this);
        normalRadioButton.addActionListener(this);
        difícilRadioButton.addActionListener(this);

        pequeñoRadioButton.addActionListener(this);
        normalRadioButton1.addActionListener(this);
        grandeRadioButton.addActionListener(this);

        aceptarButton.addActionListener(this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == fácilRadioButton){
            Init.difficulty = 140;
        } else if(e.getSource() == normalRadioButton){
            Init.difficulty = 120;
        } else if(e.getSource() == difícilRadioButton){
            Init.difficulty = 60;
        } else if(e.getSource() == pequeñoRadioButton){
            Init.prefSize = 200;
        } else if(e.getSource() == normalRadioButton1){
            Init.prefSize = 300;
        } else if(e.getSource() == grandeRadioButton){
            Init.prefSize = 600;
        } else if(e.getSource() == aceptarButton){
            if(comboBox1.getSelectedItem().toString().equals("Verde")){
                Init.color = "GREEN";
            } else if(comboBox1.getSelectedItem().toString().equals("Rojo")){
                Init.color = "RED";
            } else if(comboBox1.getSelectedItem().toString().equals("Rosa")){
                Init.color = "PINK";
            }
            dispose();
        }
    }
}