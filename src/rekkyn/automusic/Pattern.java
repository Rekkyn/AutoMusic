package rekkyn.automusic;

import rekkyn.automusic.MidiFile.Track;

public interface Pattern {
    /** Plays the pattern
     * 
     * @param track the track to play on */
    public void play(Track track);
}
