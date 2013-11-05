package rekkyn.automusic;

import java.util.ArrayList;
import java.util.Arrays;

import rekkyn.automusic.MidiFile.Track;

public class Song {
    
    public static ArrayList<String> progression;
    
    public Song setProgression(String[] s) {
        progression = new ArrayList<String>(Arrays.asList(s));
        return this;
    }
    
    public void add(Pattern p, Track track) {
        p.play(track);
    }
    
}
