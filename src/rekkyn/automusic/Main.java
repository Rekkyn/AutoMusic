package rekkyn.automusic;

import rekkyn.automusic.Chord.Progression;

public class Main {
    
    // Note lengths
    // We are working with 32 ticks to the crotchet. So
    // all the other note lengths can be derived from this
    // basic figure. Note that the longest note we can
    // represent with this code is one tick short of a
    // two semibreves (i.e., 8 crotchets)
    
    static final int SIXTEENTH = 4;
    static final int EIGHTH = 8;
    static final int QUARTER = 16;
    static final int HALF = 32;
    static final int WHOLE = 64;
    
    static MidiFile mf = new MidiFile();
    
    /**
     * Test method Ñ creates a file test1.mid when the class is executed
     */
    public static void main(String[] args) throws Exception {
        /*
        // Test 1 Ñ play a C major chord
        
        // Turn on all three notes at start-of-track (delta=0)
        mf.noteOn(0, 60, 127);
        mf.noteOn(0, 64, 127);
        mf.noteOn(0, 67, 127);
        
        // Turn off all three notes after one minim.
        // NOTE delta value is cumulative Ñ only _one_ of
        // these note-offs has a non-zero delta. The second and
        // third events are relative to the first
        mf.noteOff(HALF, 60);
        mf.noteOff(0, 64);
        mf.noteOff(0, 67);
        
        // Test 2 Ñ play a scale using noteOnOffNow
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
        
        // Test 3 Ñ play a short tune using noteSequenceFixedVelocity
        // Note the rest inserted with a note value of -1
        
        int[] sequence = new int[] { 60, EIGHTH + SIXTEENTH, 65, SIXTEENTH, 70, QUARTER + EIGHTH, 69, EIGHTH, 65, EIGHTH / 3, 62,
                EIGHTH / 3, 67, EIGHTH / 3, 72, HALF + EIGHTH, -1, SIXTEENTH, 72, SIXTEENTH, 76, HALF, };
        
        // What the heck Ñ use a different instrument for a change
        mf.progChange(18);
        
        mf.noteSequenceFixedVelocity(sequence, 127);
        
        mf.writeToFile("test1.mid");
         */
        
        Progression p = new Progression(new String[] { "C", "G", "Am", "F" });
        
        p.play();
        
        mf.writeToFile("test1.mid");
    }
}
