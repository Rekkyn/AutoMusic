package rekkyn.automusic.bass;

import rekkyn.automusic.Main;
import rekkyn.automusic.MidiFile.Track;
import rekkyn.automusic.Pattern;
import rekkyn.automusic.Song;

public class AlternatingOctave implements Pattern {

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
                Main.mf.noteOnOffNow(Main.EIGHTH, rootNum, 127, track);
                Main.mf.noteOnOffNow(Main.EIGHTH, rootNum + 12, 127, track);
                chordLength -= Main.QUARTER;
            }
            prevNote = rootNum;
        }
    }
}
