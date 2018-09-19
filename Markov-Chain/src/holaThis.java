//
//import java.net.URLDecoder;
//import java.util.ArrayList;
//import java.util.Random;
//
//import processing.core.*;
//import java.io.UnsupportedEncodingException;
//import jm.music.*;
//import jm.util.*;
//import java.io.*;
//import jm.music.data.Score;
//   
//public class holaThis extends PApplet 
//{
//	PFont f; 
//	MelodyPlayer player;
//	MidiFileToNotes midiNotes;
//	
//	//return one midi channel of the entire song
//	PredictMelody<Integer> pitchGenrator;
//	PredictMelody<Double> rhythmGenrator;
//	
//
//	public static void main(String[] args) {
//		
//		// TODO Auto-generated method stub
//		PApplet.main("holaThis");
//
//	}
//
//
//
//	public void settings()
//	{
//		
//		size(900,500);
//	}
//	
//	public void draw()
//	{		
//		float x = width/10;
//		float y = height/5;
//		float w = width*4/5;
//		float h = 80;
//		background(135,106,250);
//		fill(0);  
//		rect(x,y,w,h);
//		fill(255); 
//		text("Pressed key 'A' to see the probabilities of each detected note",x+30,y+50);
//		fill(0); 
//		rect(x,2*y,w,h);
//		fill(255);
//		text("Pressed key 'B' to see the pitches and rhythms of one melody",x+30,2*y+50);
//		fill(0); 
//		rect(x,3*y,w,h);
//		fill(255);
//		text("Pressed key 'C' to see the probabilities of pitches and rhythms from that dataset",x+30,3*y+50);
//		fill(255);
//	}
//	
//	public void keyPressed()
//	{
//		if(key == 'a')
//		{
//			println("Function 1: ");
//			ArrayList<Integer> pitch = midiNotes.getPitchArray();
//			ArrayList<Double> rhythm = midiNotes.getRhythmArray();
//			func1_a(pitch);
//			func1_b(rhythm);
//		}
//		if(key == 'b')
//		{
//			println("Function 2: ");
//			ArrayList<Integer> pitch = midiNotes.getPitchArray();
//			ArrayList<Double> rhythm = midiNotes.getRhythmArray();
//			func2_a(pitch);
//			func2_b(rhythm);
//		}
//		if(key == 'c')
//		{
//			println("Function 3: ");
//			ArrayList<Integer> pitch = midiNotes.getPitchArray();
//			ArrayList<Double> rhythm = midiNotes.getRhythmArray();
//			func3_a();
//			func3_b();
//		}
//	}
//	
//	public void setup()
//	{
//
//		String path = getPath("/mid/MaryHadALittleLamb.mid");
//		//String path = getPath("/mid/la_cumparsita.mid");
//		//Using the Processing library
//		println("path");
//		//playMidiFile(path);
//		
//		midiNotes = new MidiFileToNotes(path);
//		midiNotes.setWhichLine(0);	//Change which channel we are grabbing from
//		midiNotes.processPitchesAsTokens();
//		
//		player = new MelodyPlayer(this, 100.0f); //this point to itself
//		player.setup();
//		
//		player.setMelody(midiNotes.getPitchArray());
//		player.setRhythm(midiNotes.getRhythmArray());
//		System.out.println(midiNotes.getPitchArray());
//		System.out.println(midiNotes.getPitchArray().size());
//
//
//	}
//	
//	 void func1_a(ArrayList<Integer> cin)
//	{
//		pitchGenrator = new PredictMelody<Integer>();
//		pitchGenrator.reset();
//		pitchGenrator.setData(cin);
//		pitchGenrator.printProbability();
//	}
//	
//	 void func1_b(ArrayList<Double> cin)
//	{
//		rhythmGenrator = new PredictMelody<Double>();
//		rhythmGenrator.reset();
//		rhythmGenrator.setData(cin);
//		rhythmGenrator.printProbability();
//	}
//	
//	 
//	 //Have a function that prints out to the console the pitches and rhythms of one melody.
//	 void func2_a(ArrayList<Integer> cin)
//	 {
//		 pitchGenrator = new PredictMelody<Integer>();
//		 pitchGenrator.reset();
//		 pitchGenrator.setData(cin);
//		 pitchGenrator.printOneMelody();
//	 }
//	 
//	 void func2_b(ArrayList<Double> cin)
//	 {
//		 rhythmGenrator = new PredictMelody<Double>();
//		 rhythmGenrator.reset();
//		 rhythmGenrator.setData(cin);
//		 rhythmGenrator.printOneMelody();
//	 }
//	 
//	 
//	 //++Have a function which generates 10,000 melodies of length 20, 
//	 //then prints out to the console the probabilities of pitches and rhythms from that dataset. 
//	 void func3_a()
//	 {
//		 pitchGenrator = new PredictMelody<Integer>();
//		 pitchGenrator.genrateRandomPitch();
//		 
//		 
//	 }
//	 
//	 void func3_b()
//	 {
//		 rhythmGenrator = new PredictMelody<Double>();
//		 rhythmGenrator.genrateRandomRhythm();
//		 
//		 
//	 }
//	
//	public void playMidiFile(String filename)
//	{
//		Score score = new Score("Temp Score");
//		Read.midi(score,filename);
//		Play.midi(score);
//	}
//	
//	//Find the path to the import file
//	String getPath(String filename)
//	{
//		String filepath = "";
//		try
//		{
//			filepath = URLDecoder.decode(getClass().getResource(filename).getPath(), "UTF-8");
//		}catch (UnsupportedEncodingException e)
//		{
//			e.printStackTrace();
//		}
//		return filepath;
//	}
//
//}

