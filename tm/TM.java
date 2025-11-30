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
    private int headPos = 0;
    private int minVisited = 0;
    private int maxVisited = 0;


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
    }

    public void addTransition(int index, int nextState, int writeSymbol, char move) {
        TMState tmState = states[(int)(index / (numSymbols + 1))];
        int symbol = index % (numSymbols + 1);
        tmState.setTransition(symbol, nextState, writeSymbol, move);
    }

    public void initializeTape(String input) {
        for (int i = 0; i < input.length(); i++) {
            tape.put(i, (int)(input.charAt(i)));
        }
    }

    public void run() {
        TMState currentState = states[0];

        while(headPos != haltState) {
            int symbol = tape.getOrDefault(headPos, 0);
            
        }
    }
}
