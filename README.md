****************
* Project 3: Turing Machine Simulator
* CS 361
* 4/29/26
* Sam Kleman, Andy Kempf
****************
OVERVIEW:
This program simulates a single-tape Turing Machine based on provided input files
formatted according to assignment specifications.

INCLUDED FILES:
* TM.java - the main TM class that puts everything together
* TMDirection.java - stores either left or right
* TMParser.java - Required for input file parsing
* TMSimulator.java - Creates and runs the TM based on the input file
* TMState.java - Represents a state in a TM
* TMTape.java - Represents the bi-infinite tape
* TMTransition.java - Represents a transition from one state to another
* README - this file

COMPILING AND RUNNING:
To compile this program and it's dependencies, run:
'javac ./tm/*java' 
To run the program, run:
'java ./tm/TMSimulator.java <filename.txt>

PROGRAM DESIGN AND IMPORTANT CONCEPTS:

The design of our Turing machine is broken up into several key classes
that each simulate one specific aspect of the machine, I.E. the tape,
a state, a transition, a direction. These individual parts are put together
in the TM.java class. Each state is represented by an individual State object,
with transitions stored in a HashMap. Transitions store the destination, write
character, and direction. When transitions are added, their key is the character
consumed and their value is the transition object itself. The tape stores cells
that have been visited in an array of ints with the head (an index) starting at the center
of the array, and shifting left or right based on the corresponding 
transition read from the current state and input symbol. If the movement of the
machine butts up against either end of the array, a new array is created with 
double the length and copies the old array into the new one using System.arraycopy.

The parser class' job is to interpret the lines on the input file and convert
those specifications into a TM with the correct number of states and transitions
corresponding to each input symbol for each state. This process is done using a 
buffered reader to more easily read one line at a time. It is VERY IMPORTANT that the
input file is correctly formatted, as even a slight error will break the whole
thing. TMSimulator.java is our driver class, allowing the user to pass in an input file
in the command line and building/simulating the corresponding TM. The characters
on the tape are outputted as a string once the machine halts. Unfortunately our simulator
can only really work with TMs that are exactly within the assignment specifications,
meaning that TMs that don't have a guaranteed transition for each symbol in the alphabet,
stay put TMs, and multi-tape/head TMs are not possible under our existing framework. This
limitation is probably the first place to start if one wanted to improve our simulator. 

TESTING:
How did you test your program to be sure it works and meets all of the
requirements? What was the testing strategy? What kinds of tests were
run?

I ran the program against the included Turing Machine files that were provided on canvas. For file 5 I compared the sum
counts.

Can your program handle bad input? Is your program idiot-proof? How do
you

The program crashes on bad input. Which I think is expected behavior.

know? What are the known issues / bugs remaining in your program?

I have not noticed any yet. Slowness was an issue but that was resolved when switching from a Linked List implementation
of the tape to a standard array.

DISCUSSION:

Implementation of our simulator was very straightforward. As mentioned
above, we broke the TM itself into it's component parts and implemented
them one at a time, making changes to variable formatting and data structures
when it seemed necessary/useful. There wasn't much in this process that
went beyond our existing knowledge base, which thankfully meant that we
didn't have to spend too much time figuring out how to do something and
instead just did it. 

When initially running the simulator with file 0, our output 
seemed to be different from what was expected. However, we soon realized
that the initial blank line for input was actually supposed to be three
ones in a row (as specified in the assignment doc), which when fixed
ended up doing the trick. Once we ran file 5, we realized that our machine
was far from efficient, prompting us to replace the initial LinkedList with
a standard array for higher efficiency. I wouldn't really say that any parts 
of this project were particularly challenging or difficult (thankfully). I 
can however say that it led to a much better understanding of Turing machines 
conceptually, at least for me (Sam).

EXTRA CREDIT:
If the project had opportunities for extra credit that you attempted,
be sure to call it out so the grader does not overlook it.

*Hoping to get a good enough speed for some extra points!*

SOURCES:

Sam's sources:
I used chatGPT on April 27th, 2026 to gain an overhead understanding of
how the TMParser.java class worked (I had never seen a BufferedReader before)
--------------------------------------------------------------------------
Andy's sources:
- I searched the java enum declaration to use for TMDirection, I never used an enum in java before.
- I used chatGPT on April 28th, 2026 to ask if java always needed to use an O(n) traversal to fetch any value on a LinkedList.
  (I partially expected java would keep some active reference to move around, as if it were an iterator)
--
