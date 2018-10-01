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
		System.out.println("Pitches: ");
		System.out.println("Array: " + elements);
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
		System.out.println("Instances: " + noteInstances);
		
		//find percentages
		int total = 0; 
		for (int i = 0; i < noteInstances.size(); i++) {
			total = total + noteInstances.get(i);
		}
		for (int i = 0; i < noteInstances.size(); i++) {
			noteProbabilities.add((float)noteInstances.get(i)/total);
		}
		System.out.println("Elements " + alphabet);
		System.out.println("Probabilities: " + noteProbabilities);
	}
	
	void trainRhythms(ArrayList<Double> elements) {
		System.out.println(" ");
		System.out.println("Rhymes: ");
		//tally up the rhythms
		System.out.println("Array: " + elements);
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
		System.out.println("Elements: " + rhythms);
		System.out.println("Instances: " + rhythmInstances);
		
		//find percentages
		int total = 0; 
		for (int i = 0; i < rhythmInstances.size(); i++) {
			total = total + rhythmInstances.get(i);
		}
		for (int i = 0; i < rhythmInstances.size(); i++) {
			rhythmProbabilities.add((float)rhythmInstances.get(i)/total);
		}
		System.out.println("Probabilities: " + rhythmProbabilities);
	}
	
	void generateNotes() {
		double numGen = Math.random(); //generate a random number
		//use to generate a note -- midi value or rhythm value
		if (0 <= numGen && numGen <= 0.20) {
			generatedNotes.add(64);
		} else if (0.20 < numGen && numGen <= 0.40) {
			generatedNotes.add(62);
		} else if (0.40 < numGen && numGen <= 0.60) {
			generatedNotes.add(60);
		} else if (0.60 < numGen && numGen <= 0.80) {
			generatedNotes.add(-2147483648);
		} else if (0.80 < numGen && numGen <= 1.00) {
			generatedNotes.add(67);
		}
	}
	
	void generateRhythms() {
		double numGen = Math.random();
		if (0 <= numGen && numGen <= 0.20) {
			generatedRhythms.add(1.0);
		} else if (0.20 < numGen && numGen <= 0.40) {
			generatedRhythms.add(2.0);
		} else if (0.40 < numGen && numGen <= 0.60) {
			generatedRhythms.add(1.5);
		} else if (0.60 < numGen && numGen <= 0.80) {
			generatedRhythms.add(0.5);
		} else if (0.80 < numGen && numGen <= 1.00) {
			generatedRhythms.add(4.0);
		} 
	}
	
	
	ArrayList<Integer> generatedNotes() {
		return generatedNotes;
	}
	
	ArrayList<Double> generatedRhythms() {
		return generatedRhythms;
	}
}
