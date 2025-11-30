package tm;

public class TMState {
    
    private TMTransition[] transitions;
    public TMState(int numSymbols) {
        transitions = new TMTransition[numSymbols + 1];
    }

    public void setTransition(int symbol, int nextState, int writeSymbol, char move) {
        transitions[symbol] = new TMTransition(nextState, writeSymbol, move);
    }
}
