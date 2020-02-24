package wordGenerator;
import wordGenerator.*;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * Generates a target word using Genetic Algorithms
 * Each generation generates a new population of Words that are closer to the target Word
 */

public class WordGenerator {

	Population population, children; //population of Words and the children each generation produces
	//Word[] words, children;
	private char[] target; //the target Word to generate represented as a character array
	private int populationSize = 100; //the size of the population (100 by default)
	private int generation = 1; //The number of the current generation
	private int mutationPercentage;
	private Word parent1, parent2, child; //used for selection and crossover
	private Scanner in = new Scanner(System.in);
	private boolean targetNotFound = true; //Once the target is found, the program ends
	public void start() {
		// generate initial population
		System.out.println("Please enter population size");
		populationSize = in.nextInt();
		System.out.println("Please enter percentage for the mutation to occour");
		mutationPercentage = in.nextInt();
		System.out.println("Please enter target word or phrase");
		in.nextLine(); //flush the Scanner
		target = in.nextLine().toCharArray(); //nextLine() is used to allow for phrases (multiple words with spaces)
		population = new Population(populationSize, target);
		long startingTime = System.nanoTime();
		// compute fitness
		//display stats
		System.out.println("\nGeneration: " + generation);
		System.out.println("Top Word: " + population.getTopFitnessWord().getWord());
		System.out.println("Fitness: " + population.getTopFitness());
		System.out.print("Average Fitness: " + population.getAverageFitness()); //use print instead of println to allow for fancy ANSI escape sequences later
		// LOOP
		while(targetNotFound) {
			children = new Population(populationSize, target); //create children with an empty Word array
			for(int i = 0; i < populationSize; i++) { //loop through population times
				// selection
				//pick 2 words to breed based on fitness probability
				parent1 = population.getParent();
				parent2 = population.getParent();
				// crossover
				// mutation
				child = new Word(parent1, parent2, mutationPercentage);
				children.add(child, i);
			}
			population = new Population(children);
			// compute fitness
			generation++; //We are now on the next Generation
			//Use fancy ANSI escape sequences to update the user without flooding the terminal with thousands of lines of text
			System.out.printf(((char) 0x1b) + "[3A\r" + "Generation: %d ", generation);
			System.out.printf(((char) 0x1b) + "[1B\r" + "Top Word: %s ", population.getTopFitnessWord().getWord());
			System.out.printf(((char) 0x1b) + "[1B\r" + "Fitness: %d ", population.getTopFitness());
			System.out.printf(((char) 0x1b) + "[1B\r" + "Average Fitness: %f ", population.getAverageFitness());
			// LOOP until desired result reached
			if(population.getTopFitness() == target.length) { //target word/phrase has been generated
				//tell the user
				long elapsedTime = System.nanoTime() - startingTime;
				double seconds = elapsedTime * Math.pow(10, -9);
				System.out.println("\nElapsed time: " + seconds + " second(s)");
				targetNotFound = false;
			}
		}	
	}
	public static void main(String[] args) {
		WordGenerator wg = new WordGenerator();
		wg.start();
	}
}
