//import java.util.ArrayList;
//import java.util.*;
//import java.util.Random;
//
//public class PredictMelody<E> {
//
//private ArrayList<E> melodies; //The array holds the each unique note in the dataset
//
//private ArrayList<Integer> probability;	//This array holds the number of notes repeat in the melodies
//	
//	void reset()
//	{
//		melodies = new ArrayList<E>(); //always create ArrayList object before using it
//		probability = new ArrayList<Integer>();
//	}
//	
//	void setData(ArrayList<E> input)
//	{
//		for (int i = 0; i < input.size(); i++)
//		{
//			int index = melodies.indexOf(input.get(i));
//			
//			if(index == -1)
//			{
//				melodies.add(input.get(i));
//				probability.add(0);
//				index = melodies.size()-1;
//			}
//			setProbability(index);
//		//System.out.println("Melodies array size = " + melodies.size());
//		}
//	}
//	
//	//Call this function if the note is found in the melodies arrayList
//	void setProbability(int index)
//	{
//		//Get the value of the index in probability arrayList, add 1 to the exsiting value and replace the old value
//		int val = probability.get(index);
//		val++;
//		probability.set(index, val);
//	}
//	
//
//	//Call this function if the note is NOT found in the melodies arrayList
//	void setNewToProbability()
//	{
//		probability.add(1);
//	}
//	
//	//function one
//	void printProbability()
//	{
//
//		for(int i = 0; i < melodies.size();i++)
//		{
//			System.out.println("The note is: " + getMelodies(i));
//			System.out.println("The probability of this note is: " + getProbability(i));
//		}
//	}
//	
//	//Getter for Melodies arrayList
//	 E getMelodies(int index)
//	{
//		return melodies.get(index);
//	}
//	
//	//Getter for Probability arrayList
//	 int getProbability(int index)
//	{
//		return probability.get(index);
//	}
//	
//	 void genrateRandomPitch()
//	 {
//		 
//		 Double num = genrate();
//		 System.out.println("The random number is " + num);
//	 }
//	 
//	Double genrate()
//	{
//		Random r = new Random();
//		double randomValue = 0 + (1 - 0) * r.nextDouble();
//		return randomValue;
//	}
//		
//	 void genrateRandomRhythm()
//	 {
//		 Double num = genrate();
//		 System.out.println("The random number is " + num);
//		 
//	 }
//	 
//	void printOneMelody()
//	{
//		System.out.println("The Melodies size is: " + melodies.size());
//		for(int i = 0; i < melodies.size();i++)
//		{
//			
//			System.out.println(getMelodies(i));
//		}
//		
//		System.out.println("The Melodies size is: " + probability.size());
//		for(int i = 0; i < probability.size();i++)
//		{
//			System.out.println(getProbability(i));
//		}
//	}
//		
//}




import java.util.*;

public class PredictMelody<E> {
	ArrayList<E> notesInFile;
	ArrayList<Integer> alphabet = new ArrayList<Integer>(); //contains note names (in midi)
	ArrayList<Double> rhythms = new ArrayList<Double>();
	
	ArrayList<Integer> noteInstances = new ArrayList<Integer>(); //contains the instances the note is found (in midi file), corresponds to index of alphabet array
	ArrayList<Integer> rhythmInstances = new ArrayList<Integer>();
	
	ArrayList<Float> noteProbabilities = new ArrayList<Float>(); //contains probabilities that the specific note occurs (noteInstances/the total)
	ArrayList<Float> rhythmProbabilities = new ArrayList<Float>();
	
	ArrayList<Integer> generatedNotes = new ArrayList<Integer>(); //arrayList of generated notes based on probabilities
	ArrayList<Double> generatedRhythms = new ArrayList<Double>();
	
	PredictMelody() {}
	
	void trainPitches(ArrayList<E> elements) {
		//tally up the notes
		System.out.println("(Pitch) Array: " + elements);
		for (int i = 0; i < elements.size(); i++) {
			if (alphabet.size() == 0) {
				alphabet.add(0, (int)elements.get(i));
				noteInstances.add(0, 1);
			} else {
				if (alphabet.contains(elements.get(i))) {
					noteInstances.set(alphabet.indexOf(elements.get(i)), noteInstances.get(alphabet.indexOf(elements.get(i))) + 1);
				} else {
					alphabet.add((int)elements.get(i));
					noteInstances.add(1);
				}
			}
		}
		//System.out.println("alphabet: " + alphabet);
		System.out.println("(Pitch) Instances: " + noteInstances);
		
		//find percentages
		int total = 0; 
		for (int i = 0; i < noteInstances.size(); i++) {
			total = total + noteInstances.get(i);
		}
		for (int i = 0; i < noteInstances.size(); i++) {
			noteProbabilities.add((float)noteInstances.get(i)/total);
		}
		System.out.println("(Pitch) Probabilities: " + noteProbabilities);
	}
	
