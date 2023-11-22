package Software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Objects;
import java.util.Random;

public class MainGui implements KeyListener {
    JFrame jFrame;
    JLabel rocketJLabel;
    JLabel asteroidJLabel;
    ImageIcon rocketIcon;
    ImageIcon asteroidIcon;
    ImageIcon backgroundImage;
    Timer timer;
    int jFrameWidth = 500;
    int jFrameHeight = 500;

    public void drawingFrame(){
        jFrame = new JFrame();
        jFrame.setSize(jFrameWidth,jFrameHeight);
        jFrame.setLayout(null);
        jFrame.addKeyListener(this);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        rocketIcon = new ImageIcon("src/rocket (1).png");
        asteroidIcon = new ImageIcon("src/asteroid.png");


        rocketJLabel = new JLabel();
        rocketJLabel.setBounds(210,220,400,400);
        asteroidJLabel = new JLabel();
        asteroidJLabel.setBounds(0,0, 300,300);
        rocketJLabel.setIcon(rocketIcon);
        asteroidJLabel.setIcon(asteroidIcon);

        jFrame.add(asteroidJLabel);
        jFrame.add(rocketJLabel);

        jFrame.setVisible(true);

        

    }


    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 38:
                moveRocket(0,-10);
                break;
            case 40:
                moveRocket(0,+10);
                break;
            case 37:
                moveRocket(-10,0);
                break;
            case 39:
                moveRocket(+10,0);
                break;
        }
    }

    private void moveRocket(int deltaX, int deltaY) {
        int newX = rocketJLabel.getX() + deltaX;
        int newY = rocketJLabel.getY() + deltaY;

        int maxX = jFrame.getWidth();
        int maxY = jFrame.getHeight();
        System.out.println(newY);

        // Ensure the new position is within the bounds
        if (newX > 430){
            newX = 430;
        }else if (newX < -10){
            newX = -10;
        }
        if (newY > 230){
            newY = 230;
        }else if (newY < -160){
            newY = -160;
        }
        rocketJLabel.setLocation(newX, newY);
    }


    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released: "+e.getKeyCode());
    }
}
