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
	MelodyPlayer player;
	MelodyPlayer generatedNotes;
	MidiFileToNotes midiNotes;


	//	Unit test 1.Suffix tree output for:  abracadabra
	//	Unit test 2.Suffix tree output for:  acadaacbda
	//	Unit test 3.Suffix tree output for:  abcccdaadcdaabcadad
	//	Unit test 4. Suffix tree output for: Mary Had a Little Lamb

	public static void main(String[] args) 
	{

		PApplet.main("holaThis");

	}
	
	public void draw() 
	{
		float x = width/10;
		float y = height/8;
		float w = width*8/10;
		float h = 90;
		
		fill(0); 
		rect(x,y,w,h);
		fill(255); 
		text("UNIT TEST 1: Press key 'A' to see the Suffix tree output for:  abracadabra",x+30,y+50);
		
		fill(0); 
		rect(x,2*y,w,h);
		fill(255);
		text("UNIT TEST 2: Press key 'B' to see the Suffix tree output for:  acadaacbda",x+30,2*y+50);
		
		fill(0); 
		rect(x,3*y,w,h);
		fill(255);
		text("UNIT TEST 3-A: Press key 'C' to see the Suffix tree output for:  abcccdaadcdaabcadad",x+30,3*y+50);
		fill(255);
		
		fill(0); 
		rect(x,4*y,w,h);
		fill(255);
		text("UNIT TEST 3-B: Press key 'D' to see Suffix tree output for: Mary Had a Little Lamb",x+30,4*y+50);
		fill(255);

	}
	
	public void keyPressed() {

//		player.reset();
//		println("Melody started!");
	if (key == 'a') {
		unitTest1();
	}
	
	if (key == 'b') {
		unitTest2();
	}

	if (key == 'c') {
		unitTest3();
	}
	
	//Test Function
	if (key == 'd') {
		unitTest4_Pitches();
		unitTest4_Rhythms();
	}
	
	//For testing purpose
	if (key == 't') {
		unitTest();
	}
}
	
	public void settings() 
	{
		size(900, 800);
		// The background image must be the same size as the parameters
		// into the size() method. In this program, the size of the image
		// is 640 x 360 pixels.
	   // bg = loadImage("music.pdg");
	}

	
	public void setup()
	{
		String filePath = getPath("/mid/MaryHadALittleLamb.mid"); 
		midiNotes = new MidiFileToNotes(filePath);

		// which line
		midiNotes.setWhichLine(0);
		//midiNotes.processPitchesAsTokens();
		player = new MelodyPlayer(this, 100.0f);

		player.setup();
		player.setMelody(midiNotes.getPitchArray());
		player.setRhythm(midiNotes.getRhythmArray());

	}

	public void unitTest()
	{
		int order = 3;
		double p_Num = 0.2;
		
		System.out.println("----------------------------Here is unit test 1---------------------------------------------------");
		System.out.println("String: abracadabra");
		PSTNode<String> root = new PSTNode<String>(order);
		String[] str1 = {"a","b","r","a","c","a","d","a","b","r","a"};
		ArrayList<String> data = new ArrayList<String>(Arrays.asList(str1));
		
		PSTTree<String> test = new PSTTree<String>();
		//root.setRoot(root);
		root.addToTree(data);
		root.p_elemination(p_Num, root);
		//test.printAll();
		root.print(1);
		
	}
	
	
	public void unitTest1()
	{
		int order = 3;
		System.out.println("----------------------------Here is unit test 1---------------------------------------------------");
		System.out.println("String: abracadabra");
		PSTNode<String> root = new PSTNode<String>(order);
		String[] str1 = {"a","b","r","a","c","a","d","a","b","r","a"};
		ArrayList<String> data = new ArrayList(Arrays.asList(str1));
		root.addToTree(data);
		root.print(1);
		System.out.println();

	}

	
	public void unitTest2()
	{
		int order = 3;
		System.out.println("----------------------------Here is unit test 2------------------------------------------");
		System.out.println("String: acadaacbda");
		PSTNode<String> root = new PSTNode<String>(order);
		String[] str1 = {"a","c","a","d","a","a","b","d","a"};
		ArrayList<String> data = new ArrayList(Arrays.asList(str1));
		root.addToTree(data);
		root.print(1);
		System.out.println();

	}
	
	
	public void unitTest3()
	{
		int order = 3;
		System.out.println("----------------------------Here is unit test 3---------------------------------------------------");
		System.out.println("String: abcccdaadcdaabcadad");
		PSTNode<String> root = new PSTNode<String>(order);
		String[] str1 = {"a","b","c","c","c","d","a","a","d","c","d","a","a","b","c","a","d","a","d"};
		ArrayList<String> data = new ArrayList(Arrays.asList(str1));
		root.addToTree(data);
		root.print(1);
		System.out.println();

	}

	public void unitTest4_Pitches()
	{
		String filePath = getPath("/mid/MaryHadALittleLamb.mid"); 
		MidiFileToNotes midiNotes = new MidiFileToNotes(filePath);

		int order = 3;
		System.out.println("----------------------------Here is unit test 4 (Pitches)---------------------------------------------------");
		System.out.println("String: Mary Had a Little Lamb");
		PSTNode<Integer> root = new PSTNode<Integer>(order);
		root.addToTree(midiNotes.pitches);
		root.print(1);
		System.out.println();

	}
	
	public void unitTest4_Rhythms()
	{
		String filePath = getPath("/mid/MaryHadALittleLamb.mid"); 
		MidiFileToNotes midiNotes = new MidiFileToNotes(filePath);
		int order = 3;
		System.out.println("----------------------------Here is unit test 4 (Rhythms)---------------------------------------------------");
		System.out.println("String: Mary Had a Little Lamb");
		PSTNode<Double> root = new PSTNode<Double>(order);
		root.addToTree(midiNotes.rhythms);
		root.print(1);
		System.out.println();

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

}

