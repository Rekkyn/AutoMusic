package rekkyn.automusic.bass;

import rekkyn.automusic.Main;
import rekkyn.automusic.MidiFile.Track;
import rekkyn.automusic.Pattern;
import rekkyn.automusic.Song;

public class AlternatingOctave implements Pattern {
    
    int prevNote = 0;
    
    @Override
    public void play(Track track) {
        
        for (String chord : Song.progression) {
            char root = chord.charAt(0);
            int rootNum = 0;
            
            switch (root) {
            case 'F':
                rootNum = 29;
                break;
            case 'G':
                rootNum = 31;
                break;
            case 'A':
                rootNum = 33;
                break;
            case 'B':
                rootNum = 35;
                break;
            case 'C':
                rootNum = 36;
                break;
            case 'D':
                rootNum = 38;
                break;
            case 'E':
                rootNum = 40;
                break;
            default:
                System.out.println("Son.");
                break;
            }
            
            if (chord.contains("b") && chord.contains("#")) {
                System.out.println("Nice try.");
            } else if (chord.contains("b")) {
                rootNum--;
            } else if (chord.contains("#")) {
                rootNum++;
            }
            
            if (prevNote != 0) {
                rootNum = prevNote + Main.relDistanceBetweenNotes(prevNote, rootNum);
            }
            while (rootNum < 24)
                rootNum += 12;
            while (rootNum > 48)
                rootNum -= 12;
            
            for (int i = 0; i < 4; i++) {
                Main.mf.noteOnOffNow(Main.EIGHTH, rootNum, 127, track);
                Main.mf.noteOnOffNow(Main.EIGHTH, rootNum + 12, 127, track);
            }
            
            prevNote = rootNum;
            
        }
    }
}
