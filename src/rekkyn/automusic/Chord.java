package rekkyn.automusic;

import java.util.ArrayList;

public class Chord {
    
    int note1 = 0;
    int note2 = 0;
    int note3 = 0;
    
    public Chord(String s) {
        char root = s.charAt(0);
        
        switch (root) {
        case 'F':
            note1 = 53;
            break;
        case 'G':
            note1 = 55;
            break;
        case 'A':
            note1 = 57;
            break;
        case 'B':
            note1 = 59;
            break;
        case 'C':
            note1 = 60;
            break;
        case 'D':
            note1 = 62;
            break;
        case 'E':
            note1 = 63;
            break;
        default:
            System.out.println("Ya dun gooft.");
            break;
        }
        
        if (s.contains("b") && s.contains("#")) {
            System.out.println("Nice try.");
        } else if (s.contains("b")) {
            if (root == 'C' || root == 'F') {
                System.out.println("How bout no.");
            } else {
                note1--;
            }
        } else if (s.contains("#")) {
            if (root == 'B' || root == 'E') {
                System.out.println("How bout no.");
            } else {
                note1++;
            }
        }
        
        if (s.contains("m")) {
            note2 = note1 + 3;
        } else {
            note2 = note1 + 4;
        }
        
        note3 = note1 + 7;
    }
    
    public Chord(Chord prevChord, String s) {
        char root = s.charAt(0);
        
        switch (root) {
        case 'F':
            note1 = 53;
            break;
        case 'G':
            note1 = 55;
            break;
        case 'A':
            note1 = 57;
            break;
        case 'B':
            note1 = 59;
            break;
        case 'C':
            note1 = 60;
            break;
        case 'D':
            note1 = 62;
            break;
        case 'E':
            note1 = 63;
            break;
        default:
            System.out.println("Ya dun gooft.");
            break;
        }
        
        if (s.contains("b") && s.contains("#")) {
            System.out.println("Nice try.");
        } else if (s.contains("b")) {
            if (root == 'C' || root == 'F') {
                System.out.println("How bout no.");
            } else {
                note1--;
            }
        } else if (s.contains("#")) {
            if (root == 'B' || root == 'E') {
                System.out.println("How bout no.");
            } else {
                note1++;
            }
        }
        
        if (s.contains("m")) {
            note2 = note1 + 3;
        } else {
            note2 = note1 + 4;
        }
        
        note3 = note1 + 7;
    }
    
    public void play(int length) {
        Main.mf.noteOn(0, note1, 127);
        Main.mf.noteOn(0, note2, 127);
        Main.mf.noteOn(0, note3, 127);
        
        Main.mf.noteOff(length, note1);
        Main.mf.noteOff(0, note2);
        Main.mf.noteOff(0, note3);
    }
    
    public static class Progression {
        
        public ArrayList<Chord> progression = new ArrayList<Chord>();
        
        public Progression(String[] s) {
            for (String element : s) {
                progression.add(new Chord(element));
            }
        }
        
        public void play() {
            for (Chord c : progression) {
                c.play(Main.QUARTER);
            }
        }
    }
}