import processing.core.*;

import jm.music.data.*;
import jm.JMC;
import jm.util.*;
import jm.midi.*;

import java.io.UnsupportedEncodingException;
import java.net.*;

public class holaThis extends PApplet {
	PFont f; 
	MelodyPlayer player;
	MelodyPlayer generatedNotes;
	MidiFileToNotes midiNotes;
	public static void main(String[] args) {
	
	// TODO Auto-generated method stub
	PApplet.main("holaThis");

}
	public void settings() {
		size(900, 500);
	}

	public void setup() {
		background(135,106,250);
		String filePath = getPath("/mid/MaryHadALittleLamb.mid"); 
		//String filePath = getPath("/mid/la_cumparsita.mid"); // returns a url
		playMidiFile(filePath); //THIS ACTUALLY PLAYS THE FILE

		//gets all the midi notes from existing files
		midiNotes = new MidiFileToNotes(filePath);

		// which line
		midiNotes.setWhichLine(0);
		//midiNotes.processPitchesAsTokens();
		player = new MelodyPlayer(this, 100.0f);

		player.setup();
		player.setMelody(midiNotes.getPitchArray());
		player.setRhythm(midiNotes.getRhythmArray());
		

//Markov calculations
		MarkovGenerator<Integer> notesMarkov = new MarkovGenerator<Integer>();
		notesMarkov.markovCalcs(midiNotes.pitches);
		
		for (int i = 0; i < midiNotes.pitches.size(); i++) {
			notesMarkov.generateMarkov(60);
		}
		System.out.println("Generated MarkovNotes: " + notesMarkov.generatedMarkov);
		MarkovGenerator<Double> rhythmsMarkov = new MarkovGenerator<Double>();
		for (int i = 0; i < midiNotes.rhythms.size(); i++) {
			midiNotes.rhythms.set(i, ((float)Math.round(midiNotes.rhythms.get(i) * 100.0) / 100.0));
		}
		System.out.println("New Rhythms" + midiNotes.rhythms);
		rhythmsMarkov.markovCalcs(midiNotes.rhythms);
		for (int i = 0; i < midiNotes.pitches.size(); i++) {
			rhythmsMarkov.generateMarkov(1.5);
		}
		System.out.println("Generated Markov Rhythms: " + rhythmsMarkov.generatedMarkov);
	




//probability calculations
//		PredictMelody<Integer> myGenerator = new PredictMelody<Integer>();
//		myGenerator.trainPitches(midiNotes.pitches);
//		myGenerator.trainRhythms(midiNotes.rhythms);

//		for (int i = 0; i < midiNotes.pitches.size(); i++) {
//			myGenerator.generateNotes();
//		}
//		for (int i = 0; i < midiNotes.rhythms.size(); i++) {
//			myGenerator.generateRhythms();
//		}
//
//			System.out.println("Generated Notes by probability: " + myGenerator.generatedNotes);
//			System.out.println("Generated Rhythms by probability: " + myGenerator.generatedRhythms);
		}

	public void draw() {
		float x = width/10;
		float y = height/5;
		float w = width*4/5;
		float h = 80;
		background(135,106,250);
		fill(0);  
		rect(x,y,w,h);
		fill(255); 
		text("Pressed key 'A' to see the probabilities of each detected note",x+30,y+50);
		fill(0); 
		rect(x,2*y,w,h);
		fill(255);
		text("Pressed key 'B' to see the pitches and rhythms of one melody",x+30,2*y+50);
		fill(0); 
		rect(x,3*y,w,h);
		fill(255);
		text("Pressed key 'C' to see the probabilities of pitches and rhythms from that dataset",x+30,3*y+50);
		fill(255);
		player.play();
	}

	String getPath(String path) { //accesses resources
		String filePath = "";
		try {
			filePath = URLDecoder.decode(getClass().getResource(path).getPath(), "UTF-8"); //file name, decodes url
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath; //returns correct file path
	}

	void playMidiFile(String filename) { //using API
		Score theScore = new Score("Temp score");
		Read.midi(theScore, filename);
		Play.midi(theScore);
	}

	public void keyPressed() {
		if (key == ' ') {
			player.reset();
			println("Melody started!");
		}
	}
}

