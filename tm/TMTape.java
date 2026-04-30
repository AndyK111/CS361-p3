package tm;

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

    protected int[] tape;
    protected int headIndex;
    protected int leftBoundIndex;
    protected int rightBoundIndex;

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
        int initialSize = Math.max(1048576, inputSymbols.length * 2 + 1);
        tape = new int[initialSize];

        headIndex = initialSize / 2;
        leftBoundIndex = initialSize / 2;

        if (inputSymbols.length == 0)
        {
            rightBoundIndex = headIndex;
        }
        else
        {
            rightBoundIndex = headIndex + inputSymbols.length - 1;

            System.arraycopy(inputSymbols, 0, tape, headIndex, inputSymbols.length);
        }
    }

    public int read()
    {
        return tape[headIndex];
    }

    public void write(int symbol)
    {
        tape[headIndex] = symbol;
    }

    public void moveRight(){
        headIndex++;
        verifyCapacity();

        if (headIndex > rightBoundIndex)
        {
            rightBoundIndex = headIndex;
        }
    }

    public void moveLeft(){
        headIndex--;
        verifyCapacity();

        if (headIndex < leftBoundIndex)
        {
            leftBoundIndex = headIndex;
        }
    }

    public String getVisitedContents()
    {
        StringBuilder sb = new StringBuilder(rightBoundIndex - leftBoundIndex + 1);

        for (int i = leftBoundIndex; i <= rightBoundIndex; i++)
        {
            sb.append(tape[i]);
        }

        return sb.toString();
    }

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
