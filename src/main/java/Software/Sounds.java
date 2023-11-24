package Software;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Sounds {
    private Clip clip;
    File file = new File("src/explosion.wav");

    AudioInputStream audioInputStream;

    {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void start(){
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

}
