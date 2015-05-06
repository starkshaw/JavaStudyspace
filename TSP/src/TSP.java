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

    public final static String currentPath = System.getProperty("user.dir");       // Store current path

    public static boolean[] visited;
    public static double distance = 0.0;

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
            visited = new boolean[allData.length];      // Check if current town is visited
            // Initialize the array
            for (boolean var : visited) {
                var = false;
            }
            /*System.out.println("\n#\tTOWN");
            for (int i = 0; i < allData.length; i++) {
                System.out.println((i + 1) + "\t" + allData[i][1]);
            }*/
            System.out.println("\nAccording to the collected data, there are " + amountOfLine + " positions found.\n");
            findPath(31, coordinates, allData);
            System.out.println("\nTotal distance: " + round(distance,3) + " km");
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
     *
     * @param value  The value needs to be rounded.
     * @param places The amount of significant digit after decimal point.
     * @return The rounded value.
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void findPath(int indexOfOrigin, double[][] coordinates, String[][] allDataOfTown) {
        int indexOfNext = indexOfOrigin - 1;
        visited[indexOfOrigin - 1] = true;
        double distanceOfNext;
        for (int i = 0; i < allDataOfTown.length - 1; i++) {
            System.out.print(allDataOfTown[indexOfNext][1] + " ~ ");
            //System.out.print(allDataOfTown[indexOfNext][0] + ".");  // Output for airport problem
            double[][] nearest = nearestTown(indexOfNext + 1, coordinates, allDataOfTown);
            double[] result = findFirstUnvisited(nearest);
            indexOfNext = (int) result[0];
            distanceOfNext = result[1];
            System.out.println(allDataOfTown[indexOfNext][1] + ": " + round(distanceOfNext, 3) + " km");
            distance += distanceOfNext;
            visited[indexOfNext] = true;
        }
        //System.out.println(indexOfNext + 1);  // Output for airport problem
    }

    /**
     * Sort the towns by the order of distance (closer to further).
     *
     * @param indexOfOrigin The origin.
     * @param coordinates   The coordinate table.
     * @param allDataOfTown The array stores index, town name, etc.
     * @return Return an array consists index and distance.
     */
    public static double[][] nearestTown(int indexOfOrigin, double[][] coordinates, String[][] allDataOfTown) {
        double[][] result = new double[allDataOfTown.length][2];
        double[][] finalResult = new double[result.length - 1][2];
        double tmpIndex, tmpDistance;
        // Create distance table
        for (int i = 0; i < result.length; i++) {
            result[i][0] = i;
            result[i][1] = CalDistanceByIndex(indexOfOrigin, i + 1, coordinates);
        }
        // Test data
        /*for (int i = 0; i < result.length; i++) {
            System.out.println(allDataOfTown[indexOfOrigin - 1][1] + " ~ " + allDataOfTown[(int) result[i][0]][1] + ":\t" + result[i][1]);
        }*/
        for (int i = 0; i < result.length; i++) {
            for (int j = result.length - 1; j > i; j--) {
                if (result[j][1] < result[j - 1][1]) {
                    tmpDistance = result[j - 1][1];
                    tmpIndex = result[j - 1][0];
                    result[j - 1][1] = result[j][1];
                    result[j - 1][0] = result[j][0];
                    result[j][1] = tmpDistance;
                    result[j][0] = tmpIndex;
                }
            }
        }
        // Test data
        /*for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + "\t" + result[i][1]);
            //System.out.println(allDataOfTown[indexOfOrigin - 1][1] + " ~ " + allDataOfTown[(int) result[i][0]][1] + ":\t" + result[i][1]);
        }*/
        for (int i = 1; i < result.length; i++) {
            finalResult[i - 1][0] = result[i][0];
            finalResult[i - 1][1] = result[i][1];
        }
        // Test data
        /*for (int i = 0; i < finalResult.length; i++) {
            //System.out.println(finalResult[i][0] + "\t" + finalResult[i][1]);
            System.out.println(allDataOfTown[indexOfOrigin - 1][1] + " ~ " + allDataOfTown[(int) finalResult[i][0]][1] + ":\t" + finalResult[i][1]);
        }*/
        return finalResult;
    }

    public static double[] findFirstUnvisited(double[][] sortedList) {
        double result[] = new double[2];
        for (int i = 0; i < sortedList.length; i++) {
            if (visited[(int) sortedList[i][0]] == false) {
                result[0] = sortedList[i][0];
                result[1] = sortedList[i][1];
                break;
            } else {
                result[0] = 0;
                result[1] = 0;
            }
        }
        return result;
    }

    /**
     * Calculate the distance in terms of the index in the database
     *
     * @param index1      Index of the first town.
     * @param index2      Index of the second town.
     * @param coordinates The array consists coordinates.
     * @return The distance.
     */
    public static double CalDistanceByIndex(int index1, int index2, double[][] coordinates) {
        return DistanceCal.calDistance(
                coordinates[index1 - 1][0],
                coordinates[index1 - 1][1],
                coordinates[index2 - 1][0],
                coordinates[index2 - 1][1]);
    }
}
