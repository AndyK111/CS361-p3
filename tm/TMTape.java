package tm;

import java.util.LinkedList;

public class TMTape {
    protected LinkedList<Character> tape = new LinkedList<Character>();
    protected int head; //index of the current cell

    public TMTape(){
        this.head = 0;
        tape.add((char)0);
    }

    public TMTape(String tapeChars){
        this.head = 0;
        char[] array = tapeChars.toCharArray();
        for(char c : array){
            tape.add(c);
        }
    }

    public void moveRight(){
        this.head++;
        if(tape.size() < head + 1) {
            tape.add((char)0);
        }
    }

    public void moveLeft(){
        if(this.head == 0){
            tape.addFirst((char)0);
        } else {
            head--;
        }

    }
}
