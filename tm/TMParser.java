package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static tm.TMDirection.parseDirection;

@SuppressWarnings("unused")
public class TMParser
{
    public static TM parse(String filename) throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            int statesQuantity = Integer.parseInt(br.readLine().trim());
            int symbolsQuantity = Integer.parseInt(br.readLine().trim());

            TMState[] states = new TMState[statesQuantity];

            for (int i = 0; i < statesQuantity; i++)
            {
                //TODO: Investigate turning string state label into int
                states[i] = new TMState(String.valueOf(i));
            }

            readTransitions(br, states, statesQuantity, symbolsQuantity);

            String input = br.readLine();
            if (input == null)
            {
                input = "";
            }

            TMTape tape = new TMTape(input.trim());
            return new TM(states, tape);
        }
    }

    private static void readTransitions(BufferedReader br, TMState[] states, int statesQuantity, int symbolsQuantity) throws IOException
    {
        for (int state = 0; state < statesQuantity - 1; state++)
        {
            for (int read = 0; read <= symbolsQuantity; read++)
            {
                String[] parts = br.readLine().trim().split("\\s*,\\s*");

                int destination = Integer.parseInt(parts[0]);
                int write = Integer.parseInt(parts[1]);
                TMDirection direction = parseDirection(parts[2]);

                states[state].addTransition(read, write, states[destination], direction);
            }
        }
    }
}
