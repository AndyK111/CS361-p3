package tm;

@SuppressWarnings("unused")
public class TMTransition {
    protected TMState destination;
    protected int write;
    protected TMDirection direction; //0 for left, 1 for right

    public TMTransition(TMState toState, int write, TMDirection direction){
        this.destination = toState;
        this.write = write;
        this.direction = direction;
    }

    public TMState getDestination(){
        return this.destination;
    }

    public int getWrite(){
        return this.write;
    }

    public TMDirection getDirection(){
        return this.direction;
    }
}
