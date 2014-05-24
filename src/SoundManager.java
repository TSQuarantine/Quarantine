import java.io.File;
import javax.sound.sampled.*;

class SoundManager
{
  private Clip backgroundMusic;
  private boolean soundRunning;
  
  public long playSound(String soundName)
  {
    Clip c = makeClip(soundName);
    
    System.out.println(c);
    
    Thread t = new Thread(new SoundParticle(c));
    t.start();
    return c.getMicrosecondLength();
  }
  
  public void setBackgroundMusic(String musicTitle)
  {
    Clip c = makeClip(musicTitle);
    
    backgroundMusic.close();
    backgroundMusic = c;
    backgroundMusic.start();
  }
  
  public Clip makeClip(String file)
  {
    Clip clip = null;
    try
    {
      if(new File("../sfx/" + file + ".wav").exists())
      {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("../sfx/"+file+".wav"));
        clip = AudioSystem.getClip();
        clip.open(audioIn);
      }
      else
      {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("sfx/"+file+".wav"));
        clip = AudioSystem.getClip();
        clip.open(audioIn);
      }
    }
    catch(Exception e)
    {
      System.out.println(e);
      e.printStackTrace();
    }
    return clip;
  }
  
  private class SoundParticle implements Runnable
  {
    Clip clip;
    
    public SoundParticle(Clip c)
    {
      clip = c;
    }
    
    public void run()
    {
      soundRunning = true;
      FloatControl volume = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
      volume.setValue(-25);
      clip.start();
      System.out.println("Started");
      while(clip.isActive())
      {}
      clip.close();
      System.out.println("Finished");
      volume.setValue(25);
      soundRunning = false;
    }
  }
}