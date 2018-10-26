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

public class holaThis {
	MelodyPlayer player;
	MelodyPlayer generatedNotes;
	MidiFileToNotes midiNotes;
	
	
//	Unit test 1.Suffix tree output for:  abracadabra
//	Unit test 2.Suffix tree output for:  acadaacbda
//	Unit test 3.Suffix tree output for:  abcccdaadcdaabcadad
//	Unit test 4. Suffix tree output for: Mary Had a Little Lamb
	
	public static void main(String[] args) {
	unitTest1();
	unitTest2();
	unitTest3();

}

	public static void unitTest2()
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
	
	public static void unitTest1()
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
	
	public static void unitTest3()
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
}

