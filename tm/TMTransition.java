package tm;

/**
 * Encapsulates a single transition in the Turing machine.
 * 
 * A {@code TMTransition} stores the next state, the symbol to write
 * on the tape, and the head movement as an integer offset. The move
 * is stored as {@code -1} for left and {@code +1} for right, derived
 * from the character given to the constructor.
 *
 * @author Tiernan Benner
 */
public class TMTransition {
    private int nextState;
    private int writeSymbol;
    private int move;

    /**
     * Constructs a transition with the given behavior.
     *
     * @param nextState   the state to enter after this transition
     * @param writeSymbol the symbol to write onto the current tape cell
     * @param move        head movement direction, {@code 'L'} or {@code 'R'};
     *                    stored internally as {@code -1} or {@code +1}
     * @throws IllegalArgumentException if {@code move} is not {@code 'L'} or {@code 'R'}
     */
    public TMTransition(int nextState, int writeSymbol, char move) {
        this.nextState = nextState;
        this.writeSymbol = writeSymbol;
        
        if(move == 'L') {
            this.move = -1;
        }else if (move == 'R') {
            this.move = 1;
        }else {
            throw new IllegalArgumentException("Move must be 'L' or 'R'");
        }
    }

    /**
     * Returns the next state for this transition.
     *
     * @return the next state index
     */
    public int getNextState() {
        return nextState;
    }

    /**
     * Returns the symbol that will be written by this transition.
     *
     * @return the tape symbol to write
     */
    public int getWriteSymbol() {
        return writeSymbol;
    }

    /**
     * Returns the movement offset for the head.
     * 
     * The value is {@code -1} for a left move and {@code +1} for a right move.
     *
     * @return integer offset to add to the head position
     */
    public int getMove() {
        return move;
    }
}
