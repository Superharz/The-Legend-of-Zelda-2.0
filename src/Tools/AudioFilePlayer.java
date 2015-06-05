package Tools;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Creates a new AudioFile Player and plays the given Sound File
 *
 * @author NOT from SuperHarz Ent.
 */
public class AudioFilePlayer extends Thread {

    private String filename;
    private URL u;
    private boolean f = true;
    private Position curPosition;
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb 

    enum Position {

        LEFT, RIGHT, NORMAL
    };

    public AudioFilePlayer(String wavfile) {
        filename = wavfile;
        curPosition = Position.NORMAL;
    }

    public AudioFilePlayer(URL wavfile) {
        u = wavfile;
        f = false;
        curPosition = Position.NORMAL;
    }

    public AudioFilePlayer(String wavfile, Position p) {
        filename = wavfile;
        curPosition = p;
    }

    @Override
    public void run() {
        AudioInputStream audioInputStream = null;
        try {
            if (f) {
                File soundFile = new File(filename);
                if (!soundFile.exists()) {
                    System.err.println("Wave file not found: " + filename);
                    return;
                }
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            } else {
                audioInputStream = AudioSystem.getAudioInputStream(u);
            }
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
            return;
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (auline.isControlSupported(FloatControl.Type.PAN)) {
            FloatControl pan = (FloatControl) auline
                    .getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT) {
                pan.setValue(1.0f);
            } else if (curPosition == Position.LEFT) {
                pan.setValue(-1.0f);
            }
        }
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
