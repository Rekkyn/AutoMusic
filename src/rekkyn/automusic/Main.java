package rekkyn.automusic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.midi.*;

import rekkyn.automusic.MidiFile.Track;
import rekkyn.automusic.patterns.ClosestChord;
import rekkyn.automusic.patterns.PopcornBass;

public class Main {
    
    public static final int SIXTEENTH = 4;
    public static final int EIGHTH = 8;
    public static final int QUARTER = 16;
    public static final int HALF = 32;
    public static final int WHOLE = 64;
    
    public static MidiFile mf = new MidiFile();
    
    public static void main(String[] args) throws Exception {
        
        Song song = new Song().setProgression(new String[] { "Cm", "Ab", "Eb", "Bb", "Cm", "Bb", "Ab", "Gm" })
                .setChordLength(new Integer[] { WHOLE, WHOLE, WHOLE, WHOLE, WHOLE, WHOLE, WHOLE, WHOLE }).setKey("Cm");
        
        mf.progChange(0, Track.CHORDS);
        song.add(new ClosestChord(HALF), Track.CHORDS);
        mf.progChange(33, Track.BASS);
        song.add(new PopcornBass(), Track.BASS);
        song.add(new Melody(), Track.MELODY);
        
        mf.writeToFile("test1.mid");
        
        try {
            Sequence sequence = MidiSystem.getSequence(new File("test1.mid"));
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.start();
        } catch (MalformedURLException e) {} catch (IOException e) {} catch (MidiUnavailableException e) {} catch (InvalidMidiDataException e) {}
        
    }
    
    /** @param a first note
     * @param b second note
     * @return the absolute distance between the two notes */
    public static int distanceBetweenNotes(int a, int b) {
        int n = Math.abs(a % 12 - b % 12);
        if (n > 6)
            return 12 - n;
        else
            return n;
    }
    
    /** @param a first note
     * @param b second note
     * @return the relative distance between the 2 notes, i.e. positive or
     *         negative */
    public static int relDistanceBetweenNotes(int a, int b) {
        int n = b % 12 - a % 12;
        if (n > 6)
            return n - 12;
        else if (n < -6)
            return n + 12;
        else
            return n;
    }
    
    /** @param s chord name
     * @return the note that is the root of the chord */
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
    
    /** @param s chord name
     * @param prevNote the previous note; if nothing set to 0
     * @param min the minimum note value
     * @param max the maximum note value
     * @param traspose how much to traspose the note
     * @return the root note */
    public static int getRootFromChord(String s, int prevNote, int min, int max, int traspose) {
        int rootNum;
        if (prevNote != 0) {
            rootNum = prevNote + relDistanceBetweenNotes(prevNote, getRootFromChord(s) + traspose);
        } else {
            rootNum = getRootFromChord(s) + traspose;
        }
        while (rootNum < min)
            rootNum += 12;
        while (rootNum > max)
            rootNum -= 12;
        return rootNum;
    }
    
}
