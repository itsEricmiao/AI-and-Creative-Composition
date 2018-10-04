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
import java.util.ArrayList;
import java.util.Arrays;

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
		//String filePath = getPath("/mid/la_cumparsita.mid");
		
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
		
		
		
//probability calculations
		PredictMelody<Integer> myGenerator = new PredictMelody<Integer>();
		myGenerator.trainPitches(midiNotes.pitches);
		myGenerator.trainRhythms(midiNotes.rhythms);
		
		for (int i = 0; i < 10; i++) {
			myGenerator.generateNotes();
		}
		  
		for (int i = 0; i < 10; i++) {
			myGenerator.generateRhythms();
		}
		System.out.println("Generated Pitches by probability: " + myGenerator.generatedNotes);
		System.out.println("Generated Rhythms by probability: " + myGenerator.generatedRhythms);
		
		}	
		

	public void draw() {
		float x = width/10;
		float y = height/5;
		float w = width*4/5;
		float h = 80;
		background(255,106,250);
		fill(0); 
		rect(x,y,w,h);
		fill(255); 
		text("Pressed key 'A' to see the transition table for the test melody",x+30,y+50);
		fill(0); 
		rect(x,2*y,w,h);
		fill(255);
		text("Pressed key 'B' to see the pitches and rhythms of the melody",x+30,2*y+50);
		fill(0); 
		rect(x,3*y,w,h);
		fill(255);
		text("Pressed key 'C' to see the probabilities of pitches and rhythms from dataset",x+30,3*y+50);
		fill(255);
		//player.play();
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

//			player.reset();
//			println("Melody started!");

		if (key == 'a') {
			funcA();	
		}
		
		if (key == 'b') {
			funcB();	
		}
		
		if (key == 'c') {
			funcC();	
		}
	}
	
	//Print out the Unit test 1 function
	public void funcA()
	{
		System.out.println(" ");
		System.out.println("Pitches: ");
		for(int i = 1; i <= 10; i++)
		{
			MarkovOrderOfM<Integer> train = new MarkovOrderOfM<Integer>();
			System.out.println("ORDER OF "+ i +": --------------------------------------------------------------------------------- "); 
			train.train(midiNotes.pitches, i);
			//train.printTransitionTable();
			System.out.println(" "); 
			train.printProbabilitiesTable(i);
			System.out.println(""); 	
		}
		
		System.out.println(" "); 	
		System.out.println("Rhythms:");
		for(int i = 1; i <= 5; i++)
		{
			MarkovOrderOfM<Double> rhythmsMarkov = new MarkovOrderOfM<Double>();
			System.out.println("ORDER OF "+i+": --------------------------------------------------------------------------------- "); 
			rhythmsMarkov.train(midiNotes.rhythms, i);
			System.out.println(""); 
			//rhythmsMarkov.printTransitionTable();
			rhythmsMarkov.printProbabilitiesTable(i);
			System.out.println(""); 
		}
	}
	
	
	//Print out the Unit test 2 function
	public void funcB()
	{
		MarkovOrderOfM<Integer> train = new MarkovOrderOfM<Integer>();
		train.train(midiNotes.pitches, 3);
		Integer[] temp = {62,64,67};
		ArrayList<Integer> t = new ArrayList(Arrays.asList(temp));
//		for(int i = 0; i < 10000; i++)
//		{
			System.out.println("Input note is: [" + t+ "]  Predicted note is: ["+train.generateNote(t)+"]");
//		}
	}

		
	
	//Print out the Unit test 3 function
	public void funcC()
	{
		
//		MarkovOrderOfM masterPitch; 
//		PredictMelody train;
//		int j = 0;
//		for(j<1000000)
//		{
//			ArrayList arr = train.generate(initWord, 20);
//			masterPitch.train(arr);
//			j++;
//		}
	}


		
}

