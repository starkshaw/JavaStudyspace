/**
 * Created by Zhenbang Xiao on 2015/5/3 0003.
 * <p>
 * This class was the main program integrated all features.
 * <p>
 * According to the Traveling Salesman Problem, we have a text file called "alltowns.txt"
 * which stores the Number, Name, and Coordinates according to the alphabetic order.
 */

// Import

import java.io.*;
import java.util.*;
import java.math.*;

public class TSP {

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
            // Store the coordinates of towns
            double[][] coordinates = new double[amountOfLine][2];
            // Store origin and destination
            String[] indexs;
            Scanner input = new Scanner(System.in);
            for (int i = 0; i < allData.length; i++) {
                for (int j = 2; j < allData[i].length; j++) {
                    coordinates[i][j - 2] = Double.parseDouble(allData[i][j]);
                }
            }
            System.out.println("\n#\tTOWN");
            for (int i = 0; i < allData.length; i++) {
                System.out.println((i + 1) + "\t" + allData[i][1]);
            }
            System.out.println("\nAccording to the collected data, there are " + amountOfLine + " towns found.");
            System.out.print("\nEnter the index of your origin and destination, separate by a comma only: ");
            indexs = input.nextLine().split(",");
            System.out.println("\nCalculating the distance from " + allData[Integer.parseInt(indexs[0]) - 1][1] + " to " + allData[Integer.parseInt(indexs[1]) - 1][1] + "...");
            // Calculations
            System.out.println("The direct distance is " + round(
                    DistanceCal.calDistance(
                            coordinates[Integer.parseInt(indexs[0]) - 1][0],
                            coordinates[Integer.parseInt(indexs[0]) - 1][1],
                            coordinates[Integer.parseInt(indexs[1]) - 1][0],
                            coordinates[Integer.parseInt(indexs[1]) - 1][1]), 3) + " km.");
            // Print out all collected data
            /*for (int i = 0; i < coordinates.length; i++) {
                for (int j = 0; j < coordinates[i].length; j++) {
                    //System.out.print(allData[i][j] + "\t");
                    System.out.print(coordinates[i][j] + "\t");
                }
                System.out.println();
            }*/
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

    /**
     * Optimized mathematical round
     * @param value     The value needs to be rounded.
     * @param places    The amount of significant digit after decimal point.
     * @return The rounded value.
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
