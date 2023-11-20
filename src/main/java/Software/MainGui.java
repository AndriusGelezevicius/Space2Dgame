package Software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Objects;

public class MainGui implements KeyListener {
    JFrame jFrame;
    JLabel jLabel;
    ImageIcon icon;

    public void drawingFrame(){
        jFrame = new JFrame();
        jFrame.setSize(500,500);
        jFrame.setLayout(null);
        jFrame.addKeyListener(this);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        icon = new ImageIcon("src/rocket (1).png");
//        icon = new ImageIcon("C:\\Users\\YourUser\\Path\\To\\Your\\Project\\src\\rocket(1).png");

        jLabel = new JLabel();
        jLabel.setBounds(0,0,400,400);
        jLabel.setIcon(icon);

        jFrame.add(jLabel);
        jFrame.setVisible(true);
//        System.out.println(new File("src/rocket(1).png").getAbsolutePath());

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 38:
                jLabel.setLocation(jLabel.getX(), jLabel.getY()-10);
                break;
            case 40:
                jLabel.setLocation(jLabel.getX(), jLabel.getY()+10);
                break;
            case 37:
                jLabel.setLocation(jLabel.getX()-10, jLabel.getY());
                break;
            case 39:
                jLabel.setLocation(jLabel.getX()+10, jLabel.getY());
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released: "+e.getKeyCode());

    }
}
