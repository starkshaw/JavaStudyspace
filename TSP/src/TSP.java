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
            /*System.out.println("\n#\tTOWN");
            for (int i = 0; i < allData.length; i++) {
                System.out.println((i + 1) + "\t" + allData[i][1]);
            }
            System.out.println("\nAccording to the collected data, there are " + amountOfLine + " towns found.");*/
            visited = new boolean[allData.length];      // Check if current town is visited
            nearestTown(31, coordinates, allData);

            //retrieveAllTowns(31, coordinates, allData);
            //findPath(31, coordinates, allData);
            //System.out.println(distance);
            /*System.out.print("\nEnter the index of your origin and destination, separate by a comma only: ");
            indexs = input.nextLine().split(",");
            System.out.println("\nCalculating the distance from " + allData[Integer.parseInt(indexs[0]) - 1][1] + " to " + allData[Integer.parseInt(indexs[1]) - 1][1] + "...");
            // Calculations
            System.out.println("The direct distance is " + round(
                    DistanceCal.calDistance(
                            coordinates[Integer.parseInt(indexs[0]) - 1][0],
                            coordinates[Integer.parseInt(indexs[0]) - 1][1],
                            coordinates[Integer.parseInt(indexs[1]) - 1][0],
                            coordinates[Integer.parseInt(indexs[1]) - 1][1]), 3) + " km.");*/
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

    public static void retrieveAllTowns(int indexOfOrigin, double[][] coordinates, String[][] allDataOfTown) {
        /*System.out.print(allDataOfTown[indexOfOrigin - 1][1] + " ~ ");
        if(visited[indexOfOrigin-1] == false){
            visited[indexOfOrigin-1]=true;
            distance += nearestTown(indexOfOrigin,coordinates,allDataOfTown)[1];
            int currentTown = (int)nearestTown(indexOfOrigin,coordinates,allDataOfTown)[0];
            retrieveAllTowns(currentTown-1,coordinates,allDataOfTown);
        } else {

        }*/

        /*visited[indexOfOrigin - 1] = true;
        int visitedTown = 1;      // The amount of visited city
        int indexOfNearestTown = indexOfOrigin;
        double distanceOfNearestTown;
        double sum = 0.0;
        while (visitedTown != visited.length) {
            System.out.print(allDataOfTown[indexOfNearestTown - 1][1] + " ~ ");
            if (indexOfNearestTown == indexOfOrigin || visited[indexOfNearestTown - 1] == false) {
                indexOfNearestTown = (int) nearestTown(indexOfNearestTown, coordinates, allDataOfTown)[0];
                distanceOfNearestTown = nearestTown(indexOfNearestTown, coordinates, allDataOfTown)[1];
                System.out.println((allDataOfTown[indexOfNearestTown - 1][1] + "\t" + distanceOfNearestTown));
                sum += distanceOfNearestTown;
                visited[indexOfNearestTown - 1] = true;
                visitedTown++;
            } else if (indexOfNearestTown != indexOfOrigin && visited[indexOfNearestTown - 1] == true) {
                indexOfNearestTown = (int) nearestTown(indexOfNearestTown, coordinates, allDataOfTown)[2];
                distanceOfNearestTown = nearestTown(indexOfNearestTown, coordinates, allDataOfTown)[3];
                System.out.println((allDataOfTown[indexOfNearestTown - 1][1] + "\t" + distanceOfNearestTown));
                sum += distanceOfNearestTown;
                visited[indexOfNearestTown - 1] = true;
                visitedTown++;
            }
        }
        System.out.println(sum);*/
        /*for(int i = 0; i<allDataOfTown.length;i++){
            if(i!=indexOfOrigin-1){
                System.out.println("Distance from " + allDataOfTown[indexOfOrigin-1][1] + " to " + allDataOfTown[i][1] +
                " is " + round(DistanceCal.calDistance(
                        coordinates[indexOfOrigin-1][0],
                        coordinates[indexOfOrigin-1][1],
                        coordinates[i][0],
                        coordinates[i][1]),3) + " km."
                );
            }
        }*/
    }

    /*public static void findPath(int indexOfOrigin, double[][] coordinates, String[][] allDataOfTown) {
        visited[indexOfOrigin - 1] = true;
        int amountOfVisited = 1;
        int indexOfNext = indexOfOrigin;
        double distanceOfNext = 0.0;
        while (amountOfVisited != allDataOfTown.length) {
            System.out.print(allDataOfTown[indexOfNext - 1][1] + " - ");
            double[] nearest = nearestTown(indexOfNext, coordinates, allDataOfTown);
            if (visited[(int) nearest[0] - 1] == false) {
                indexOfNext = (int) nearest[0] - 1;
                distanceOfNext = nearest[1];
                System.out.println(allDataOfTown[indexOfNext - 1][1] + ": " + distanceOfNext);
                visited[indexOfNext - 1] = true;
                amountOfVisited++;
            } else if (visited[(int) nearest[0] - 1] == true && visited[(int) nearest[2] - 1] == false) {
                indexOfNext = (int) nearest[2] - 1;
                distanceOfNext = nearest[3];
                System.out.println(allDataOfTown[indexOfNext - 1][1] + ": " + distanceOfNext);
                visited[indexOfNext - 1] = true;
                amountOfVisited++;
            } else {
                System.out.println("\nInvalid.");
                amountOfVisited++;
            }
            distance += distanceOfNext;
        }
    }*/

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
        double tmpIndex, tmpDistance;
        // Create distance table
        for (int i = 0; i < result.length; i++) {
            if (i != indexOfOrigin - 1) {
                result[i][0] = i;
                result[i][1] = CalDistanceByIndex(indexOfOrigin, i + 1, coordinates);
            } else {
                result[i][0] = i;
                result[i][1] = 0;
            }
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
        for (int i = 0; i < result.length; i++) {
            //System.out.println(result[i][0] + "\t" + result[i][1]);
            System.out.println(allDataOfTown[indexOfOrigin - 1][1] + " ~ " + allDataOfTown[(int) result[i][0]][1] + ":\t" + result[i][1]);
        }
        return result;
    }

    public static double CalDistanceByIndex(int index1, int index2, double[][] coordinates) {
        return DistanceCal.calDistance(
                coordinates[index1 - 1][0],
                coordinates[index1 - 1][1],
                coordinates[index2 - 1][0],
                coordinates[index2 - 1][1]);
    }
}
