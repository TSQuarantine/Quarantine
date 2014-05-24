import java.awt.*;
import java.io.File;
import javax.swing.*;
import java.awt.image.*;
import javax.sound.sampled.*;

class Intro
{
  private SoundManager soundM;
  
  public Intro(SoundManager sm)
  {
    soundM = sm;
  }
  
  public void performIntro()
  {
    JFrame f = new JFrame();
    JPanel p = new JPanel(new GridLayout(1,1));
    JLabel l = new JLabel(makeIcon("TS dark"));
    p.add(l);
    p.setBackground(Color.black);
    f.getContentPane().add(p);
    f.setExtendedState(JFrame.MAXIMIZED_BOTH);
    f.setUndecorated(true);
    f.setAlwaysOnTop(true);
    
    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    
    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
    cursorImg, new Point(0, 0), "blank cursor");
    f.setCursor(blankCursor);
    
    f.setVisible(true);
    
    Clip c = soundM.makeClip("Intro");
    FloatControl volume = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
    volume.setValue(-25);
    c.start();
    try{Thread.sleep(c.getMicrosecondLength()/1000L);}
    catch(Exception e){}
    
    f.setVisible(false);
  }
  
  private ImageIcon makeIcon(String pName)
  {
    ImageIcon ii;
    if (new File("../img/" + pName + ".png").exists())
    {
      ii = new ImageIcon("../img/" + pName);
    }
    else
    {
      ii = new ImageIcon(getClass().getResource("img/"+pName + ".png"));
    }
    return ii;
  }
}