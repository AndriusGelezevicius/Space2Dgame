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
    JLabel asteroidJLabel2;
    JLabel gameOverLabel;
    JLabel enemyRocketLabel;
    ImageIcon rocketIcon;
    ImageIcon asteroidIcon;
    ImageIcon asteroidIcon2;
    ImageIcon asteroidIcon3;
    ImageIcon enemyRocketIcon;
    ImageIcon backgroundImage;
    Timer timer;
    Timer enemyShowTimer;
    Timer enemyMoveTimer;
    int jFrameWidth = 500;
    int jFrameHeight = 500;
    int enemyX = 0;
    int enemyY = -100;
    int asteroidX = 0;
    int asteroidY = 0;
    int asteroidX2;
    int asteroidY2;
    int asteroidXedge = 1;
    int asteroidYedge = 1;
    int asteroidXedge2 = 1;
    int asteroidYedge2 = 1;
    int newX, newY;
    private boolean gameOver = false;
    private Sounds explosionSound;
    private Sounds backgroundSound;

    public void drawingFrame() throws FontFormatException {

        jFrame = new JFrame();
        jFrame.setSize(jFrameWidth,jFrameHeight);
        jFrame.setLayout(null);
        jFrame.addKeyListener(this);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        backgroundSound = new Sounds();
        explosionSound = new Sounds();
        Font font = new Font("Monaco", Font.BOLD | Font.ITALIC, 30);

        rocketIcon = new ImageIcon("src\\main\\resources\\Images\\rocket (1).png");
        asteroidIcon = new ImageIcon("src\\main\\resources\\Images\\asteroid.png");
        asteroidIcon2 = new ImageIcon("src\\main\\resources\\Images\\asteroid.png");
        asteroidIcon3 = new ImageIcon("src\\main\\resources\\Images\\asteroid.png");
        enemyRocketIcon = new ImageIcon("src\\main\\resources\\Images\\enemy.png");

        jFrame.getContentPane().setBackground(Color.black);
        backgroundSound.playBackgroundSong();

        gameOverLabel = new JLabel("GAME OVER!");
        gameOverLabel.setBounds(150,200,200,100);
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setVisible(false);
        gameOverLabel.setFont(font);

        rocketJLabel = new JLabel();
        rocketJLabel.setBounds(210,400,60,65);

        Random random = new Random();
        asteroidX2 = random.nextInt(400);
        asteroidY2 = random.nextInt(400);

        Random enemyRandom = new Random();
        enemyX = random.nextInt(500);

        enemyRocketLabel = new JLabel();
        enemyRocketLabel.setBounds(enemyX,enemyY, 80,80);

        asteroidJLabel = new JLabel();
        asteroidJLabel2 = new JLabel();
        asteroidJLabel.setBounds(asteroidX,asteroidY, 70,70);
        asteroidJLabel2.setBounds(asteroidX2,asteroidY2, 70,70);

        rocketJLabel.setIcon(rocketIcon);
        asteroidJLabel.setIcon(asteroidIcon);
        asteroidJLabel2.setIcon(asteroidIcon2);
        enemyRocketLabel.setIcon(enemyRocketIcon);
        enemyRocketLabel.setVisible(false);
        jFrame.add(asteroidJLabel);
        jFrame.add(asteroidJLabel2);
        jFrame.add(rocketJLabel);
        jFrame.add(gameOverLabel);
        jFrame.add(enemyRocketLabel);
        jFrame.setVisible(true);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               asteroidX2 =  asteroidX2 + (10 * asteroidXedge2);
               asteroidY2 =  asteroidY2 + (10 * asteroidYedge2);
                asteroidX = asteroidX + (10 * asteroidXedge); // Move the asteroid by 10 pixels
                asteroidY = asteroidY +(10 * asteroidYedge);

                if (!gameOver){
                    // Check if the asteroid has reached the right edge or left edge
                    if (asteroidX >= 417 || asteroidX <= 0) {
                        asteroidXedge = -asteroidXedge; // Reverse the direction of movement
                    }
                    if (asteroidY >= 400 || asteroidY <=0){
                        asteroidYedge = -asteroidYedge;
                    }
                    if(asteroidX2 >=417 || asteroidX2<=0){
                        asteroidXedge2 = -asteroidXedge2;
                    }
                    if (asteroidY2 >= 400 || asteroidY2 <=0){
                        asteroidYedge2 = -asteroidYedge2;
                    }
                    asteroidJLabel.setLocation(asteroidX, asteroidY);
                    asteroidJLabel2.setLocation(asteroidX2, asteroidY2);
                    System.out.println(asteroidX2);
                    System.out.println(asteroidY2);
                }
            }
        });

        timer.start();
        enemyShowTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enemyRocketLabel.setVisible(true);
            }
        });
        enemyShowTimer.start();

        enemyMoveTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enemyY = enemyY + 10;
                enemyRocketLabel.setLocation(enemyX, enemyY);
            }
        });
        enemyMoveTimer.start();
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

    private int getRandomDirection(){
        Random random = new Random();
        return random.nextBoolean() ? 1:-1;
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
        checkCollision();
        System.out.println(newY);
    }
    public void checkCollision(){
        Rectangle asteroidRect = new Rectangle(asteroidX, asteroidY, asteroidJLabel.getWidth(), asteroidJLabel.getHeight());
        Rectangle asteroidRect2 = new Rectangle(asteroidX2, asteroidY2, asteroidJLabel2.getWidth(), asteroidJLabel2.getHeight());
        Rectangle rocketRect = new Rectangle(newX, newY, rocketJLabel.getWidth(), rocketJLabel.getHeight());
        Rectangle enemyRect = new Rectangle(enemyX, enemyY, enemyRocketLabel.getWidth(), enemyRocketLabel.getHeight());
        if (asteroidRect.intersects(rocketRect) || asteroidRect2.intersects(rocketRect) || enemyRect.intersects(rocketRect)) {
            System.out.println("GAME OVER");
            explosionSound.playSoundEffect();
            gameOverLabel.setVisible(true);
            gameOver = true;
            enemyMoveTimer.stop();

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
