package tm;

/**
 * Represents a single state in the Turing machine.
 * 
 * A {@code TMState} holds the outgoing transitions for all possible
 * tape symbols in this state. Each symbol in {@code 0..numSymbols}
 * maps to a {@link TMTransition} describing how the machine behaves
 * when that symbol is read in this state.
 *
 * @author Tiernan Benner
 */
public class TMState {
    
    /**
     * Constructs a state that can store transitions for all tape symbols.
     *
     * @param numSymbols number of input symbols {@code m}; this state
     *                   allocates space for transitions on symbols
     *                   {@code 0..m}
     */
    private TMTransition[] transitions;
    public TMState(int numSymbols) {
        transitions = new TMTransition[numSymbols + 1];
    }

    /**
     * Defines the transition to take when a particular symbol is read
     * in this state.
     *
     * @param symbol      tape symbol that triggers this transition
     * @param nextState   next state to enter when this transition is taken
     * @param writeSymbol symbol to write onto the current tape cell
     * @param move        head movement direction, {@code 'L'} or {@code 'R'}
     */
    public void setTransition(int symbol, int nextState, int writeSymbol, char move) {
        transitions[symbol] = new TMTransition(nextState, writeSymbol, move);
    }

    /**
     * Returns the transition associated with the given input symbol.
     *
     * @param symbol tape symbol being read in this state
     * @return the {@link TMTransition} for the specified symbol, or
     *         {@code null} if no transition has been defined
     */
    public TMTransition getTransitions(int symbol) {
        return transitions[symbol];
    }
}
