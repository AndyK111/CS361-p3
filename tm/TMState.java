package tm;

import java.util.HashMap;

@SuppressWarnings("unused")
public class TMState {
    protected String stateName;
    protected HashMap<String, TMTransition> transitions = new HashMap<>();

    public TMState(String id){
        this.stateName = id;
    }

    public void addTransition(int readSymbol, int writeSymbol, TMState destination, TMDirection tapeDirection){
        String mapID = readSymbol + "";
        TMTransition trans = new TMTransition(destination, writeSymbol, tapeDirection);
        transitions.put(mapID, trans);
    }

    public String getName(){
        return this.stateName;
    }

    public TMTransition getTransition(int readSymbol){
        return transitions.get(readSymbol+"");
    }
}
