import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio_player {
    public static void SongCaller() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try (Scanner sc = new Scanner(System.in)) {
            String str = "", Filename;
            System.out.println("ENter file name: = ");
            Filename = sc.nextLine();
            File file = new File(Filename);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            while (!str.equals("Q")) {
                System.out.println("Choice s for start and q for quit and r for rewind:= ");
                str = sc.next();

                switch (str) {
                    case "s" -> clip.start();
                    case "q" -> clip.stop();
                    case "r" -> clip.setMicrosecondPosition(0);
                    default -> System.out.println("error");
                }

                str = "Q";
            }
        }
    }

}
