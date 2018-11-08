import themidibus.*;
import processing.core.*;
import java.util.*;

// send the MIDI elsewhere to play the notes
public class MelodyPlayer {
	MidiBus outputMidiBus;

	int note_index = 0; // where we are in the notes

	float notems; // the value of a quaver or 1/4 note in millis
	float last_time; // the last time we called draw()
	boolean play; // should we play?
	float bpm; // beats per minute of melody

	double rhythm_multiplier; //determines note length and onset of the next note

	ArrayList<Integer> melody; //list of pitches in order
	ArrayList<Double> rhythm; //list of note lengths (& thus time before next note, in order)

	boolean hasRhythm; //has there been a list of rhythms assigned?
	boolean hasMelody; //has there been a list of pitches assigned?

	PApplet parent;

	

	MelodyPlayer(PApplet p, float tempo) {
		parent = p;
		reset();
		setBPM(tempo);
		play = true;
		hasRhythm = false;
		rhythm_multiplier = 0.5f; // default is 1/8 notes
	}

	void setMelody(ArrayList<Integer> m) {
		melody = m;
		hasMelody = true;
	}

	void setRhythm(ArrayList<Double> r) {
		rhythm = r;
		hasRhythm = true;
	}

	//list the midi devices -- choosing IAC bus (for mac/OS X) so can send to an external program (eg DAW/sampler)
	void setupMidi() {
		MidiBus.list(); // display all ports
		outputMidiBus = new MidiBus(this, "IAC Bus 1", "IAC Bus 1");
	}

	void setBPM(float tempo) {
		bpm = tempo;
		notems = (float) (((1.0 / (bpm / 60.0))) * 1000); //how many ms in a 1/4 note for this tempo
	}

	void setup() {
		setupMidi();
	}
	
	
	//send the melody out, in correct timing, to the external program
	void play() {
		// just do nothing if there is no melody (pitches)
		if (!hasMelody)
			return;

		int vel = 100; //midi velocity -- TODO: change/assign if want to vary
		float cur_time = parent.millis(); //what time is it now?
		play = cur_time - last_time >= notems * rhythm_multiplier; //should we send the note now? based on prev. note's duration
		
		if (play)
			last_time = cur_time;

		//send a note off to previous note -- TODO: control note-offs on staccato or legato, etc. values
		if (note_index <= melody.size() && note_index > 0 && play) {
			outputMidiBus.sendNoteOff(0, (int) melody.get(note_index - 1), 0);
//			System.out.println("note off:" + (note_index - 1)); //TODO: comment out when not debugging or not needed

			// don't send anything else if done
			if (note_index == melody.size())
				note_index = 0;// ++; //cycle vs. stop at end ? TODO: create as an option
		}
		
		//send out next pitch, find next rhythm / duration
		if (note_index < melody.size() && note_index > -1 && play) {

			outputMidiBus.sendNoteOn(0, (int) melody.get(note_index), vel);
			System.out.println("note on:" + (int) melody.get(note_index)); //TODO: comment out when not debugging or not needed

			if (hasRhythm)
				rhythm_multiplier = rhythm.get(note_index);

			note_index++;
		}

	}

	//reset note to 0
	void reset() {
		note_index = 0;
	}

}
