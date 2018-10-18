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
import java.util.Random;

public class holaThis extends PApplet {
	PFont f; 
	MelodyPlayer player;
	MelodyPlayer generatedNotes;
	MidiFileToNotes midiNotes;
	PImage bg;
	
	public static void main(String[] args) {
	
	// TODO Auto-generated method stub
	PApplet.main("holaThis");

}
	public void settings() {
		
		size(800, 800);
		  // The background image must be the same size as the parameters
		  // into the size() method. In this program, the size of the image
		  // is 640 x 360 pixels.
		  //	bg = loadImage("music.pdg");
	}

	public void setup() {
		background(135,106,250);
		//background(bg);
		String filePath = getPath("/mid/MaryHadALittleLamb.mid"); 
		//String filePath = getPath("/mid/Love_Me_Tender.mid"); 
		//String filePath = getPath("/mid/la_cumparsita.mid");
		
		//playMidiFile(filePath); //THIS ACTUALLY PLAYS THE FILE

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
		float y = height/6;
		float w = width*5/7;
		float h = 80;
		//background(255,106,250);
		
		fill(0); 
		rect(x,y,w,h);
		fill(255); 
		text("UNIT TEST 1: Pressed key 'A' to see the transition table for the test melody",x+30,y+50);
		
		fill(0); 
		rect(x,2*y,w,h);
		fill(255);
		text("UNIT TEST 2: Pressed key 'B' to see the genrated melody notes",x+30,2*y+50);
		
		fill(0); 
		rect(x,3*y,w,h);
		fill(255);
		text("UNIT TEST 3-A: Pressed key 'C' to see the probabilities of pitches and rhythms from dataset order of 1-5",x+30,3*y+50);
		fill(255);
		
		fill(0); 
		rect(x,4*y,w,h);
		fill(255);
		text("UNIT TEST 3-B: Pressed key 'D' to see the probabilities of pitches and rhythms from dataset order of 5-10",x+30,4*y+50);
		fill(255);
		
		fill(0); 
		rect(x,5*y,w,h);
		fill(255);
		text("Pressed key 'P' to play new music ",x+30,5*y+50);
		fill(255);
		
		
		player.play();
		//some boolean that triggers this
		
	}
	
	public void playGenFile()
	{
		System.out.println("Playing crap");
		String filePath = getPath("/mid/la_cumparsita.mid");
		//gets all the midi notes from existing files
		MidiFileToNotes midiNotes2 = new MidiFileToNotes(filePath);

		// which line
		midiNotes2.setWhichLine(0);
		//create generator then train and generator
		int i = 1;
		//create pitches
		MarkovOrderOfM<Integer> train1 = new MarkovOrderOfM<Integer>();
		train1.train(midiNotes.pitches, i);
		Integer[] temp1 = {64, -2147483648, 74, -2147483648, 71, -2147483648, 68, -2147483648, 52, 64, 65, 64, 63, 64, 64, -2147483648, 76, -2147483648, 72};
		ArrayList<Integer> temp = new ArrayList();
		ArrayList<Integer> output = new ArrayList();
		ArrayList<Integer> t1 = new ArrayList(Arrays.asList(temp1));
		temp = train1.generate(t1, 40+i, i);
		for(int j = i; j<40+i; j++)
		{
			output.add(temp.get(j));
		}
		
		i = 1;
		//create rhythms:
		MarkovOrderOfM<Double> train11 = new MarkovOrderOfM<Double>();
		train11.train(midiNotes.rhythms, i);
		Double[] temp11 = {0.546875, 0.453125, 0.546875, 0.453125, 0.546875, 0.453125, 0.546875, 0.453125, 0.546875, 0.5, 0.5, 0.5, 1.0, 0.8359375, 0.546875, 0.453125};
		ArrayList<Double> tempR = new ArrayList();
		ArrayList<Double> outputR = new ArrayList();
		ArrayList<Double> tR = new ArrayList(Arrays.asList(tempR));
		tempR = train11.generate(tR, 40+i, i);
		for(int j = i; j<40+i; j++)
		{
			outputR.add(tempR.get(j));
		}
		
		//midiNotes.processPitchesAsTokens();
		player = new MelodyPlayer(this, 100.0f);
		player.setup();
		System.out.print(output);
		System.out.println();
		System.out.print(outputR);
		player.setMelody(output);
		player.setRhythm(outputR);
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
			funcBB();
			funcBC();
		}

