package tm;

import java.util.HashMap;

public class TMState {
    protected String stateName;
    protected HashMap<String, TMTransition> transitions = new HashMap<String, TMTransition>();

    public TMState(String id){
        this.stateName = id;
    }

    public void addTransition(TMState destination, char input, int dir){
        String mapID = input + "";
        TMTransition trans = new TMTransition(destination, input, dir);
        transitions.put(mapID, trans);
    }

    public String getName(){
        return this.stateName;
    }

    public TMTransition getTransition(char input){
        return transitions.get(input + "");
    }
}
