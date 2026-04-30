package tm;

/**
 * Object represents an individual state and all of its child transition objects.
 * @author Andy Kempf, Sam Kleman
 */
public class TMState {
    protected String stateName; //The string name of the state
    protected TMTransition[] transitions; //Array of transitions indexed by the symbol they are on.

    /**
     * Constructor for TMState
     * @param id String id for this state
     * @param symbolsQuantity The number of symbols / size of the alphabet
     */
    public TMState(String id, int symbolsQuantity){
        this.stateName = id;
        this.transitions = new TMTransition[symbolsQuantity + 1]; //0 is included with +1
    }

    /**
     * Assign a particular position in the transition lookup table with specified transition
     * @param readSymbol The symbol read / index in the lookup table
     * @param writeSymbol The symbol to write at the current head location
     * @param destination The next state the machine should assume after this transition is taken
     * @param tapeDirection The direction the head should move in after this transition is taken
     */
    public void addTransition(int readSymbol, int writeSymbol, TMState destination, TMDirection tapeDirection){
        transitions[readSymbol] = new TMTransition(destination, writeSymbol, tapeDirection);
    }

    /**
     * Get the name of this transition
     * @return The name of this transition
     */
    @SuppressWarnings("unused")
    public String getName(){
        return this.stateName;
    }

    /**
     * Lookup some transition in this state's table
     * @param readSymbol The read symbol to find a transition for
     * @return The transition defined for this symbol on this state
     */
    public TMTransition getTransition(int readSymbol){
        return transitions[readSymbol];
    }
}
