package rekkyn.automusic.bass;

import rekkyn.automusic.*;
import rekkyn.automusic.MidiFile.Track;

public class PopcornBass implements Pattern {
    
    int prevNote = 0;
    
    @Override
    public void play(Track track) {
        for (String chord : Song.progression) {
            int rootNum = Main.getRootFromChord(chord, prevNote, 24, 48) - 24;

            for (int i = 0; i < 3; i++) {
                Main.mf.noteOnOffNow(Main.EIGHTH, rootNum, 127, track);
                Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum + 12, 127, track);
                Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum + 7, 127, track);
            }
            Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum, 127, track);
            Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum + (chord.contains("m") ? 3 : 4), 127, track);
            Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum + 7, 127, track);
            Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum + 12, 127, track);
            
            prevNote = rootNum;
        }
    }
    
}
