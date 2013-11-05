package rekkyn.automusic;

import rekkyn.automusic.MidiFile.Track;
import rekkyn.automusic.bass.SixteethBass;
import rekkyn.automusic.chords.ClosestChord;



public class Main {
    
    // Note lengths
    // We are working with 32 ticks to the crotchet. So
    // all the other note lengths can be derived from this
    // basic figure. Note that the longest note we can
    // represent with this code is one tick short of a
    // two semibreves (i.e., 8 crotchets)
    
    public static final int SIXTEENTH = 4;
    public static final int EIGHTH = 8;
    public static final int QUARTER = 16;
    public static final int HALF = 32;
    public static final int WHOLE = 64;
    
    public static MidiFile mf = new MidiFile();
    
    public static void main(String[] args) throws Exception {
        
        Song song = new Song().setProgression(new String[] { "C", "G", "Am", "F" });
        
        // mf.progChange(10, Track.CHORDS);
        song.add(new ClosestChord(QUARTER), Track.CHORDS);
        // mf.progChange(42, Track.BASS);
        song.add(new SixteethBass(), Track.BASS);
        
        mf.writeToFile("test1.mid");
        
    }
    
    public static int distanceBetweenNotes(int a, int b) {
        int n = Math.abs(a % 12 - b % 12);
        if (n > 6)
            return 12 - n;
        else
            return n;
    }
    
    public static int relDistanceBetweenNotes(int a, int b) {
        int n = b % 12 - a % 12;
        if (n > 6)
            return n - 12;
        else if (n < -6)
            return n + 12;
        else
            return n;
    }
    
    public static int getRootFromChord(String s) {
        char root = s.charAt(0);
        int rootNum = 0;
        switch (root) {
        case 'F':
            rootNum = 53;
            break;
        case 'G':
            rootNum = 55;
            break;
        case 'A':
            rootNum = 57;
            break;
        case 'B':
            rootNum = 59;
            break;
        case 'C':
            rootNum = 60;
            break;
        case 'D':
            rootNum = 62;
            break;
        case 'E':
            rootNum = 64;
            break;
        default:
            System.out.println("Ya dun gooft.");
            break;
        }
        
        if (s.contains("b") && s.contains("#")) {
            System.out.println("Nice try.");
        } else if (s.contains("b")) {
            rootNum--;
        } else if (s.contains("#")) {
            rootNum++;
        }
        return rootNum;
        
    }
    
}
