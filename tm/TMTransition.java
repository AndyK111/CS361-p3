package tm;

/**
 * Contains the data relevant to a transition for a particular state and read symbol
 * @author Andy Kempf, Sam Kleman
 */
public class TMTransition {
    protected TMState destination; //State that this transition should lead to
    protected int write; //Symbol this transition should write
    protected TMDirection direction; //Direction the head should shift for this transition

    /**
     * Constructor for TMTransition object
     * @param toState The state this transition should arrive to
     * @param write The symbol that should be written at the head
     * @param direction The direction the head should move
     */
    public TMTransition(TMState toState, int write, TMDirection direction){
        this.destination = toState;
        this.write = write;
        this.direction = direction;
    }

    /**
     * Returns the destination state for this transition
     * @return TMState reference of destination
     */
    public TMState getDestination(){
        return this.destination;
    }

    /**
     * Return the symbol that this state writes
     * @return Integer to be written
     */
    public int getWrite(){
        return this.write;
    }

    /**
     * The direction this state moves the head
     * @return TMDirection to move the head
     */
    public TMDirection getDirection(){
        return this.direction;
    }
}
