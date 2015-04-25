/**
 * Created by Zhenbang Xiao on 2015/4/25 0025.
 */

/**
 * This class was designated to test the text file reading feature.
 *
 * According to the Traveling Salesman Problem, we have a text file called "alltowns.txt"
 * which stores the Number, Name, and Coordinates according to the alphabetic order.
 */

// Import

import java.io.*;
import java.util.*;

public class TextFileReading {
    final static String currentPath = System.getProperty("user.dir");       // Store current path

    public static void main(String[] args) {
        BufferedReader preread = null;     // Pre-read the line and column from the file
        try {
            System.out.print("Loading... ");
            File filename = new File(currentPath + "//alltowns.txt");
            preread = new BufferedReader(new FileReader(filename));
            System.out.println("Finished.");
            // Store and separate data for further use
            String tmpLine;         // Temporary stores each lines
            int amountOfLine = 0;   // Store the amount of line
            int amountOfColumn = 0; // Store the MAXIMUM amount of columns
            // Accumulate the amount of line and column
            while ((tmpLine = preread.readLine()) != null) {
                amountOfLine++;
                String[] tmpSeparate = tmpLine.split(",");
                if (tmpSeparate.length > amountOfColumn) {
                    amountOfColumn = tmpSeparate.length;
                }
            }
            // Separate all data
            String[][] allData = new String[amountOfLine][amountOfColumn];
            // The real file reader
            BufferedReader read = new BufferedReader(new FileReader(filename));
            int line = 0;
            while (((tmpLine = read.readLine()) != null) && line < amountOfLine) {
                String[] tmpSeparate = tmpLine.split(",");
                for (int i = 0; i < tmpSeparate.length; i++) {
                    allData[line][i] = tmpSeparate[i];
                }
                line++;
            }
            read.close();   // Close the file reader
            // Print out all collected data
            for (int i = 0; i < allData.length; i++) {
                for (int j = 0; j < allData[i].length; j++) {
                    System.out.print(allData[i][j] + "\t");
                }
                System.out.println();
            }
        } catch (IOException ex) {      // Exception handlers
            System.out.println("Error.");
            System.err.println("An exception has been found.");
            ex.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                preread.close();       // Close the file reader
            } catch (IOException ex) {
                System.err.println("An exception has been found.");
                ex.printStackTrace();
                System.exit(-1);
            }
        }

    }
}
