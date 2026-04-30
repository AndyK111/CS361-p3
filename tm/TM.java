package tm;

/**
 * Handles a Turing Machine as a collection of components needed. The Tape, Transitions, and States are managed
 * by this class.
 *
 * @author Andy Kempf, Sam Kleman
 */
public class TM
{
    protected TMState[] states; //Array of states
    protected TMState currentState; //The current state of the machine
    protected TMState haltState; //Halting state of the Turing machine
    protected TMTape tape; //Tape object used to handle machine's tape operations

    /**
     * Constructor for Turing Machine instance
     *
     * @param states The states of this Turing Machine
     * @param tape A Tape object for this Turing Machine
     */
    public TM(TMState[] states, TMTape tape)
    {
        this.states = states;
        this.currentState = states[0];
        this.haltState = states[states.length-1];
        this.tape = tape;
    }

    /**
     * Perform Turing Machine operations until a halting state is encountered.
     */
    public void run()
    {
        while (currentState != haltState)
        {
            int readSymbol = tape.read();
            TMTransition transition = currentState.getTransition(readSymbol);

            tape.write(transition.getWrite());

            if (transition.getDirection() == TMDirection.LEFT)
            {
                tape.moveLeft();
            }
            else
            {
                tape.moveRight();
            }

            currentState = transition.getDestination();
        }
    }

    /**
     * Dumps the contents of all tape's visited contents to String
     * @return All traversed contents of tape
     */
    public String getTapeContents()
    {
        return tape.getVisitedContents();
    }
}
