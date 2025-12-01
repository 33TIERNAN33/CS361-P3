package tm;

import java.util.HashMap;
import java.util.Map;

/**
 * Javadoc comment describing this class.
 */
public class TM {
    
    private TMState[] states;
    private int numStates;
    private int numSymbols;
    private int haltState;
    private Map<Integer, Integer> tape = new HashMap<>();


    public TM(int numStates, int numSymbols) {
        this.numStates = numStates;
        this.numSymbols = numSymbols;
        haltState = numStates - 1;
        states = new TMState[numStates];
        for (int i = 0; i < numStates; i++) {
            states[i] = new TMState(numSymbols);
        }

        for (int i = 0; i < numStates - 1; i++) {
            for (int j = 0; j <= numSymbols; j++) {
                // Initialize transitions for each state and symbol
            }
        }
        //System.out.println("Initialized TM with " + numStates + " states and " + numSymbols + " symbols.");
    }

    public void addTransition(int index, int nextState, int writeSymbol, char move) {
        TMState tmState = states[(int)(index / (numSymbols + 1))];
        int symbol = index % (numSymbols + 1);
        tmState.setTransition(symbol, nextState, writeSymbol, move);
    }

    public void initializeTape(String input) {
        for (int i = 0; i < input.length(); i++) {
            tape.put(i, Character.getNumericValue(input.charAt(i)));
        }
        //System.out.println("Initialized tape with input: " + input);
    }

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

        int sumOfSymbols = 0;
        for (int i = minVisited; i <= maxVisited; i++) {
            sumOfSymbols += tape.getOrDefault(i, 0);
        }

        //System.out.println("Output:");
        System.out.println(output.toString());
        //System.out.println("output length: " + output.length());
        //System.out.println("Sum of symbols: " + sumOfSymbols);
    }
}
