class CentralStarter
{
  public static void main(String[] s)
  {
    SoundManager sm = new SoundManager();
    Intro i = new Intro(sm);
    i.performIntro();
  }
}