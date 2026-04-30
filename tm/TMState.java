package tm;

@SuppressWarnings("unused")
public class TMState {
    protected String stateName;
    protected TMTransition[] transitions;

    public TMState(String id, int symbolsQuantity){
        this.stateName = id;
        this.transitions = new TMTransition[symbolsQuantity + 1];
    }

    public void addTransition(int readSymbol, int writeSymbol, TMState destination, TMDirection tapeDirection){
        transitions[readSymbol] = new TMTransition(destination, writeSymbol, tapeDirection);
    }

    public String getName(){
        return this.stateName;
    }

    public TMTransition getTransition(int readSymbol){
        return transitions[readSymbol];
    }
}
