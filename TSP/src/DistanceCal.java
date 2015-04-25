/**
 * Created by Zhenbang Xiao on 2015/4/25 0025.
 */

/**
 * This class was designated to calculate the distance of 2 known coordinates.
 */

// Import

import java.util.*;

public class DistanceCal {
    public static final double PI = 3.141592653589793;      // An approx. value of pi
    public static final double RADIUS = 6371;               // The mean radius of earth

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String coord1, coord2;      // Store user input data.
        double lon1, lat1, lon2, lat2;
        System.out.println("Positive values express East or North, while negative values express West or South.\n");
        System.out.print("Enter the first coordinates by longitude and latitude respectively, separate by a comma only: ");
        coord1 = read.nextLine();
        String[] tmp1 = coord1.split(",");
        lat1 = Double.parseDouble(tmp1[0]);
        lon1 = Double.parseDouble(tmp1[1]);
        System.out.print("\nEnter the second coordinates by longitude and latitude respectively, separate by a comma only: ");
        coord2 = read.nextLine();
        String[] tmp2 = coord2.split(",");
        lat2 = Double.parseDouble(tmp2[0]);
        lon2 = Double.parseDouble(tmp2[1]);
        System.out.println("\nThe distance of (" + lat1 + ", " +
                lon1 + ") to (" + lat2 + ", " + lon2 + ") is " +
                calDistance(lat1, lon1, lat2, lon2) + " km.");
    }

    /**
     * Calculate the distance between 2 places use "Haversine".
     *
     * @param lat1 Latitude of 1st place
     * @param lon1 Longitude of 1st place
     * @param lat2 Latitude of 2nd place
     * @param lon2 Longitude of 2nd place
     * @return The distance
     */
    public static double calDistance(double lat1, double lon1, double lat2, double lon2) {
        double dlon = Math.toRadians(lon2 - lon1);   // Delta longitude
        double dlat = Math.toRadians(lat2 - lat1);   // Delta latitude

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIUS * c;
    }
}
