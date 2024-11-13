import java.util.*;

public class MusicExchangeCenter {
    private List<User> users;
    private Map<String, Float> royalties = new HashMap<>();
    private List<Song> downloadedSongs = new ArrayList<>();

    public MusicExchangeCenter (){
        users = new ArrayList<>();
    }

    public List<User> onlineUsers (){
        List<User> online = new ArrayList<>();
        for (User user : users) {
            if (user.isOnline()) {
                online.add(user);
            }
        }
        return online;
    }

    public List<Song> allAvailableSongs (){
        List<User> onlineUsers = onlineUsers();
        List<Song> availSongs = new ArrayList<>();
        for (User u: onlineUsers){
            availSongs.addAll(u.getSongList());
        }
        return availSongs;
    }

    public String toString(){
        return "Music Exchange Center (" + onlineUsers().size() + " users online, " + allAvailableSongs().size() + " songs available)";
    }

    public String userWithName(String s){
        for (User u: users){
            if (u.getUserName().toLowerCase().contains(s.toLowerCase())){
                return u.getUserName();
            }
        }
        return null;
    }

    public void registerUser(User x){
        if (userWithName(x.getUserName()) == null){
            users.add(x);
        }
    }

    public List<Song> availableSongsByArtist(String artist){

        List<Song> availArt = new ArrayList<>();
        for (Song s: allAvailableSongs()){
            if (s.getArtist().contains(artist)){
                availArt.add(s);
            }
        }
        return availArt;
    }

    public Song getSong(String title, String ownerName){
        for (User u: onlineUsers()){
            if (u.getUserName().contains(ownerName)){
                for (Song s: u.getSongList()){
                    if (s.getTitle().contains(title)){
                        return s;
                    }
                }
            }
        }
        return null;
    }

    public void displayRoyalties(){

    }

}
