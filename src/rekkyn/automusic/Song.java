package rekkyn.automusic;

import java.util.ArrayList;
import java.util.Arrays;

import rekkyn.automusic.MidiFile.Track;

public class Song {
    
    public static ArrayList<String> progression;
    public static ArrayList<Integer> chordLength;
    public static String key;
    
    /** @param s list of chord names
     * @return this */
    public Song setProgression(String[] s) {
        progression = new ArrayList<String>(Arrays.asList(s));
        return this;
    }
    
    public Song setChordLength(Integer[] i) {
        for (int Int : i) {
            if (Int % 16 != 0) {
                System.err.println("Chord length must be a multiple of a quarter note");
            }
        }
        chordLength = new ArrayList<Integer>(Arrays.asList(i));
        if (chordLength.size() != progression.size()) {
            System.err.println("Chord lengths don't match up to the chord progression");
        }
        return this;
    }
    
    public Song setKey(String s) {
        key = s;
        return this;
    }

    /** @param p the patern to add
     * @param track which track to add it on */
    public void add(Playable p, Track track) {
        p.play(track);
    }
    
}
