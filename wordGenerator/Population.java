package wordGenerator;
import wordGenerator.Word;
import java.util.Random;

/*
* A Population is a collection of Words
* The goal is to generate the target word/phrase inside a Population
* Each generation a new Population is created, generating and mutating all Words in the previous generation's Population
* The Population keeps track of the fitness of each Word
* The Population uses the fitness to mutate Words closer to the target word/phrase
*/
public class Population {

	private char[] target; //The target word/phrase that will be generated
	private Word[] words; //The current population of generated words, one of which will eventually be the target 
	private float totalFitness = 0.0f; //The total fitness of the current population (used to calculate average fitness)
	private float averageFitness = 0.0f; //The average fitness of the current population
	private int topFitness = 0; //The fitness of the word with the highest fitness in the current population
	private int topFitnessIndex = 0; //The index value of the word with the highest fitness in the current population
	private int parentPickChance; //Random number between 0(inclusive) and target.length(exclusive)
	private Random r = new Random(); //Allows for random calculations
	
	
	//Constructors
	public Population(int size, char[] t) { //initialize population
		words = new Word[size];
		target = t;
		for(int i = 0; i < words.length; i++) { //fill the population with random words
			words[i] = new Word(target);
		}
	}
	
	public Population(Population p) { //clones a population
		words = new Word[p.getWords().length];
		target = p.getTarget();
		for(int i = 0; i < words.length; i++) { //sets each word in this population to be the same as the input population
			words[i] = new Word(p.getWords()[i]);
		}
	}
	
	//getters
	public Word[] getWords() {
		return words;
	}
	
	public char[] getTarget() {
		return target;
	}
	
	public float getAverageFitness() { //computes the average fitness of the population
		totalFitness = 0.0f;
		for(Word w : words) {
			totalFitness += w.computeFitness();
		}
		averageFitness = totalFitness / words.length; //average is the sum of the fitness of all the words divided by the number of words
		return averageFitness;
	}
	
	public int getTopFitness() { //returns the fitness of the word with the highest fitness
		topFitness = 0; //reset topFitness 
		for(Word w : words) {
			if(w.computeFitness() > topFitness) { //Linear search for highest fitness
				topFitness = w.computeFitness();
			}
		}
		return topFitness;
	}
	
	public Word getTopFitnessWord() { //returns the word with the highest fitness
		topFitnessIndex = 0; //reset topFitnessIndex
		for(int i = 0; i < words.length; i++) {
			if(words[i].computeFitness() > topFitness) { //same as getTopFitness, but saves the index as well
				topFitness = words[i].computeFitness();
				topFitnessIndex = i;
			}
		}
		return words[topFitnessIndex];
	}
	
	//methods
	public void add(Word word, int index) { //sets the given word to the given index in the population
		words[index] = new Word(word);
	}
	
	public Word getParent() { //select 1 "parent" using the fitness as the probability
		parentPickChance = r.nextInt(target.length); //generate a random number between 0(inclusive) and target.length(exclusive)
		for(Word w : words) {
			if (parentPickChance < w.computeFitness()) { //this makes it so that the higher the fitness, the more likely it is to be picked
				return w;
			}
		}
		return words[0]; //if all the words in the population have a fitness of 0, just return the first word in the population
	}
	
}
