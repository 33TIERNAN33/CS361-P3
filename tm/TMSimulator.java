package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Entry point for the Turing machine simulator.
 * 
 * This class is responsible for parsing the machine description file,
 * constructing the {@link TM} instance, initializing the input tape,
 * and starting the simulation. It expects a single command-line
 * argument naming the file that encodes the Turing machine and input.
 * The program prints the contents of all visited tape cells as digits
 * with no spaces, followed by a newline, when the machine halts.
 *
 * @author Tiernan Benner
 */
public class TMSimulator {
    /**
     * Main entry point for the simulator.
     * 
     * Usage: java tm.TMSimulator inputfile.txt
     * 
     * The input file must contain the Turing machine description and
     * (optionally) an input string on the last line, formatted as
     * specified in the project description.
     *
     * @param args command-line arguments; expects exactly one argument,
     *             the path to the machine description file
     */
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
            //System.out.println("Number of states: " + numStates);

            line = reader.readLine();
            numSymbols = Integer.parseInt(line.trim());
            //System.out.println("Number of symbols: " + numSymbols);

            int totalTransitions = (numStates -1 ) * (numSymbols + 1);
            //System.out.println("Total transitions to read: " + totalTransitions);

            TM tm = new TM(numStates, numSymbols);

            for (int i = 0; i < totalTransitions; i++) {
                line = reader.readLine().trim();
                String[] transitionParts = line.split(",");
                int nextState = Integer.parseInt(transitionParts[0]);
                int writeSymbol = Integer.parseInt(transitionParts[1]);
                char move = transitionParts[2].charAt(0);

                tm.addTransition(i, nextState, writeSymbol, move);
                //System.out.println("Added transition " + i + ": " + nextState + ", " + writeSymbol + ", " + move);
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
