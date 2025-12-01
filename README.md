# Project #3: Turing Machine simulator

* Author: Tiernan Benner
* Class: CS361 Section #001
* Semester: Fall 2025

## Overview

This program simulates a deterministic, total Turing machine with a bi-infinite tape.  
The simulator reads a machine description file that specifies the number of states,
input alphabet size, and the full transition function for all non-halting states,
followed by an optional input string. It then executes the machine starting in
state 0 with the head on the first input symbol, runs until the unique halting
state is reached, and prints the contents of all visited tape cells as digits
with no spaces, followed by a newline.

## Reflection

To preface this project reflection I ended up doing this project by myself. 
I had tried to find a partner via discussion board on canvas and when that 
didn't work I tried to email the professor to help find a partner but I sent 
it that monday starting the thanksgiving break so I never got a response. 
So please don't deduct too harshly from me not doing with a partner, even 
though it is partially my fault I didn't get one, I really did try to.

Overall, this project helped me connect the theoretical definition of a Turing
machine with an actual implementation. Once I decided on a representation for
the machine and tape, the core simulation loop was straightforward. Using a
sparse `HashMap` for the tape made it easy to support a bi-infinite tape and
arbitrary head positions without worrying about resizing arrays or managing
offsets manually like I would have had to with an array.

The most challenging parts were correctly interpreting the file format and
getting the halting condition right. At first I confused the halting state at
the head position rather than the current state position, which caused 
incorrect termination behavior, and I also had to be careful about reading the 
final (possibly blank) input line without triggering `NullPointerException`s. 
Another subtle issue was remembering that the tape alphabet symbols are digits 
(`0..m`), not their character codes, which took me a while to figure out when I 
was inserting '1' into my hashmap of the tape and tried to print the results it 
was printing 48 which I was able to look up and resolve with proper integer parsing 
from a character since apparently Integer.parseInt() only works on string, not char, 
so I also had to look up the proper way to parse char to in. After that I was then 
able to convert characters to integer symbols and back when reading and printing 
easily. Debugging these issues made me pay close attention to off-by-one errors and 
how I mapped the linear transition index to `(state, symbol)` pairs.

To keep the code easier to debug and modify, I separated the design into small
classes: a `TM` class to manage the machine and tape, a `TMState` class to
store outgoing transitions, and a `TMTransition` class to hold the next-state,
write-symbol, and movement information. This object-oriented structure made it
clear who is responsible for what and made the simulation loop in `TMSimulator`
much more readable. If I could go back and start over, I would prototype the
transition parsing and tape initialization earlier, and write small tests for
tiny machines before running the large provided files. That would have caught
my encoding and halting mistakes sooner and saved some time.

## Compiling and Using

To compile the simulator, make sure you are in the project directory that
contains the `tm` folder. Then run:

bash
javac tm/*.java

To run the simulator run:
java tm.TMSimulator file#.txt
replace file# with whatever file you would want to read from that is in the correct format.

## Sources used

I gave my readme and javadocs to AI so it could make everything more understandable/neat.

----------
This README template is using Markdown. To preview your README output,
you can copy your file contents to a Markdown editor/previewer such
as [https://stackedit.io/editor](https://stackedit.io/editor).
