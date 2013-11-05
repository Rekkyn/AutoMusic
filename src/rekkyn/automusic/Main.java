package rekkyn.automusic;

import rekkyn.automusic.MidiFile.Track;
import rekkyn.automusic.bass.AlternatingOctave;
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
    
    /**
     * Test method � creates a file test1.mid when the class is executed
     */
    public static void main(String[] args) throws Exception {
        /*
        // Test 1 � play a C major chord
        
        // Turn on all three notes at start-of-track (delta=0)
        mf.noteOn(0, 60, 127);
        mf.noteOn(0, 64, 127);
        mf.noteOn(0, 67, 127);
        
        // Turn off all three notes after one minim.
        // NOTE delta value is cumulative � only _one_ of
        // these note-offs has a non-zero delta. The second and
        // third events are relative to the first
        mf.noteOff(HALF, 60);
        mf.noteOff(0, 64);
        mf.noteOff(0, 67);
        
        // Test 2 � play a scale using noteOnOffNow
        // We don't need any delta values here, so long as one
        // note comes straight after the previous one
        
        mf.noteOnOffNow(EIGHTH, 60, 127);
        mf.noteOnOffNow(EIGHTH, 62, 127);
        mf.noteOnOffNow(EIGHTH, 64, 127);
        mf.noteOnOffNow(EIGHTH, 65, 127);
        mf.noteOnOffNow(EIGHTH, 67, 127);
        mf.noteOnOffNow(EIGHTH, 69, 127);
        mf.noteOnOffNow(EIGHTH, 71, 127);
        mf.noteOnOffNow(EIGHTH, 72, 127);
        
        // Test 3 � play a short tune using noteSequenceFixedVelocity
        // Note the rest inserted with a note value of -1
        
        int[] sequence = new int[] { 60, EIGHTH + SIXTEENTH, 65, SIXTEENTH, 70, QUARTER + EIGHTH, 69, EIGHTH, 65, EIGHTH / 3, 62,
                EIGHTH / 3, 67, EIGHTH / 3, 72, HALF + EIGHTH, -1, SIXTEENTH, 72, SIXTEENTH, 76, HALF, };
        
        // What the heck � use a different instrument for a change
        mf.progChange(18);
        
        mf.noteSequenceFixedVelocity(sequence, 127);
        
        mf.writeToFile("test1.mid");
         */
        
        Song song = new Song().setProgression(new String[] { "A", "C", "E", "A", "B", "C", "D", "G" });
        
        mf.progChange(10, Track.CHORDS);
        song.add(new ClosestChord(), Track.CHORDS);
        mf.progChange(42, Track.BASS);
        song.add(new AlternatingOctave(), Track.BASS);
        
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
    
}
