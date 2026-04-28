package tm;

@SuppressWarnings("unused")
public class TM
{
    protected TMState[] states;
    protected TMState currentState;
    protected TMState haltState;
    protected TMTape tape;

    public TM(TMState[] states, TMTape tape)
    {
        this.states = states;
        this.currentState = states[0];
        this.haltState = states[states.length-1];
        this.tape = tape;
    }

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

    public String getTapeContents()
    {
        return tape.getVisitedContents();
    }
}
