import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Владимир on 02.08.2017.
 */
public class MediaPlayer {

    MediaPlayer(BufferedInputStream input) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(input);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setFramePosition(0);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
            clip.stop();
            clip.close();
            ais.close();
            input.close();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        } catch (InterruptedException exc) {
        }
    }

    MediaPlayer(AudioInputStream input) {
        try {
            AudioInputStream ais = input;
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setFramePosition(0);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
            clip.stop();
            clip.close();
            ais.close();
            input.close();
        } catch (IOException | LineUnavailableException exc) {
            exc.printStackTrace();
        } catch (InterruptedException exc) {
        }
    }
}
