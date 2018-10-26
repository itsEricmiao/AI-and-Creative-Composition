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
		unitTest1();
		unitTest2();
		unitTest3();
		unitTest4_Pitches();
		exit();
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

	public void unitTest2()
	{
		int order = 3;
		System.out.println("----------------------------Here is unit test 2------------------------------------------");
		System.out.println("String: acadaacbda");
		PSTNode<String> root = new PSTNode<String>(order);
		String[] str1 = {"a","c","a","d","a","a","b","d","a"};
		ArrayList<String> data = new ArrayList(Arrays.asList(str1));
		root.addToTree(data);
		root.print(3);
		System.out.println();

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
		root.print(3);
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
		root.print(3);
		System.out.println();

	}

	public void unitTest4_Pitches()
	{
		String filePath = getPath("/mid/Love_Me_Tender.mid"); 
		MidiFileToNotes midiNotes = new MidiFileToNotes(filePath);

		int order = 3;
		System.out.println("----------------------------Here is unit test 4 (Pitches)---------------------------------------------------");
		System.out.println("String: Mary Had a Little Lamb");
		PSTNode<Integer> root = new PSTNode<Integer>(order);
		root.addToTree(midiNotes.pitches);
		root.print(3);
		System.out.println();

	}
	
	public void unitTest4_Rhythms()
	{
		String filePath = getPath("/mid/Love_Me_Tender.mid"); 
		MidiFileToNotes midiNotes = new MidiFileToNotes(filePath);
		int order = 3;
		System.out.println("----------------------------Here is unit test 4 (Rhythms)---------------------------------------------------");
		System.out.println("String: Mary Had a Little Lamb");
		PSTNode<Double> root = new PSTNode<Double>(order);
		root.addToTree(midiNotes.rhythms);
		root.print(3);
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

