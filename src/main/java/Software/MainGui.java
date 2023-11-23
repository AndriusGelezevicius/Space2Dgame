package Software;

import javax.swing.*;
import java.awt.Rectangle;
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
    JLabel gameOverLabel;
    ImageIcon rocketIcon;
    ImageIcon asteroidIcon;
    ImageIcon backgroundImage;
    Timer timer;
    int jFrameWidth = 500;
    int jFrameHeight = 500;
    int asteroidX = 0;
    int asteroidY = 0;
    int asteroidXedge = 1;
    int asteroidYedge = 1;
    int newX, newY;

    public void drawingFrame() throws FontFormatException {

        jFrame = new JFrame();
        jFrame.setSize(jFrameWidth,jFrameHeight);
        jFrame.setLayout(null);
        jFrame.addKeyListener(this);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        Font font = new Font("Monaco", Font.BOLD | Font.ITALIC, 30);

        rocketIcon = new ImageIcon("src/rocket (1).png");
        asteroidIcon = new ImageIcon("src/asteroid.png");
        gameOverLabel = new JLabel("GAME OVER!");
        gameOverLabel.setBounds(150,200,200,100);
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setVisible(false);
        gameOverLabel.setFont(font);
        rocketJLabel = new JLabel();
        rocketJLabel.setBounds(210,400,60,65);
        asteroidJLabel = new JLabel();
        asteroidJLabel.setBounds(asteroidX,asteroidY, 70,70);
        rocketJLabel.setIcon(rocketIcon);
        asteroidJLabel.setIcon(asteroidIcon);
        jFrame.add(asteroidJLabel);
        jFrame.add(rocketJLabel);
        jFrame.add(gameOverLabel);

        jFrame.setVisible(true);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asteroidX = asteroidX + (10 * asteroidXedge); // Move the asteroid by 10 pixels

                // Check if the asteroid has reached the right edge or left edge
                if (asteroidX >= 417 || asteroidX <= 0) {
                    asteroidXedge = -asteroidXedge; // Reverse the direction of movement
                }



                asteroidJLabel.setLocation(asteroidX, asteroidY);
            }
        });
        timer.start();
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
        newX = rocketJLabel.getX() + deltaX;
        newY = rocketJLabel.getY() + deltaY;

        int maxX = jFrame.getWidth();
        int maxY = jFrame.getHeight();

        // Ensure the new position is within the bounds
        if (newX > 430){
            newX = 430;
        }else if (newX < -10){
            newX = -10;
        }
        if (newY > 400){
            newY = 400;
        }else if (newY < 0){
            newY = 0;
        }

        rocketJLabel.setLocation(newX, newY);
        System.out.println(newY);
        checkCollision();
    }
    public void checkCollision(){
        Rectangle rocketRect = new Rectangle(newX, newY, rocketJLabel.getWidth(), rocketJLabel.getHeight());
        Rectangle asteroidRect = new Rectangle(asteroidX, asteroidY, asteroidJLabel.getWidth(), asteroidJLabel.getHeight());

        if (rocketRect.intersects(asteroidRect)) {
            System.out.println("GAME OVER");
            gameOverLabel.setVisible(true);
        }

    }



    @Override
    public void keyReleased(KeyEvent e) {

    }
}
