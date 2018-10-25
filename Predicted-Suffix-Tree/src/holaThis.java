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
	
	
	
	
	public static void main(String[] args) {
	
	// TODO Auto-generated method stub
	PApplet.main("holaThis");
	
	
	
	
	PSTNode<String> root = new<String> PSTNode();
	String[] str = {"a","b","r","a","c","a","d","a","b","r","a"};
	ArrayList<String> data = new ArrayList(Arrays.asList(str));
	ArrayList<String> temp = new ArrayList();
	ArrayList<ArrayList<String>> suffix = new ArrayList();
	int length = 2;
	
	for(int x = 0; x < data.size()-length; x++)
	{
		for(int y = x; y < x+length; y++)
		{
			temp.add(data.get(y));
		}
		System.out.println(temp);
		
		if(!suffix.contains(temp))
		{
			
			root.addChild(new PSTNode<ArrayList<String>>(temp));
			suffix.add(temp);
		}
		temp.clear();
	}
}
	
	
	public void settings() {
		size(900, 800);
	}

	public void setup() {
		background(135,106,250);
	}
		
		
//probability calculations
		
		

	public void draw() {
		
		float x = width/10;
		float y = height/8;
		float w = width*8/10;
		float h = 90;	
	}
	
	//This will play the music generated by the computer using order of 2
	
}

