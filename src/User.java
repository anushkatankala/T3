import java.util.ArrayList;
import java.util.List;

public class User {
  private String     userName;
  private boolean    online;
  private List<Song> songList;

  public User()  { this(""); }
  
  public User(String u)  {
    userName = u;
    online = false;
    songList = new ArrayList<>();
  }
  
  public String getUserName() { return userName; }
  public boolean isOnline() { return online; }
  public List<Song> getSongList(){return songList; }

  public String toString()  {
    String s = "" + userName + ": " + getSongList().size() + " songs (";
    if (!online) s += "not ";
    return s + "online)";
  }

  public void addSong(Song s){
    s.setOwner(this);
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

  public List<String> requestCompleteSonglist(MusicExchangeCenter m){
    List<String> songList = new ArrayList<>();
    String title = "TITLE";
    String artist = "ARTIST";
    String time = "TIME";
    String owner = "OWNER";
    songList.add(String.format("    %-30s %-16s %-7s %-15s", title, artist, time, owner));
    songList.add("");
    int count = 0;
    for (Song s: m.allAvailableSongs()){
      count++;
      songList.add(String.format("%2d. %-30s %-16s %-1s:%-5s %-10s", count, s.getTitle(), s.getArtist(), s.getMinutes(), s.getSeconds(), s.getOwner().getUserName()));
    }
    return songList;
  }

  public List<String> requestSonglistByArtist(MusicExchangeCenter m, String artist){
    List<String> songList = new ArrayList<>();
    String title = "TITLE";
    String a = "ARTIST";
    String time = "TIME";
    String owner = "OWNER";
    songList.add(String.format("    %-30s %-16s %-7s %-15s", title, a, time, owner));
    songList.add("");
    int count = 0;
    for (Song s: m.allAvailableSongs()){
      if (artist.toLowerCase().contains(s.getArtist().toLowerCase())){
        count++;
        songList.add(String.format("%2d. %-30s %-16s %-1s:%-5s %-10s", count, s.getTitle(), s.getArtist(), s.getMinutes(), s.getSeconds(), s.getOwner().getUserName()));
      }
    }
    return songList;
  }

  public boolean songWithTitle(String title){
    for (Song s: this.getSongList()){
      if (title.toLowerCase().contains(s.getTitle().toLowerCase())){
        return true;
      }
    }
    return false;
  }

  public void downloadSong(MusicExchangeCenter m, String title, String ownerName) {
    // try to download the song from the music exchange center
    Song song = m.getSong(title, ownerName);

    // only add the song if it isn't already in the user's song list
    if (song != null && !this.getSongList().contains(song)) {
      this.addSong(song); // Add to the user's song list
    }
  }




}
