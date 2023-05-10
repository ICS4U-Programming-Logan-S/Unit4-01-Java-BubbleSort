import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
* This program gets input from a file,
* and uses a recursive function to
* sort them by using a bubble sort
* system.
*
* @author Logan S
* @version 1.0
* @since 2023-05-09
*/

public final class BubbleSort {

    /**
    * For style checker.
    *
    * @exception IllegalStateException Utility class.
    * @see IllegalStateException
    */

    private BubbleSort() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * This is the main method.
    *
    * @param args Unused.
    */

    public static void main(String[] args) {

        // Create an empty ArrayList to store each line of input from the file
        final ArrayList<String> lines = new ArrayList<>();
        // Create an empty String to store the sorted lines
        String sortedLines = "";
        // Set the separator for each value in the input file
        final String separator = " ";

        try {
            // Open the input file
            final File inputFile = new File("input.txt");
            final Scanner scanner = new Scanner(inputFile);
            // Create an output file to store the sorted lines
            final FileWriter outputFile = new FileWriter("output.txt");

            // Read each line of the input file and add it to the ArrayList
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            // For each line in the ArrayList
            for (String line : lines) {
                try {
                    // Split the line into an array of integers
                    final int size = line.split(separator).length;
                    final int[] array = new int[size];
                    for (int i = 0; i < size; i++) {
                        array[i] = Integer.parseInt(line.split(separator)[i]);
                    }

                    // Sort the array using bubble sort
                    final int[] sortedArray = bubbleSort(array, array.length);
                    /* Convert the sorted array to a string and add it to the
                    output */
                    sortedLines += Arrays.toString(sortedArray) + "\n";
                } catch (NumberFormatException exception) {
                    /* If there's an error with the line, add an error message
                    to the output */
                    sortedLines += "Error: " + exception.getMessage() + " \n";
                }
            }

            // Write the sorted lines to the output file
            outputFile.write(sortedLines);
            // Print the sorted lines to the console
            System.out.println(sortedLines);

            // Close the output file
            outputFile.close();
            // Close the scanner
            scanner.close();

        } catch (IOException error) {
            /* If there's an error with the file input or output, print an
            error message */
            System.err.println("Error - " + error.getMessage());
        }
    }

    /**
    * This is the sorting function.
    *
    * @param array The base.
    * @param length the exponent
    * @return The sorted array.
    */

    public static int[] bubbleSort(int[] array, int length) {
        // If the array is already sorted (length = 1), return it
        if (length == 1) {
            return array;
        }

        /* Iterate through the array and swap adjacent elements if they're out
        of order */
        for (int i = 0; i < length - 1; i++) {
            if (array[i] > array[i + 1]) {
                final int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
            }
        }

        // Recursively sort the remaining unsorted elements of the array
        return bubbleSort(array, length - 1);
    }
}
