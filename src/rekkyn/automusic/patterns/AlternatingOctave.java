package rekkyn.automusic.patterns;

import rekkyn.automusic.*;
import rekkyn.automusic.MidiFile.Track;

public class AlternatingOctave implements Playable {
    
    int prevNote = 0;
    
    @Override
    public void play(Track track) {
        for (int lmnop = 0; lmnop < Song.progression.size(); lmnop++) {
            int rootNum = Main.getRootFromChord(Song.progression.get(lmnop), prevNote, 24, 48, -24);
            
            int chordLength = Song.chordLength.get(lmnop);
            while (chordLength > 0) {
                Main.mf.noteOnOffNow(Main.EIGHTH, rootNum, 127, track);
                Main.mf.noteOnOffNow(Main.EIGHTH, rootNum + 12, 127, track);
                chordLength -= Main.QUARTER;
            }
            prevNote = rootNum;
        }
    }
}
