# WordChain

Word Chains is a vocabulary game when we need to change one word to another with the smallest amount of transformation. 
In each transform we can change only one letter.

### Task
This task was posted into [codekata](http://codekata.com/kata/kata19-word-chains/). The description of it was listed right below.
> Write a program that solves word-chain puzzles.
> There’s a type of puzzle where the challenge is to build a chain of words, starting with one particular word and ending with another. 
> Successive entries in the chain must all be real words, and each can differ from the previous word by just one letter. 
> For example, you can get from “cat” to “dog” using the following chain.
>
> cat -> cot -> cog -> dog
>
> The objective of this kata is to write a program that accepts start and end words and, using words from the dictionary, builds a word chain between them. 
> For added programming fun, return the shortest word chain that solves each puzzle. For example, you can turn “lead” into “gold” in four steps 
>
> lead -> load -> goad -> gold
> 
> and “ruby” into “code” 
>
> ruby -> roby -> robe -> rode -> code
>
> Once your code works, try timing it. Does it take less than a second for the above examples given a decent-sized word list? And is the timing the same forwards and backwards (so “lead” into “gold” takes the same time as “gold” into “lead”)?

### Approach
1. Dictionary is loaded into memory
2. To solve this task [Bidirectional search](https://en.wikipedia.org/wiki/Bidirectional_search) based on [BFS algorithm](https://en.wikipedia.org/wiki/Breadth-first_search) was used.

### Solution
To create this application I decided to use Java Spring MVC. We can get the result by the REST API and also we could use endpoint web site to find path.
Example of REST API:
http://localhost:8080/wordChain?startWord=cat&endWord=123
