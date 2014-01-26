package rekkyn.automusic;

import java.util.Random;

import rekkyn.automusic.MidiFile.Track;

public class Melody implements Playable {
    
    int prevNote = 60;
    int prevPos = 0;
    Random rand = new Random();
    
    @Override
    public void play(Track track) {
        for (int lmnop = 0; lmnop < Song.progression.size(); lmnop++) {
            int rootNum = Main.getRootFromChord(Song.key, prevNote, 60, 72, 0);
            int chordLength = Song.chordLength.get(lmnop);
            while (chordLength > 0) {
                int pos = rand.nextInt(11) - 5;
                pos += prevPos;
                System.out.println(pos);
                if (pos < 0) {
                    pos += Scale.minor.length;
                    rootNum -= 12;
                }
                if (pos >= Scale.minor.length) {
                    pos -= Scale.minor.length;
                    rootNum += 12;
                }
                Main.mf.noteOnOffNow(Main.QUARTER, rootNum + Scale.minor[pos], 127, track);
                chordLength -= Main.QUARTER;
                prevNote = rootNum + Scale.minor[pos];
                prevPos = pos;
            }
        }
        
    }
    
}
