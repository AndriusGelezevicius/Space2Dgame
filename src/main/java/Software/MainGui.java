package Software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Objects;

public class MainGui implements KeyListener {
    JFrame jFrame;
    JLabel rocketJLabel;
    JLabel asteroidJLabel;
    ImageIcon rocketIcon;
    ImageIcon asteroidIcon;
    ImageIcon backgroundImage;

    public void drawingFrame(){
        jFrame = new JFrame();
        jFrame.setSize(500,500);
        jFrame.setLayout(null);
        jFrame.addKeyListener(this);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        rocketIcon = new ImageIcon("src/rocket (1).png");
        asteroidIcon = new ImageIcon("src/asteroid.png");
        backgroundImage = new ImageIcon("src/background.png");

        rocketJLabel = new JLabel();
        rocketJLabel.setBounds(210,220,400,400);
        asteroidJLabel = new JLabel();
        asteroidJLabel.setBounds(0,0, 300,300);
        rocketJLabel.setIcon(rocketIcon);
        asteroidJLabel.setIcon(asteroidIcon);

        jFrame.add(asteroidJLabel);
        jFrame.add(rocketJLabel);
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
                rocketJLabel.setLocation(rocketJLabel.getX(), rocketJLabel.getY()-10);
                break;
            case 40:
                rocketJLabel.setLocation(rocketJLabel.getX(), rocketJLabel.getY()+10);
                break;
            case 37:
                rocketJLabel.setLocation(rocketJLabel.getX()-10, rocketJLabel.getY());
                break;
            case 39:
                rocketJLabel.setLocation(rocketJLabel.getX()+10, rocketJLabel.getY());
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released: "+e.getKeyCode());

    }
}
