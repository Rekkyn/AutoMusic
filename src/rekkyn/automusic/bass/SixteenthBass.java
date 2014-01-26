package rekkyn.automusic.bass;

import rekkyn.automusic.*;
import rekkyn.automusic.MidiFile.Track;

public class SixteenthBass implements Pattern {

    int prevNote = 0;

    @Override
    public void play(Track track) {
        for (int lmnop = 0; lmnop < Song.progression.size(); lmnop++) {
            int rootNum = Main.getRootFromChord(Song.progression.get(lmnop)) - 24;

            if (prevNote != 0) {
                rootNum = prevNote + Main.relDistanceBetweenNotes(prevNote, rootNum);
            }
            while (rootNum < 24)
                rootNum += 12;
            while (rootNum > 48)
                rootNum -= 12;

            int chordLength = Song.chordLength.get(lmnop);
            while (chordLength > 0) {
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
                chordLength -= Main.QUARTER;
            }
            prevNote = rootNum;
        }
    }

}
