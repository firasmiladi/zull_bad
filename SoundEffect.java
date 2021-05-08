    import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.event.ActionListener;
import java.io.File;

/**
 *This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class is used to trait the music in this game .
 * 
 * @author Firas miladi + source=https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java 
 * @version 30/04/2020                                                  
 */
public class SoundEffect
{
    private Clip myClip;

    /**
     * Constructeur par defaut d'objets de la classe SoundEffect
     * @param pFileName music title
     */
    public SoundEffect(final String pFileName)
    {
      try {
          File file = new File(pFileName);
        Clip myClip = AudioSystem.getClip(); 
        AudioInputStream sound = AudioSystem.getAudioInputStream(file); 
        myClip.open(sound);
      } catch(Exception e){}// SoundEffect()
    }
    
    public void play() {myClip.start();}
    
    public void close() {myClip.close();}
    
} // SoundEffect
    
