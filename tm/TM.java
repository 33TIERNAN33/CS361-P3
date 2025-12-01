package tm;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a deterministic, total Turing machine with a bi-infinite tape.
 * 
 * A {@code TM} object contains the full transition function for all
 * non-halting states, as well as a sparse tape representation backed
 * by a {@link java.util.HashMap}. The machine supports loading an
 * input string onto the tape at the origin, then simulating steps
 * until the halting state is reached, finally printing the contents
 * of all visited tape cells.
 *
 * @author Tiernan Benner
 */
public class TM {
    
    private TMState[] states;
    private int numStates;
    private int numSymbols;
    private int haltState;
    private Map<Integer, Integer> tape = new HashMap<>();

    /**
     * Constructs a new Turing machine with the given number of states
     * and tape symbols.
     *
     * @param numStates  total number of states in the machine; states
     *                   are indexed {@code 0..numStates-1}, where
     *                   {@code numStates-1} is the unique halting state
     * @param numSymbols number of input symbols {@code m}; the tape
     *                   alphabet is {@code {0,1,...,m}} where 0 is blank
     */
    public TM(int numStates, int numSymbols) {
        this.numStates = numStates;
        this.numSymbols = numSymbols;
        haltState = numStates - 1;
        states = new TMState[numStates];
        for (int i = 0; i < numStates; i++) {
            states[i] = new TMState(numSymbols);
        }
        //System.out.println("Initialized TM with " + numStates + " states and " + numSymbols + " symbols.");
    }

    /**
     * Adds a single transition to the machine based on its linear index.
     * 
     * The index encodes the {@code (state, symbol)} pair in row-major
     * order over all non-halting states and all tape symbols. From
     * this index the owning state and input symbol are derived, and
     * the corresponding {@link TMTransition} is stored in the
     * appropriate {@link TMState}.
     *
     * @param index      zero-based index over all transitions,
     *                   in the range {@code 0..(numSymbols+1)*(numStates-1)-1}
     * @param nextState  next state to enter when this transition is taken
     * @param writeSymbol symbol to write onto the current tape cell
     * @param move       head movement direction, {@code 'L'} or {@code 'R'}
     */
    public void addTransition(int index, int nextState, int writeSymbol, char move) {
        TMState tmState = states[(int)(index / (numSymbols + 1))];
        int symbol = index % (numSymbols + 1);
        tmState.setTransition(symbol, nextState, writeSymbol, move);
    }

    /**
     * Initializes the tape contents from the given input string.
     * 
     * Each character of the string is assumed to be a digit representing
     * a tape symbol in the range {@code 0..numSymbols}. The first
     * character is written at logical tape position 0, the second at
     * position 1, and so on. If the input string is empty, the tape
     * is left entirely blank (all zeros).
     *
     * @param input the input string to place on the tape; may be empty
     */
    public void initializeTape(String input) {
        for (int i = 0; i < input.length(); i++) {
            tape.put(i, Character.getNumericValue(input.charAt(i)));
        }
        //System.out.println("Initialized tape with input: " + input);
    }

    /**
     * Runs the Turing machine simulation until the halting state is reached.
     * 
     * The head starts at logical position 0 in state 0. On each step,
     * the machine reads the current tape symbol, looks up the
     * corresponding transition, writes the new symbol, moves the head
     * left or right, and updates the current state. The method tracks
     * the minimum and maximum tape positions visited by the head. When
     * the halting state is entered, the method prints the symbols in
     * all visited cells from leftmost to rightmost as digits with no
     * spaces, followed by a newline.
     */
    public void run() {
        int currentStateIndex = 0;
        TMState currentState = states[currentStateIndex];
        int headPos = 0;
        int minVisited = 0;
        int maxVisited = 0;
        int symbol = tape.getOrDefault(headPos, 0);

        while(currentStateIndex != haltState) {
            symbol = tape.getOrDefault(headPos, 0);
            tape.put(headPos, currentState.getTransitions(symbol).getWriteSymbol());
            headPos += currentState.getTransitions(symbol).getMove();
            currentStateIndex = currentState.getTransitions(symbol).getNextState();
            currentState = states[currentStateIndex];
            
            if(headPos < minVisited) {
                minVisited = headPos;
            }

            if(headPos > maxVisited) {
                maxVisited = headPos;
            }
        }

        StringBuilder output = new StringBuilder();

        for(int i = minVisited; i <= maxVisited; i++) {
            output.append(tape.getOrDefault(i, 0));
        }

        /* int sumOfSymbols = 0;
        for (int i = minVisited; i <= maxVisited; i++) {
            sumOfSymbols += tape.getOrDefault(i, 0);
        } */

        //System.out.println("Output:");
        System.out.println(output.toString());
        //System.out.println("output length: " + output.length());
        //System.out.println("Sum of symbols: " + sumOfSymbols);
    }
}
