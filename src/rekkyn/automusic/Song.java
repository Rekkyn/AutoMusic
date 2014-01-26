package rekkyn.automusic;

import java.util.ArrayList;
import java.util.Arrays;

import rekkyn.automusic.MidiFile.Track;

public class Song {
    
    public static ArrayList<String> progression;
    
    /** @param s list of chord names
     * @return this */
    public Song setProgression(String[] s) {
        progression = new ArrayList<String>(Arrays.asList(s));
        return this;
    }
    
    /** @param p the patern to add
     * @param track which track to add it on */
    public void add(Pattern p, Track track) {
        p.play(track);
    }
    
}
