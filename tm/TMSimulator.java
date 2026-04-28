package tm;

import java.io.IOException;

public class TMSimulator
{
    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
        {
            System.err.println("Usage: java tm.TMSimulator <input-file-path>");
            return;
        }

        TM turingMachine = TMParser.parse(args[0]);

        turingMachine.run();
        System.out.println(turingMachine.getTapeContents());
    }


}

