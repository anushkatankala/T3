import java.util.*;

public class MusicExchangeCenter {
    private List<User> users;
    private Map<String, Float> royalties = new HashMap<>();
    private List<Song> downloadedSongs = new ArrayList<>();

    public MusicExchangeCenter (){
        users = new ArrayList<>();
    }

    public List<Song> getDownloadedSongs() {return downloadedSongs;}

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

    public Song getSong(String title, String ownerName) {
        for (User u : onlineUsers()) {
            if (u.getUserName().contains(ownerName)) {
                for (Song s : u.getSongList()) {
                    if (s.getTitle().contains(title)) {
                        // Add the song to the downloadedSongs list
                        downloadedSongs.add(s);

                        // Update royalties for the artist of the song
                        String artist = s.getArtist();
                        royalties.put(artist, royalties.getOrDefault(artist, 0f) + 0.25f);

                        return s;  // Return the song after processing
                    }
                }
            }
        }
        return null;  // Return null if the song was not found
    }



    public void displayRoyalties(){
        System.out.println("Amount  Artist\n---------------");
        for (String s : royalties.keySet()){
            System.out.printf("$%-6.2f %s%n", royalties.get(s), s);
        }
    }

    public TreeSet<Song> uniqueDownloads(){
        TreeSet<Song> unique = new TreeSet<>();
        for (Song s: downloadedSongs){
            if(!unique.contains(s)){
                unique.add(s);
            }
        }
        return unique;
    }

    public List<Pair<Integer, Song>> songsByPopularity() {
        List<Pair<Integer, Song>> songs = new ArrayList<>();
        // Iterate over downloaded songs
        for (Song s : downloadedSongs) {
            boolean found = false;
            // Check if the song is already in the list
            for (Pair<Integer, Song> pair : songs) {
                if (pair.getValue().equals(s)) {
                    // If the song already exists, increment its download count
                    pair.setKey(pair.getKey() + 1);
                    found = true;
                    break; // Exit the loop once we find and update the song
                }
            }
            // If the song was not found in the list, add it with a count of 1
            if (!found) {
                songs.add(new Pair<>(1, s));
            }
        }

        Collections.sort(songs, new Comparator<Pair<Integer, Song>>() {
            public int compare(Pair<Integer, Song> p1, Pair<Integer, Song> p2) {
                return Integer.compare(p2.getKey(), p1.getKey());
            }
        });

        return songs;
    }


}