	void trainRhythms(ArrayList<Double> elements) {
		//tally up the rhythms
		System.out.println("(Rhythm) Array: " + elements);
		for (int i = 0; i < elements.size(); i++) {
			if (rhythms.size() == 0) {
				rhythms.add(0, (double)elements.get(i));
				rhythmInstances.add(0, 1);
			} else {
				if (rhythms.contains(elements.get(i))) {
					rhythmInstances.set(rhythms.indexOf(elements.get(i)), rhythmInstances.get(rhythms.indexOf(elements.get(i))) + 1);
				} else {
					rhythms.add((double)elements.get(i));
					rhythmInstances.add(1);
				}
			}
		}
		System.out.println("Rhythms: " + rhythms);
		System.out.println("(Rhythm) Instances: " + rhythmInstances);
		
		//find percentages
		int total = 0; 
		for (int i = 0; i < rhythmInstances.size(); i++) {
			total = total + rhythmInstances.get(i);
		}
		for (int i = 0; i < rhythmInstances.size(); i++) {
			rhythmProbabilities.add((float)rhythmInstances.get(i)/total);
		}
		System.out.println("(Rhythm) Probabilities: " + rhythmProbabilities);
	}
	
	void generateNotes() {
		double numGen = Math.random(); //generate a random number
		//use to generate a note -- midi value or rhythm value
		if (0 <= numGen && numGen <= 0.19) {
			generatedNotes.add(69);
		} else if (0.19 < numGen && numGen <= 0.36) {
			generatedNotes.add(71);
		} else if (0.36 < numGen && numGen <= 0.46) {
			generatedNotes.add(-2147483648);
		} else if (0.46 < numGen && numGen <= 0.54) {
			generatedNotes.add(72);
		} else if (0.54 < numGen && numGen <= 0.61) { 
			generatedNotes.add(68);
		} else if (0.61 < numGen && numGen <= 0.66) {
			generatedNotes.add(64);
		} else if (0.66 < numGen && numGen <= 0.71) {
			generatedNotes.add(73); 
		} else if (0.71 < numGen && numGen <= 0.75) {
			generatedNotes.add(65);
		} else if (0.75 < numGen && numGen <= 0.78) {
			generatedNotes.add(76);
		} else if (0.78 < numGen && numGen <= 0.81) {
			generatedNotes.add(74);
		} else if (0.81 < numGen && numGen <= 0.84) {
			generatedNotes.add(62);
		} else if (0.84 < numGen && numGen <= 0.87) {
			generatedNotes.add(66);
		} else if (0.87 < numGen && numGen <= 0.90) {
			generatedNotes.add(61);
		} else if (0.90 < numGen && numGen <= 0.93) {
			generatedNotes.add(67);
		} else if (0.93 < numGen && numGen <= 0.95) {
			generatedNotes.add(63);
		} else if (0.95 < numGen && numGen <= 0.97) {
			generatedNotes.add(70);
		} else if (0.97 < numGen && numGen <= 0.98) {
			generatedNotes.add(81);
		} else if (0.98 < numGen && numGen <= 0.99) {
			generatedNotes.add(59);
		} else if (0.99 < numGen && numGen <= 1) {
			generatedNotes.add(78);
		}
	}
	
