package Software;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Sounds {
    private Clip clip;
    File explosionFile = new File("src\\main\\resources\\Sounds\\explosion.wav");
    File backgroundFile = new File("src\\main\\resources\\Sounds\\spaceBackgroundSound.wav");

    AudioInputStream audioInputStream;

    public void playSoundEffect(){
        {
            try {
                audioInputStream = AudioSystem.getAudioInputStream(explosionFile);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void playBackgroundSong(){
        {
            try {
                audioInputStream = AudioSystem.getAudioInputStream(backgroundFile);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public void start(){
//        if (clip != null && !clip.isRunning()) {
//            clip.start();
//        }
//    }

}
