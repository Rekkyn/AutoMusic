package rekkyn.automusic.chords;

import java.util.ArrayList;

import rekkyn.automusic.Main;
import rekkyn.automusic.MidiFile.Track;
import rekkyn.automusic.Pattern;
import rekkyn.automusic.Song;

public class ClosestChord implements Pattern {
    
    public ArrayList<Integer> notes = new ArrayList<Integer>();
    public ArrayList<Integer> prevNotes = new ArrayList<Integer>();
    
    public void getNotes(String s) {
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
        
        ArrayList<Integer> newnotes = new ArrayList<Integer>();
        if (rootNum != 0) {
            newnotes.add(rootNum);
            newnotes.add(s.contains("m") ? rootNum + 3 : rootNum + 4);
            newnotes.add(rootNum + 7);
        } else {
            newnotes = prevNotes;
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
        for (String chord : Song.progression) {
            getNotes(chord);
            int length = Main.WHOLE;
            for (int note : notes) {
                Main.mf.noteOn(0, note, 127, track);
            }
            
            Main.mf.noteOff(length, notes.get(0), track);
            
            for (int note : notes) {
                Main.mf.noteOff(0, note, track);
            }
            prevNotes = (ArrayList<Integer>) notes.clone();
            notes.clear();
        }
    }
    
    
}
