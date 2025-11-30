package tm;

public class TMTransition {
    private int nextState;
    private int writeSymbol;
    private int move;

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

    public int getNextState() {
        return nextState;
    }

    public int getWriteSymbol() {
        return writeSymbol;
    }

    public int getMove() {
        return move;
    }
}
