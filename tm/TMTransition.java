package tm;

public class TMTransition {
    protected TMState destination;
    protected char input;
    protected int direction; //0 for left, 1 for right

    public TMTransition(TMState toState, char input, int direction){
        this.destination = toState;
        this.input = input;
        this.direction = direction;
    }

    public TMState getDestination(){
        return this.destination;
    }

    public char getInput(){
        return this.input;
    }

    public int getDirection(){
        return this.direction;
    }
}
