# Java Word Generator using Genetic Algorithms

## by Daniel Booher

This is a `Java` program, run in the terminal, that generates a target word/phrase using Genetic Algorithms

This program uses `ANSI Escape Sequences` meaning that **Windows Command Prompt (cmd) CANNOT OUTPUT CORRECTLY!** 

If you are using Windows, there are a number of terminal emulators available that do support `ANSI Escape Sequences`, the easiest of which to get is:

[Windows Terminal](https://www.microsoft.com/en-us/p/windows-terminal-preview/9n0dx20hk701?activetab=pivot:overviewtab)

# Terms

These are some terms that are necessary to understand in order to use this program:

`Target`
> The word or phrase that the program will try to generate using genetic algorithms

`Word`
> Each `Word` has the same number of letters as the `target` word/phrase
>
> Every `Word` has a `fitness` based on the letters it contains

`Fitness`
> If a letter in a given `Word` is the same letter and in the same spot as in the `target`, it is worth 1 `fitness` 
> 
> `Fitness` for each `Word` is calculated by summing up each letter's `fitness`
> 
> Example:
> 
> `target` = cat
> 
> `Word` = cac
> 
> `Fitness` = 2 (1 for the c and 1 for the a and 0 for the second c)

`Population`
> A collection of `Words` used to generate new `Words`
>
> A `Population` knows:
> 
> The highest `fitness` within the population
>
> The word that has the highest `fitness` in the population
>
> The average `fitness` in the population

`Generation`
> Each `Generation` a new `Population` is created
>
> This new `Population` gets its `Words` by mutating the `Words` of the old `Population`
>
> It does this by picking 2 `Words` from the old `Population` as `parents` and creating a new `Word` (the `child`) from those `parents`
>
> Every `child` has a 50% chance to inherit a letter from each `parent` (50% to inherit from parent 1 and 50% to inherit from parent 2)
>
> Every `child` also has a user determined percent chance to have each letter `mutate`
>
> A `mutation` replaces that letter with a random letter and no inheritance happens for that letter

# Usage: 

`java wordGenerator.WordGenerator`

It will ask you to input some values:

`Population size`
> The number of words to be mutated in a `Population`
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