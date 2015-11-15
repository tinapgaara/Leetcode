package bloomberg.design.musicplayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by yingtan on 11/15/15.
 */
public class MusicPlayer {

    // key: playlist's name;  value: list of songs// for shuffle
    private Map<String, List<Song>> playlists;
    // key: song's name, value: song
    private Map<String, Song> songs;

    public MusicPlayer() {
        playlists = new HashMap<>();
        songs = new HashMap<>();
    }

    public void shuffle(String playListName) {
        List<Song> songs = playlists.get(playListName);
        int totalNum = songs.size();
        Random random = new Random();
        // for each song in list: choose any element between whole lists, and exchange it with the current song
        for (int i = 0 ; i < songs.size() ; i ++) {
            int changeRandIndex = random.nextInt(totalNum);
            Song exchangeSong = songs.get(changeRandIndex);
            Song curSong = songs.get(i);
            songs.set(i, exchangeSong);
            songs.set(changeRandIndex, curSong);
        }
    }

}