	void generateRhythms() {
		double numGen = Math.random();
		if (0 < numGen && numGen <= 0.45) {
			generatedRhythms.add(0.2265625);
		} else if (0.45 < numGen && numGen <= 0.47) {
			generatedRhythms.add(0.5234375);
		} else if (0.47 < numGen && numGen <= 0.49) {
			generatedRhythms.add(0.44921875);
		} else if (0.49 < numGen && numGen <= 0.53) {
			generatedRhythms.add(0.30078125);
		} else if (0.53 < numGen && numGen <= 0.55) {
			generatedRhythms.add(0.47265625);
		} else if (0.55 < numGen && numGen <= 0.56) {
			generatedRhythms.add(0.52734375);
		} else if (0.56 < numGen && numGen <= 0.57) {
			generatedRhythms.add(0.5);
		} else if (0.57 < numGen && numGen <= 0.59) {
			generatedRhythms.add(0.2421875);
		} else if (0.59 < numGen && numGen <= 0.61) {
			generatedRhythms.add(0.17578125);
		} else if (0.61 < numGen && numGen <= 0.615) {
			generatedRhythms.add(0.16796875);
		} else if (0.615 < numGen && numGen <=0.62) {
			generatedRhythms.add(0.48828125);
		} else if (0.62 < numGen && numGen <= 0.625) {
			generatedRhythms.add(0.51171875);
		} else if (0.625 < numGen && numGen <= 0.635) {
			generatedRhythms.add(0.20703125);
		} else if (0.635 < numGen && numGen <= 0.655) {
			generatedRhythms.add(0.171875);
		} else if (0.655 < numGen && numGen <= 0.66) {
			generatedRhythms.add(0.55078125);
		} else if (0.66 < numGen && numGen <= 0.665) {
			generatedRhythms.add(0.67578125);
		} else if (0.665 < numGen && numGen <= 0.675) {
			generatedRhythms.add(0.25390625);
		} else if (0.675 < numGen && numGen <= 0.68) {
			generatedRhythms.add(0.234375);
		} else if (0.68 < numGen && numGen <= 0.685) {
			generatedRhythms.add(0.375);
		} else if (0.685 < numGen && numGen <= 0.695) {
			generatedRhythms.add(0.8984375);
		} else if (0.695 < numGen && numGen <= 0.7) {
			generatedRhythms.add(2.02734375);
		} else if (0.7 < numGen && numGen <= 0.71) {
			generatedRhythms.add(0.4140625);
		} else if (0.71 < numGen && numGen <= 0.72) {
			generatedRhythms.add(0.9921875);
		} else if (0.73 < numGen && numGen <= 0.74) {
			generatedRhythms.add(1.10546875);
		} else if (0.74 < numGen && numGen <= 0.75) {
			generatedRhythms.add(0.39453125);
		} else if (0.75 < numGen && numGen <= 0.76) {
			generatedRhythms.add(0.3828125);
		} else if (0.76 < numGen && numGen <= 0.78) {
			generatedRhythms.add(0.328125);
		} else if (0.78 < numGen && numGen <= 0.79) {
			generatedRhythms.add(0.28515625);
		} else if (0.79 < numGen && numGen <= 0.8) {
			generatedRhythms.add(0.953125);
		} else if (0.8 < numGen && numGen <= 0.81) {
			generatedRhythms.add(0.90234375);
		} else if (0.81 < numGen && numGen <= 0.82) {
			generatedRhythms.add(0.59765625);
		} else if (0.82 < numGen && numGen <= 0.83) {
			generatedRhythms.add(0.31640625);
		} else if (0.83 < numGen && numGen <= 0.84) {
			generatedRhythms.add(0.3125);
		} else if (0.84 < numGen && numGen <= 0.85) {
			generatedRhythms.add(0.28125);
		} else if (0.85 < numGen && numGen <= 0.86) {
			generatedRhythms.add(0.14453125);
		} else if (0.86 < numGen && numGen <= 0.87) {
			generatedRhythms.add(0.609375);
		} else if (0.87 < numGen && numGen <= 0.88) {
			generatedRhythms.add(0.390625);
		} else if (0.88 < numGen && numGen <= 0.89) {
			generatedRhythms.add(0.2109375);
		} else if (0.89 < numGen && numGen <= 0.9) {
			generatedRhythms.add(0.23828125);
		} else if (0.9 < numGen && numGen <= 0.906) {
			generatedRhythms.add(0.37890625);
		} else if (0.906 < numGen && numGen <= 0.912) {
			generatedRhythms.add(0.203125);
		} else if (0.912 < numGen && numGen <= 0.922) {
			generatedRhythms.add(0.296875);
		} else if (0.922 < numGen && numGen <= 0.928) {
			generatedRhythms.add(0.1328125);
		} else if (0.928 < numGen && numGen <= 0.934) {
			generatedRhythms.add(0.6015625);
		} else if (0.934 < numGen && numGen <= 0.94) {
			generatedRhythms.add(0.75);
		} else if (0.94 < numGen && numGen <= 0.95) {
			generatedRhythms.add(0.25);
		} else {
			generatedRhythms.add(0.2265625);
		}
	}
	
//	void determineRanges(ArrayList<Float> probabilities, ArrayList<Double> rhythms) {
//		for (int i = 0; i < rhythms.size(); i++) {
//			
//		}
//	}
	
	ArrayList<Integer> generatedNotes() {
		return generatedNotes;
	}
	
	ArrayList<Double> generatedRhythms() {
		return generatedRhythms;
	}
}
