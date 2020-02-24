# Java Word Generator using Genetic Algorithms

## by Daniel Booher

This is a `Java` program that generates a target word/phrase using Genetic Algorithms

# Usage: 

`java wordGenerator.WordGenerator`

It will ask you to input some values:

`Population size`
> is the number of words to be mutated in a `Population`
> 
> `Population size` is an `int`

`Mutation Percentage`
> The percentage each letter in a given word has to `mutate` (randomly change to another letter)
> 
> `Mutation Percentage` is entered as an `int` which is interpreted as a percentage
> 
> ex: `Mutation Percentage` = 10 -> 10% chance for a letter to mutate

`Target`
> The word/phrase that this program will try to generate
> 
> is a `String` and can include A-Z, a-z, 0-9, spaces, and symbols

# Example:

Input: `java wordGenerator.WordGenerator`

Output: `Please enter population size`

Input: `100`

Output: `Please enter the percentage for the mutation to occour`

Input: `1`

Output: `Please enter target word or phrase`

Input: `Hello World`

Output: 
        
```
Generation: 559

Top Word: Hello World

Fitness: 11

Average Fitness: 9.880000

Elapsed time: 0.8557480000000001 second(s)
```