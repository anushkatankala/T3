import java.util.Arrays;

public class User {
  private String     userName;
  private boolean    online;
  private Song[] songList;

  public User()  { this(""); }
  
  public User(String u)  {
    userName = u;
    online = false;
    songList = null;
  }
  
  public String getUserName() { return userName; }
  public boolean isOnline() { return online; }
  public Song[] getSongList(){return songList; }

  public String toString()  {
    String s = "" + userName + ": " + getSongList().length + " songs (";
    if (!online) s += "not ";
    return s + "online)";
  }

  public void addSong(Song s){
    if (songList == null) {
      songList[] = s;  // Initialize songList if it's null
    }
    songList.add(s);  // Add the song to the end of the list

  }

  public int totalSongTime (){
    int total = 0;
    for (Song song : songList){
      total += song.getDuration();
    }
    return total;
  }

  public void register(MusicExchangeCenter m){
      m.registerUser(this);
  }

  public void logon(){
    this.online = true;
  }

  public void logoff(){
    this.online = false;
  }
}