		if (key == 'c') {
			funcCA();	
			funcCB();	
		}
		
		//Test Function
		if (key == 'd') {
			funcCC();	
			funcCD();	
		}
		if(key == 'p') {
			playGenFile();
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
		for(int i = 1; i <= 10; i++)
		{
			MarkovOrderOfM<Double> rhythmsMarkov = new MarkovOrderOfM<Double>();
			System.out.println("ORDER OF "+i+": --------------------------------------------------------------------------------- "); 
			rhythmsMarkov.train(midiNotes.rhythms, i);
			System.out.println(""); 
			rhythmsMarkov.printProbabilitiesTable(i);
			System.out.println(""); 
		}
	}
	
	//Print out the Unit test 2 function
		//Function B will print out the next pitch and rhythm from Marov Chain order of 1 to 10
	public void funcBB()
	{
		System.out.println("---------------------PITCHES--------------------");
		for(int i = 1; i <= 10; i ++)
		{
			MarkovOrderOfM<Integer> train1 = new MarkovOrderOfM<Integer>();
			train1.train(midiNotes.pitches, i);
			Integer[] temp1 = {62, 60, 62, 64, 64, 64, 62, 62, 62, 64};
			ArrayList<Integer> temp = new ArrayList();
			ArrayList<Integer> output = new ArrayList();
			ArrayList<Integer> t1 = new ArrayList(Arrays.asList(temp1));
			temp = train1.generate(t1, 20+i, i);
			for(int j = i; j<20+i; j++)
			{
				output.add(temp.get(j));
			}
			System.out.println("Order of "+i+ "  Predicted pitches are: "+output);
		}
	}
	public void funcBC()
	{
		System.out.println("---------------------RHYTHMS--------------------");
		for(int i = 1; i <= 10; i++)
		{
			MarkovOrderOfM<Double> train11 = new MarkovOrderOfM<Double>();
			train11.train(midiNotes.rhythms, i);
			Double[] temp11 = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0};
			ArrayList<Double> temp = new ArrayList();
			ArrayList<Double> output = new ArrayList();
			ArrayList<Double> t11 = new ArrayList(Arrays.asList(temp11));
			temp = train11.generate(t11, 20+i, i);
			for(int j = i; j<20+i; j++)
			{
				output.add(temp.get(j));
			}
			System.out.println("Order of "+i+ "  Predicted rhythms are: "+output);
		}
	}
	
	
	//Ignore this function
	public void funcBA()
	{
		System.out.println();
		System.out.println("---------------------PITCHES--------------------");
		//order of 1:
		MarkovOrderOfM<Integer> train1 = new MarkovOrderOfM<Integer>();
		train1.train(midiNotes.pitches, 1);
		Integer[] temp1 = {67};
		ArrayList<Integer> t1 = new ArrayList(Arrays.asList(temp1));
		System.out.println("Order of "+1+": Input note is " + t1 + "  Predicted note is: "+train1.generateNote(t1));
		
		//order of 2:
		MarkovOrderOfM<Integer> train2 = new MarkovOrderOfM<Integer>();
		train2.train(midiNotes.pitches, 2);
		Integer[] temp2 = {60,62};
		ArrayList<Integer> t2 = new ArrayList(Arrays.asList(temp2));
		System.out.println("Order of "+2+": Input note is " + t2 + "  Predicted note is: "+train2.generateNote(t2));
		
		//order of 3:
		MarkovOrderOfM<Integer> train3 = new MarkovOrderOfM<Integer>();
		train3.train(midiNotes.pitches, 3);
		Integer[] temp3 = {64, 62, 60};
		ArrayList<Integer> t3 = new ArrayList(Arrays.asList(temp3));
		System.out.println("Order of "+3+": Input note is " + t3 + "  Predicted note is: "+train3.generateNote(t3));
		
		//order of 4:
		MarkovOrderOfM<Integer> train4 = new MarkovOrderOfM<Integer>();
		train4.train(midiNotes.pitches, 4);
		Integer[] temp4 = {64, 62, 60, 62};
		ArrayList<Integer> t4 = new ArrayList(Arrays.asList(temp4));
		System.out.println("Order of "+4+": Input note is " + t4 + "  Predicted note is: "+train4.generateNote(t4));
				
		//order of 5:
		MarkovOrderOfM<Integer> train5 = new MarkovOrderOfM<Integer>();
		train5.train(midiNotes.pitches, 5);
		Integer[] temp5 = {64, 62, 60, 62, 64};
		ArrayList<Integer> t5 = new ArrayList(Arrays.asList(temp5));
		System.out.println("Order of "+5+": Input note is " + t5 + "  Predicted note is: "+train5.generateNote(t5));
				
		//order of 6:
		MarkovOrderOfM<Integer> train6 = new MarkovOrderOfM<Integer>();
		train6.train(midiNotes.pitches, 6);
		Integer[] temp6 = {64, 62, 60, 62, 64, 64};
		ArrayList<Integer> t6 = new ArrayList(Arrays.asList(temp6));
		System.out.println("Order of "+6+": Input note is " + t6 + "  Predicted note is: "+train6.generateNote(t6));
				
		//order of 7:
		MarkovOrderOfM<Integer> train7 = new MarkovOrderOfM<Integer>();
		train7.train(midiNotes.pitches, 7);
		Integer[] temp7 = {64, 62, 60, 62, 64, 64, 64};
		ArrayList<Integer> t7 = new ArrayList(Arrays.asList(temp7));
		System.out.println("Order of "+7+": Input note is " + t7 + "  Predicted note is: "+train7.generateNote(t7));
				
		//order of 8:
		MarkovOrderOfM<Integer> train8 = new MarkovOrderOfM<Integer>();
		train8.train(midiNotes.pitches, 8);
		Integer[] temp8 = {64, 62, 60, 62, 64, 64, 64, 62};
		ArrayList<Integer> t8 = new ArrayList(Arrays.asList(temp8));
		System.out.println("Order of "+8+": Input note is " + t8 + "  Predicted note is: "+train8.generateNote(t8));
				
		//order of 9:
		MarkovOrderOfM<Integer> train9 = new MarkovOrderOfM<Integer>();
		train9.train(midiNotes.pitches, 9);
		Integer[] temp9 = {64, 62, 60, 62, 64, 64, 64, 62, 62};
		ArrayList<Integer> t9 = new ArrayList(Arrays.asList(temp9));
		System.out.println("Order of "+9+": Input note is " + t9 + "  Predicted note is: "+train9.generateNote(t9));
				
		//order of 10:
		MarkovOrderOfM<Integer> train10 = new MarkovOrderOfM<Integer>();
		train10.train(midiNotes.pitches, 10);
		Integer[] temp10 = {62, 60, 62, 64, 64, 64, 62, 62, 62, 64};
		ArrayList<Integer> t10 = new ArrayList(Arrays.asList(temp10));
		System.out.println("Order of "+10+": Input note is " + t10 + "  Predicted note is: "+train10.generateNote(t10));
		
		System.out.println();
		System.out.println("---------------------RHYTHMS--------------------");
		
		//order of 1:
		MarkovOrderOfM<Double> train11 = new MarkovOrderOfM<Double>();
		train11.train(midiNotes.rhythms, 1);
		Double[] temp11 = {1.0};
		ArrayList<Double> t11 = new ArrayList(Arrays.asList(temp11));
		System.out.println("Order of "+1+": Input note is " + t11 + "  Predicted note is: "+train11.generateNote(t11));
		
		//order of 2:
		MarkovOrderOfM<Double> train12 = new MarkovOrderOfM<Double>();
		train12.train(midiNotes.rhythms, 2);
		Double[] temp12 = {1.0};
		ArrayList<Double> t12 = new ArrayList(Arrays.asList(temp12));
		System.out.println("Order of "+2+": Input note is " + t12 + "  Predicted note is: "+train11.generateNote(t12));
		
		//order of 3:
		MarkovOrderOfM<Double> train13 = new MarkovOrderOfM<Double>();
		train13.train(midiNotes.rhythms, 3);
		Double[] temp13 = {1.0,1.0, 1.0};
		ArrayList<Double> t13 = new ArrayList(Arrays.asList(temp13));
		System.out.println("Order of "+3+": Input note is " + t13 + "  Predicted note is: "+train13.generateNote(t13));
		
		//order of 4:
		MarkovOrderOfM<Double> train14 = new MarkovOrderOfM<Double>();
		train14.train(midiNotes.rhythms, 4);
		Double[] temp14 = {1.0, 1.0, 1.0, 1.0};
		ArrayList<Double> t14 = new ArrayList(Arrays.asList(temp14));
		System.out.println("Order of "+4+": Input note is " + t14 + "  Predicted note is: "+train14.generateNote(t14));
		
		//order of 5:
		MarkovOrderOfM<Double> train15 = new MarkovOrderOfM<Double>();
		train15.train(midiNotes.rhythms, 5);
		Double[] temp15 = {1.0, 1.0, 1.0, 1.0, 1.0};
		ArrayList<Double> t15 = new ArrayList(Arrays.asList(temp15));
		System.out.println("Order of "+5+": Input note is " + t15 + "  Predicted note is: "+train15.generateNote(t15));
		
		//order of 6:
		MarkovOrderOfM<Double> train16 = new MarkovOrderOfM<Double>();
		train16.train(midiNotes.rhythms, 6);
		Double[] temp16 = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		ArrayList<Double> t16 = new ArrayList(Arrays.asList(temp16));
		System.out.println("Order of "+6+": Input note is " + t16 + "  Predicted note is: "+train16.generateNote(t16));
		
		//order of 7:
		MarkovOrderOfM<Double> train17 = new MarkovOrderOfM<Double>();
		train17.train(midiNotes.rhythms, 7);
		Double[] temp17 = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		ArrayList<Double> t17 = new ArrayList(Arrays.asList(temp17));
		System.out.println("Order of "+7+": Input note is " + t17 + "  Predicted note is: "+train16.generateNote(t17));
				
		//order of 8:
		MarkovOrderOfM<Double> train18 = new MarkovOrderOfM<Double>();
		train18.train(midiNotes.rhythms, 8);
		Double[] temp18 = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		ArrayList<Double> t18 = new ArrayList(Arrays.asList(temp18));
		System.out.println("Order of "+8+": Input note is " + t18 + "  Predicted note is: "+train16.generateNote(t18));
		
		//order of 9:
		MarkovOrderOfM<Double> train19 = new MarkovOrderOfM<Double>();
		train19.train(midiNotes.rhythms, 9);
		Double[] temp19 = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0};
		ArrayList<Double> t19 = new ArrayList(Arrays.asList(temp19));
		System.out.println("Order of "+9+": Input note is " + t19 + "  Predicted note is: "+train19.generateNote(t19));
				
		//order of 10:
		MarkovOrderOfM<Double> train20 = new MarkovOrderOfM<Double>();
		train20.train(midiNotes.rhythms, 10);
		Double[] temp20 = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0};
		ArrayList<Double> t20 = new ArrayList(Arrays.asList(temp20));
		System.out.println("Order of "+10+": Input note is " + t20 + "  Predicted note is: "+train20.generateNote(t20));
	}

		
	
	//Function C (A and B) will generates 10,000 melodies of length 20, then prints out to the console the conditional probabilities of each pitch and rhythm from that dataset
	public void funcCA()
	{
		System.out.println();
		System.out.println("UNIT TEST 3");
		System.out.println("---------------------PITCHES--------------------");
		for(int order = 1; order <= 5; order ++ )
		{
			//MasterPitch will store each order of 10,000 melodies of length 20
			MarkovOrderOfM<Integer> masterPitch = new MarkovOrderOfM<Integer>();
			//temp array contains the initial elements for the generation
			Integer[] temp = new Integer[order];
			Integer[] arr = {64, 62, 60, 62, 64, 64, 64, 62, 62, 62, 64, 67, 67, 64, 62, 60, 62, 64, 64, 64, 64, 62, 62, 64, 62, 60, -2147483648, 62, 64, 62, 60, 62, 64, 64, 64, 62, 62, 62, 64, 67, 67, 64, 62, 60, 62, 64, 64, 64, 64, 62, 62, 64, 62, 60};
			ArrayList<Integer> all = new ArrayList();
			//for each order we provide first few elements for the generation
			MarkovOrderOfM<Integer> train = new MarkovOrderOfM<Integer>();
			train.train(midiNotes.pitches, order);
			int j = 0;
			//Loop through 10,000 times
			while(j<10000)
			{
				for (int x = 0; x < order; x++)
				{
					Random rand = new Random();
					//nextInt as provided by Random is exclusive of the top value so you need to add 1 
					int randomNum = rand.nextInt((53 - 0) + 1) + 0;
					temp[x] = arr[randomNum];
				}
				ArrayList<Integer> t = new ArrayList(Arrays.asList(temp));
				//we generate melodies length of 20 for 10000 times
				ArrayList<Integer> a = train.generate(t, 20, order);
				//we use the generated melodies for training in masterPitches
				all.addAll(a);
				j++;
			}
			System.out.println();
			System.out.println("Order of "+order);
			masterPitch.train(all, order);
			masterPitch.printProbabilitiesTable(order);
		}
		
	}
	//Unit Test 3: Rhythms
	public void funcCB()
	{
			System.out.println();
			System.out.println("---------------------RHYTHMS--------------------");
			for(int order = 1; order <= 5; order ++ )
			{
				//MasterPitch will store each order of 10,000 melodies of length 20
				MarkovOrderOfM<Double> masterRhythms = new MarkovOrderOfM<Double>();
				//temp array contains the initial elements for the generation
				Double[] temp = new Double[order];
				Double[] arr = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.5, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.5, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 4.0};
				ArrayList<Double> all = new ArrayList();
				//for each order we provide first few elements for the generation
				for (int i = 0; i < order; i++)
				{
					temp[i] = arr[i];
				}
					MarkovOrderOfM<Double> train = new MarkovOrderOfM<Double>();
					train.train(midiNotes.rhythms, order);
					int j = 0;
					//Loop through 10,000 times
					while(j<10000)
					{
						
						ArrayList<Double> t = new ArrayList(Arrays.asList(temp));
						//we generate melodies length of 20 for 10000 times
						ArrayList<Double> a = train.generate(t, 20, order);
						//we use the generated melodies for training in masterRhythms
						all.addAll(a);
						j++;
					}
					System.out.println();
					System.out.println("Order of "+order);
					masterRhythms.train(all, order);
					masterRhythms.printProbabilitiesTable(order);
			}
	}
	
	public void funcCC()
	{
		System.out.println();
		System.out.println("UNIT TEST 3");
		System.out.println("---------------------PITCHES--------------------");
		for(int order = 5; order <= 10; order ++ )
		{
			//MasterPitch will store each order of 10,000 melodies of length 20
			MarkovOrderOfM<Integer> masterPitch = new MarkovOrderOfM<Integer>();
			//temp array contains the initial elements for the generation
			Integer[] temp = new Integer[order];
			Integer[] arr = {64, 62, 60, 62, 64, 64, 64, 62, 62, 62, 64, 67, 67, 64, 62, 60, 62, 64, 64, 64, 64, 62, 62, 64, 62, 60, -2147483648, 62, 64, 62, 60, 62, 64, 64, 64, 62, 62, 62, 64, 67, 67, 64, 62, 60, 62, 64, 64, 64, 64, 62, 62, 64, 62, 60};
			ArrayList<Integer> all = new ArrayList();
			//for each order we provide first few elements for the generation
			MarkovOrderOfM<Integer> train = new MarkovOrderOfM<Integer>();
			train.train(midiNotes.pitches, order);
			int j = 0;
			//Loop through 10,000 times
			while(j<10000)
			{
				for (int x = 0; x < order; x++)
				{
					Random rand = new Random();
					//nextInt as provided by Random is exclusive of the top value so you need to add 1 
					int randomNum = rand.nextInt((53 - 0) + 1) + 0;
					temp[x] = arr[randomNum];
				}
				ArrayList<Integer> t = new ArrayList(Arrays.asList(temp));
				//we generate melodies length of 20 for 10000 times
				ArrayList<Integer> a = train.generate(t, 20, order);
				//we use the generated melodies for training in masterPitches
				all.addAll(a);
				j++;
			}
			System.out.println();
			System.out.println("Order of "+order);
			masterPitch.train(all, order);
			masterPitch.printProbabilitiesTable(order);
		}
	}
	
	public void funcCD()
	{
		System.out.println();
		System.out.println("---------------------RHYTHMS--------------------");
		for(int order = 5; order <= 10; order ++ )
		{
			//MasterPitch will store each order of 10,000 melodies of length 20
			MarkovOrderOfM<Double> masterRhythms = new MarkovOrderOfM<Double>();
			//temp array contains the initial elements for the generation
			Double[] temp = new Double[order];
			Double[] arr = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.5, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.5, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 4.0};
			ArrayList<Double> all = new ArrayList();
			//for each order we provide first few elements for the generation
			for (int i = 0; i < order; i++)
			{
				temp[i] = arr[i];
			}
				MarkovOrderOfM<Double> train = new MarkovOrderOfM<Double>();
				train.train(midiNotes.rhythms, order);
				int j = 0;
				//Loop through 10,000 times
				while(j<10000)
				{
					
					ArrayList<Double> t = new ArrayList(Arrays.asList(temp));
					//we generate melodies length of 20 for 10000 times
					ArrayList<Double> a = train.generate(t, 20, order);
					//we use the generated melodies for training in masterRhythms
					all.addAll(a);
					j++;
				}
				System.out.println();
				System.out.println("Order of "+order);
				masterRhythms.train(all, order);
				masterRhythms.printProbabilitiesTable(order);
		}
	}
	
	//Test function....Plz ignore
	public void funcT()
	{
		int i = 1;
		MarkovOrderOfM<Integer> train = new MarkovOrderOfM<Integer>();
		System.out.println("ORDER OF "+ i +": --------------------------------------------------------------------------------- "); 
		train.train(midiNotes.pitches, i);
		//train.printTransitionTable(i);
		System.out.println(" "); 
		train.printProbabilitiesTable(i);
		
		
		System.out.println(""); 
		System.out.println("Machine Learning Part: "+ "--------------------------------------------------------------------------------- "); 

		
		int order = 1;
		MarkovOrderOfM<Integer> masterPitch = new MarkovOrderOfM<Integer>();
		//temp array contains the initial elements for the generation
		Integer[] temp = new Integer[order];
		Integer[] arr = {64, 62, 60, 62, 64, 64, 64, 62, 62, 62, 64, 67, 67, 64, 62, 60, 62, 64, 64, 64, 64, 62, 62, 64, 62, 60, -2147483648, 62, 64, 62, 60, 62, 64, 64, 64, 62, 62, 62, 64, 67, 67, 64, 62, 60, 62, 64, 64, 64, 64, 62, 62, 64, 62, 60};
		//for each order we provide first few elements for the generation
		
		MarkovOrderOfM<Integer> train2 = new MarkovOrderOfM<Integer>();
		train2.train(midiNotes.pitches, order);
		//no problem with train2
		int loop = 0;
		ArrayList<Integer> all = new ArrayList();
		//Loop through 10,000 times
		while(loop<10000)
		{
			for (int j = 0; j < order; j++)
			{
				Random rand = new Random();
				//nextInt as provided by Random is exclusive of the top value so you need to add 1 
				int randomNum = rand.nextInt((53 - 0) + 1) + 0;
				temp[j] = arr[randomNum];
			}
			
			ArrayList<Integer> t = new ArrayList(Arrays.asList(temp));
			//we generate melodies length of 20 for 10000 times
			ArrayList<Integer> a = train2.generate(t, 20, order);
			//we use the generated melodies for training in masterPitches
			all.addAll(a);
			loop++;
		}
		masterPitch.train(all, order);
		masterPitch.printProbabilitiesTable(order);
		
		
		
		
	}
}

