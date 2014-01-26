package rekkyn.automusic;

import rekkyn.automusic.MidiFile.Track;

public interface Playable {
    /** Plays the pattern
     * 
     * @param track the track to play on */
    public void play(Track track);
}
