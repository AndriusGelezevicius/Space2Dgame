package Software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainGui implements KeyListener {
    JFrame jFrame;
    JLabel jLabel;

    public void drawingFrame(){
        jFrame = new JFrame();
        jFrame.setSize(500,500);
        jFrame.setLayout(null);
        jFrame.addKeyListener(this);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        jLabel = new JLabel();
        jLabel.setBounds(0,0,100,100);
        jLabel.setBackground(Color.BLUE);
        jLabel.setOpaque(true); //because of BG, thaat will be display

        jFrame.add(jLabel);
        jFrame.setVisible(true);

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
