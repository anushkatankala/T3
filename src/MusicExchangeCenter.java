import java.util.Arrays;

public class MusicExchangeCenter {
    private User[] users;

    public MusicExchangeCenter (){
        users = null;
    }

    public User[] onlineUsers (){
        int count = 0;
        for(User u: users){
            if (u.isOnline()){
                count++;
            }
        }
        User[] online = new User[count];
        int u_count = 0;
        for (User user : users) {
            if (user.isOnline()) {
                online[u_count] = user;
                u_count++;
            }
        }

        return online;
    }

    public Song[] allAvailableSongs (){
        User[] onlineUsers = onlineUsers();

        int availCount = 0;

        for (User u: onlineUsers){
            availCount += u.getSongList().length;
        }

        Song[] availSongs = new Song[availCount];
        for (User u: onlineUsers){
            for (Song s : u.getSongList()){
                availSongs[availSongs.length - 1] = s;
            }
        }

        return availSongs;
    }

    public String toString(){
        return "Music Exchange Center (" + onlineUsers().length + " users online, " + allAvailableSongs().length + " songs available)";
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
            users[users.length - 1] = x;
        }
    }

    public Song[] availableSongsByArtist(String artist){
        int artCount = 0;
        for (Song s: allAvailableSongs()){
            if (s.getArtist().contains(artist)){
                artCount++;
            }
        }
        Song[] availArt = new Song[artCount];
        for (Song s: allAvailableSongs()){
            if (s.getArtist().contains(artist)){
                availArt[availArt.length - 1] = s;
            }
        }
        return availArt;
    }



}
