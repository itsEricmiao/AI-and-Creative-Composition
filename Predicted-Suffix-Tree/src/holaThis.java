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
	
	public static void main(String[] args) {
	PSTNode<String> root = new PSTNode<String>(4);
	String[] str = {"a","b","r","a","c","a","d","a","b","r","a"};
	ArrayList<String> data = new ArrayList(Arrays.asList(str));
	root.addToTree(data);
	root.print(1);

}

	
}

