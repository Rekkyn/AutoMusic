package rekkyn.automusic.bass;

import rekkyn.automusic.Main;
import rekkyn.automusic.MidiFile.Track;
import rekkyn.automusic.Pattern;
import rekkyn.automusic.Song;

public class PopcornBass implements Pattern {

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
            while (chordLength - Main.QUARTER > 0) {
                Main.mf.noteOnOffNow(Main.EIGHTH, rootNum, 127, track);
                Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum + 12, 127, track);
                Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum + 7, 127, track);
                chordLength -= Main.QUARTER;
            }
            Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum, 127, track);
            Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum + (Song.progression.get(lmnop).contains("m") ? 3 : 4), 127,
                                 track);
            Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum + 7, 127, track);
            Main.mf.noteOnOffNow(Main.SIXTEENTH, rootNum + 12, 127, track);

            prevNote = rootNum;
        }
    }

}
