package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static tm.TMDirection.parseDirection;

/**
 * TMParser reads the contents of a TM.txt file and creates all the representative objects in the java runtime.
 *
 * @author Andy Kempf, Sam Kleman
 */
public class TMParser
{
    /**
     * Static factory for TM objects provided the filename of the TM.txt file
     * @param filename The name of the TM.txt file
     * @return A TM object with representative underlying supporting objects (tape & states)
     * @throws IOException Thrown by file I/O
     */
    public static TM parse(String filename) throws IOException
    {
        //Create a BufferedReader for parsing files text contents
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            //Extract first two lines into number of states & symbols
            int statesQuantity = Integer.parseInt(br.readLine().trim());
            int symbolsQuantity = Integer.parseInt(br.readLine().trim());

            //Initialize states array
            TMState[] states = new TMState[statesQuantity];

            //Initialize needed state objects and provide size of internal transitions array
            for (int i = 0; i < statesQuantity; i++)
            {
                states[i] = new TMState(String.valueOf(i), symbolsQuantity);
            }

            //Read the transitions and write to states
            readTransitions(br, states, statesQuantity, symbolsQuantity);

            //Empty line = empty input tape
            String input = br.readLine();
            if (input == null)
            {
                input = "";
            }

            //Otherwise extract final line and export to tape
            TMTape tape = new TMTape(input.trim());
            return new TM(states, tape);
        }
    }

    /**
     * Read the transition lines from TM.txt and export to corresponding states
     * @param br BufferedReader for the TM.txt
     * @param states Array of state objects to export transitions to
     * @param statesQuantity The number of states in this TM
     * @param symbolsQuantity The number of symbols/alphabet in this TM
     * @throws IOException Thrown by file I/O
     */
    private static void readTransitions(BufferedReader br, TMState[] states, int statesQuantity, int symbolsQuantity) throws IOException
    {
        for (int state = 0; state < statesQuantity - 1; state++)
        {
            for (int read = 0; read <= symbolsQuantity; read++)
            {
                //Regex trims white space and delimits by commas
                String[] parts = br.readLine().trim().split("\\s*,\\s*");

                //Parse the parts of the transition
                int destination = Integer.parseInt(parts[0]);
                int write = Integer.parseInt(parts[1]);
                TMDirection direction = parseDirection(parts[2]);

                //Write to the current state/symbol pair
                states[state].addTransition(read, write, states[destination], direction);
            }
        }
    }
}
