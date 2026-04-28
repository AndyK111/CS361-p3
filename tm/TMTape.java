package tm;

import java.util.LinkedList;

@SuppressWarnings("unused")
public class TMTape {
    private static int[] toSymbolArray(String input)
    {
        int[] symbols = new int[input.length()];

        for(int i = 0; i < input.length(); i++)
        {
            symbols[i] = Character.getNumericValue(input.charAt(i));
        }

        return symbols;
    }

    protected LinkedList<Integer> tape = new LinkedList<>();
    protected int head; //index of the current cell

    public TMTape()
    {
        this(new int[0]);
    }

    public TMTape(String inputString)
    {
        this(toSymbolArray(inputString));
    }

    public TMTape(int[] inputSymbols)
    {
        this.head = 0;

        if (inputSymbols.length == 0)
        {
            tape.add(0);
        }
        else
        {
            for (int symbol : inputSymbols)
            {
                tape.add(symbol);
            }
        }
    }

    public int read()
    {
        return tape.get(head);
    }

    public void write(int symbol)
    {
        tape.set(head, symbol);
    }

    public void moveRight(){
        this.head++;
        if(head >= tape.size()) {
            tape.add(0);
        }
    }

    public void moveLeft(){
        if(this.head == 0){
            tape.addFirst(0);
        } else {
            head--;
        }
    }

    public String getVisitedContents()
    {
        StringBuilder output = new StringBuilder();

        for (int symbol : tape)
        {
            output.append(symbol);
        }

        return output.toString();
    }
}
