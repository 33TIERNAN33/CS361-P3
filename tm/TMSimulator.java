package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Javadoc comment describing this class.
 */
public class TMSimulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java tm.TMSimulator <inputfile>");

            return;
        }
        
        String inputFile = args[0];
        int numStates;
        int numSymbols;


        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            line = reader.readLine();
            numStates = Integer.parseInt(line.trim());

            line = reader.readLine();
            numSymbols = Integer.parseInt(line.trim());

            int totalTransitions = (numStates -1 ) * (numSymbols + 1);

            TM tm = new TM(numStates, numSymbols);

            for (int i = 0; i < totalTransitions; i++) {
                line = reader.readLine().trim();
                String[] parts = line.split(",");
                int nextState = Integer.parseInt(parts[0]);
                int writeSymbol = Integer.parseInt(parts[1]);
                char move = parts[2].charAt(0);

                tm.addTransition(i, nextState, writeSymbol, move);
            }

            String input = reader.readLine();
            if (input == null) {
                input = "";
            } else {
                input = input.trim();
            }

            tm.initializeTape(input);
            tm.run();


        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing file: " + e.getMessage());
        }
    }
}
