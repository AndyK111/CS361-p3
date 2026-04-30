package tm;

/**
 * This class manages the tape data, tape size, head position, etc for a state machine.
 * It is backed by an array that grows as needed via doubling.
 * @author Andy Kempf, Sam Kleman
 */
public class TMTape {

    /**
     * Static factory for turning string input into an array of integer values for tape import
     * @param input The string input to turn into an array of integers
     * @return The resulting array of integers.
     */
    private static int[] toSymbolArray(String input)
    {
        int[] symbols = new int[input.length()];

        for(int i = 0; i < input.length(); i++)
        {
            symbols[i] = Character.getNumericValue(input.charAt(i));
        }

        return symbols;
    }

    protected int[] tape; //Int array that stores tape data
    protected int headIndex; //Index position of the head
    protected int leftBoundIndex; //LeftMost explored index of the tape
    protected int rightBoundIndex; //RightMost explored index of the tape

    /**
     * Constructor that creates an empty tape.
     */
    public TMTape()
    {
        this(new int[0]);
    }

    /**
     * Constructor that creates a tape populated with some (to be parsed) input string.
     * @param inputString String containing integer initial input of tape.
     */
    public TMTape(String inputString)
    {
        this(toSymbolArray(inputString));
    }

    /**
     * Constructor that creates a tape and populated it with the values given in the inputSymbols array
     * @param inputSymbols Array of integer values to populate tape with.
     */
    public TMTape(int[] inputSymbols)
    {
        int initialSize = Math.max(1048576, inputSymbols.length * 2 + 1); //Giant initial size, or input symbols + buffer if bigger
        tape = new int[initialSize]; //Initialize tape array

        headIndex = initialSize / 2; //Head in the center
        leftBoundIndex = initialSize / 2; //LeftBoudn in the center

        //Move right bnound to center if no initial input, otherwise where the initial input ends.
        if (inputSymbols.length == 0)
        {
            rightBoundIndex = headIndex;
        }
        else
        {
            rightBoundIndex = headIndex + inputSymbols.length - 1;

            System.arraycopy(inputSymbols, 0, tape, headIndex, inputSymbols.length); //Placing the initial input
        }
    }

    /**
     * Return the element under the head currently
     * @return Value underneath the head
     */
    public int read()
    {
        return tape[headIndex];
    }

    /**
     * Replace the value under the head currently
     * @param symbol Value that is to be placed under the head
     */
    public void write(int symbol)
    {
        tape[headIndex] = symbol;
    }

    /**
     * Moves the tape head right
     */
    public void moveRight(){
        headIndex++;
        verifyCapacity();

        if (headIndex > rightBoundIndex)
        {
            rightBoundIndex = headIndex;
        }
    }

    /**
     * Moves the tape head left
     */
    public void moveLeft(){
        headIndex--;
        verifyCapacity();

        if (headIndex < leftBoundIndex)
        {
            leftBoundIndex = headIndex;
        }
    }

    /**
     * Returns all cells that the tape head has ever traversed over.
     * @return String containing the contents of all traversed cells
     */
    public String getVisitedContents()
    {
        StringBuilder sb = new StringBuilder(rightBoundIndex - leftBoundIndex + 1);

        for (int i = leftBoundIndex; i <= rightBoundIndex; i++)
        {
            sb.append(tape[i]);
        }

        return sb.toString();
    }

    /**
     * Verify the internal array is not at capacity and double & copy to expand array if needed.
     */
    public void verifyCapacity()
    {
        if (headIndex >= 0 && headIndex < tape.length) return;

        int oldLength = tape.length;
        int newLength = oldLength * 2;
        int[] newTape = new int[newLength];
        int offset = oldLength/2;

        System.arraycopy(tape, 0, newTape, offset, oldLength);

        tape = newTape;
        headIndex += offset;
        leftBoundIndex += offset;
        rightBoundIndex += offset;
    }
}
