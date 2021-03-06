package rekkyn.automusic.patterns;

import java.util.ArrayList;

import rekkyn.automusic.*;
import rekkyn.automusic.MidiFile.Track;

public class ClosestChord implements Playable {
    
    public ArrayList<Integer> notes = new ArrayList<Integer>();
    public ArrayList<Integer> prevNotes = new ArrayList<Integer>();
    
    int length = Main.WHOLE;
    
    public ClosestChord(int length) {
        this.length = length;
    }
    
    public void getNotes(String s) {
        int rootNum = Main.getRootFromChord(s);
        
        ArrayList<Integer> newnotes = new ArrayList<Integer>();
        if (rootNum != 0) {
            newnotes.add(rootNum);
            newnotes.add(s.contains("m") ? rootNum + 3 : rootNum + 4);
            newnotes.add(rootNum + 7);
        } else {
            notes.clear();
            notes.add(48);
            notes.add(52);
            notes.add(55);
            notes.add(66);
            notes.add(69);
            notes.add(74);
            return;
        }
        
        int closest = newnotes.get(0);
        if (!prevNotes.isEmpty()) {
            for (int newNote : newnotes) {
                if (Main.distanceBetweenNotes(prevNotes.get(0), newNote) <= Main.distanceBetweenNotes(prevNotes.get(0), closest)) {
                    closest = prevNotes.get(0) + Main.relDistanceBetweenNotes(prevNotes.get(0), newNote);
                }
            }
        }
        
        if (!newnotes.contains(closest)) {
            if (closest < newnotes.get(0)) {
                newnotes.set(0, newnotes.get(0) - 12);
                newnotes.set(1, newnotes.get(1) - 12);
                newnotes.set(2, newnotes.get(2) - 12);
            } else if (closest > newnotes.get(0)) {
                newnotes.set(1, newnotes.get(1) + 12);
                newnotes.set(0, newnotes.get(0) + 12);
                newnotes.set(2, newnotes.get(2) + 12);
            }
        }
        
        if (newnotes.indexOf(closest) != 0) {
            for (int i = 0; i < newnotes.size(); i++) {
                if (newnotes.indexOf(closest) == 1) {
                    notes.clear();
                    notes.add(newnotes.get(1));
                    notes.add(newnotes.get(2));
                    notes.add(newnotes.get(0) + 12);
                } else if (newnotes.indexOf(closest) == 2) {
                    notes.clear();
                    notes.add(newnotes.get(2));
                    notes.add(newnotes.get(0) + 12);
                    notes.add(newnotes.get(1) + 12);
                }
            }
        } else {
            notes = newnotes;
        }
        
    }
    
    @Override
    public void play(Track track) {
        for (int lmnop = 0; lmnop < Song.progression.size(); lmnop++) {
            getNotes(Song.progression.get(lmnop));
            int chordLength = Song.chordLength.get(lmnop);
            while (chordLength > 0) {
                int playLength;
                if (chordLength > length) {
                    playLength = length;
                } else {
                    playLength = chordLength;
                }
                
                for (int note : notes) {
                    Main.mf.noteOn(0, note, 127, track);
                }
                
                Main.mf.noteOff(playLength, notes.get(0), track);
                
                for (int note : notes) {
                    Main.mf.noteOff(0, note, track);
                }
                
                chordLength -= playLength;
                
            }
            prevNotes = (ArrayList<Integer>) notes.clone();
            notes.clear();
        }
    }
    
}
