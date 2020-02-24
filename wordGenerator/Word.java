package wordGenerator;

import java.util.Random;

/*
 * Each Word is represented as a character array
 * If a character in the word is the same and in the same spot as in the target, it is worth 1 fitness 
 * Fitness for each word is calculated by summing up each character's fitness
 * Example:
 * target = cat
 * word = cac
 * Fitness = 2 (1 for the c and 1 for the a and 0 for the second c)
*/

public class Word {

	private char[] genes; //the actual Word represented as a character array
	private char[] target; //the target Word to generate represented as a character array
	private int rand; //used for selection and mutation
	private int fitness = 0; //How "fit" the word is i.e. how close the Word is to the target Word
	private Random r = new Random(); //used for selection and mutation
	
	//Constructors
	public Word(Word w) { //duplicate word
		target = w.getTarget();
		genes = new char[target.length];
		for(int i = 0; i < genes.length; i++) {
			genes[i] = w.getWordArray()[i];
		}
	}
	
	public Word(char[] t) {
		this.target = t;
		genes = new char[target.length];
		//generate genes (letters of the word)
		for(int i = 0; i < genes.length; i++) {
			//generates a number between 65 and 122
			rand = r.nextInt(95) + 32; //generates numbers between 0(inclusive) and 95(exclusive) plus 32 meaning 32-126
			//generates a random char (A-Z, a-z, 0-9, space, and symbols)
			genes[i] = (char) rand; 
		}
	}
	
	public Word(Word p1, Word p2, int mutationPercentage) {
		//create child
		this.target = p1.getTarget();
		this.genes = new char[target.length];
		for(int i = 0; i < this.genes.length; i++) {
			// 50% chance to pick a letter from parent1 or parent2
			int chance = r.nextInt(2); //generate a random number between 0(inclusive) and 2(exclusive)
			int mutationChance = r.nextInt(100) + 1; //generates a random number between 0(inclusive) and 100(exclusive) plus 1 meaning 1-100
			//IMPLEMENT MUTATION - 1% chance per letter to mutate (meaning random letter instead)
			if(mutationChance <= mutationPercentage) { //mutation happens
				int mut = r.nextInt(95) + 32; //generates numbers between 0(inclusive) and 95(exclusive) plus 32 meaning 32-122
				//generates a random char (A-Z, a-z, 0-9, space, and symbols)
				this.genes[i] = (char) mut; //put the random char in that spot
			}else { //mutation doen't happen
				if(chance == 1) {
					this.genes[i] = p1.getWordArray()[i]; //50% chance to get the char from parent 1
				}else {
					this.genes[i] = p2.getWordArray()[i]; //50% chance to get the char from parent 2
				}
			}
		}
	}
	
	//getters
	public String getWord() {
		return new String(genes);
	}
	
	public char[] getWordArray() {
		return this.genes;
	}
	
	public char[] getTarget() {
		return this.target;
	}
	
	//methods
	public int computeFitness() { //returns the fitness of the word as compared to the target
		this.fitness = 0;
		for(int i = 0; i < genes.length; i++) {
			if(genes[i] == this.target[i]) {
				this.fitness++;
			}
		}
		return this.fitness;
	}
	
}
