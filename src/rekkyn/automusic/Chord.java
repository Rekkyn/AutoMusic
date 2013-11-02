package rekkyn.automusic;

import java.util.ArrayList;

import rekkyn.automusic.MidiFile.Track;

public class Chord {
    
    public ArrayList<Integer> notes = new ArrayList<Integer>();
    
    public Chord(String s) {
        this(null, s);
    }
    
    public Chord(Chord prevChord, String s) {
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
            newnotes = prevChord.notes;
        }
        
        int closest = newnotes.get(0);
        if (prevChord != null) {
            for (int newNote : newnotes) {
                if (distanceBetweenNotes(prevChord.notes.get(0), newNote) <= distanceBetweenNotes(prevChord.notes.get(0), closest)) {
                    closest = prevChord.notes.get(0) + relDistanceBetweenNotes(prevChord.notes.get(0), newNote);
                }
            }
        }
        System.out.println(closest);
        
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
    
    public void play(int length) {
        
        for (int note : notes) {
            Main.mf.noteOn(0, note, 127, Track.CHORDS);
        }
        
        Main.mf.noteOff(length, notes.get(0), Track.CHORDS);
        
        for (int note : notes) {
            Main.mf.noteOff(0, note, Track.CHORDS);
        }
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
            return 12 - n;
        else
            return n;
    }
    
    public static class Progression {
        
        public ArrayList<Chord> progression = new ArrayList<Chord>();
        
        public Progression(String[] s) {
            for (int i = 0; i < s.length; i++) {
                if (i == 0) {
                    progression.add(new Chord(s[i]));
                } else {
                    progression.add(new Chord(progression.get(progression.size() - 1), s[i]));
                }
            }
        }
        
        public void play() {
            for (Chord c : progression) {
                c.play(Main.QUARTER);
            }
        }
    }
}
