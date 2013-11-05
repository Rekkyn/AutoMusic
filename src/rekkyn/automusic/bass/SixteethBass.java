package rekkyn.automusic.bass;

import rekkyn.automusic.Main;
import rekkyn.automusic.MidiFile.Track;
import rekkyn.automusic.Pattern;
import rekkyn.automusic.Song;

public class SixteethBass implements Pattern {
    
    int prevNote = 0;
    
    @Override
    public void play(Track track) {
        for (String chord : Song.progression) {
            int rootNum = Main.getRootFromChord(chord) - 24;
            
            if (prevNote != 0) {
                rootNum = prevNote + Main.relDistanceBetweenNotes(prevNote, rootNum);
            }
            while (rootNum < 24)
                rootNum += 12;
            while (rootNum > 48)
                rootNum -= 12;
            
            for (int i = 0; i < 4; i++) {
                Main.mf.noteOn(0, rootNum, 127, track);
                Main.mf.noteOn(0, rootNum + 12, 127, track);
                Main.mf.noteOff(Main.SIXTEENTH, rootNum, track);
                Main.mf.noteOff(0, rootNum + 12, track);
                
                Main.mf.noteOn(0, rootNum, 127, track);
                Main.mf.noteOn(0, rootNum + 12, 127, track);
                Main.mf.noteOff(Main.SIXTEENTH, rootNum, track);
                Main.mf.noteOff(0, rootNum + 12, track);
                
                Main.mf.noteOn(0, rootNum, 127, track);
                Main.mf.noteOn(0, rootNum + 12, 127, track);
                Main.mf.noteOff(Main.EIGHTH, rootNum, track);
                Main.mf.noteOff(0, rootNum + 12, track);
                
            }
            prevNote = rootNum;
        }
    }
    
}
