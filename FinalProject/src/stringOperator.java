/**
 * This class includes operations related to string.
 * 
 * @author Zhenbang Xiao
 *
 */
public class stringOperator {
	/**
	 * Separate the combined string into parts.
	 * 
	 * @param originalArray
	 *            The original array which to be handle.
	 * @param splitKeyword
	 *            The keyword which separates the element in original array.
	 * @return The separated string.
	 */
	public static String[][] separateString(String[] originalArray, String splitKeyword) {
		String temp_getColumn[] = originalArray[0].split(splitKeyword);
		String[][] targetArray = new String[originalArray.length][temp_getColumn.length];
		for (int i = 0; i <= targetArray.length - 1; i++) {
			String temp[] = originalArray[i].split(splitKeyword);
			for (int j = 0; j <= targetArray[i].length - 1; j++) {
				targetArray[i][j] = temp[j];
			}
		}
		return targetArray;
	}

	/**
	 * Get the ratings by one user by ID.
	 * !Be advised! The last row includes the mean value! Do not count last value!
	 * 
	 * @param UserID
	 *            The ID of certain user.
	 * @param separatedRatings
	 *            The separated ratings array. In this case it is 'separated_RATINGS'.
	 * @return A 2D string array consist by MovieID and ratings. The last row column 2 is the average value.
	 */
	public static String[][] getRatingsByUserID(int UserID, String[][] separatedRatings) {
		int amount = 0;												// Count the amount of ratings of UserID
		int count = 0;												// Count the row of ratingsList
		int sum = 0;
		double mean = 0.0;
		for (int i = 0; i <= separatedRatings.length - 1; i++) {
			if (separatedRatings[i][0].equals(UserID + "")) {
				amount++;
			}
		}
		String[][] ratingsList = new String[amount + 1][2];
		for (int i = 0; i <= separatedRatings.length - 1; i++) {
			if (separatedRatings[i][0].equals(UserID + "")) {
				ratingsList[count][0] = separatedRatings[i][1];		// The column of MovieID
				ratingsList[count][1] = separatedRatings[i][2];		// The column of rating
				count++;
			}
		}
		for (int i = 0; i <= ratingsList.length - 2; i++) {			// Notice the different! It is ratingsList.length-2!
			sum = sum + Integer.parseInt(ratingsList[i][1]);
		}
		mean = (double) (sum) / (double) (amount);					// Do not count last row! They are:
		ratingsList[ratingsList.length - 1][0] = "Mean";			// retingsList[ratingsLIst.length-1][0]
		ratingsList[ratingsList.length - 1][1] = mean + "";			// retingsList[ratingsLIst.length-1][1]
		return ratingsList;
	}

	/**
	 * Merge 2D String array into String array with a unique split keyword.
	 * 
	 * @param originalString
	 *            The original 2D String array.
	 * @param splitKeyword
	 *            The unique keyword.
	 * @return Merged String array.
	 */
	public static String[] merge2DString(String[][] originalString, String splitKeyword) {
		String[] targetString = new String[originalString.length];
		for (int i = 0; i <= originalString.length - 1; i++) {
			for (int j = 0; j <= originalString[i].length - 1; j++) {
				if (j != originalString[i].length - 1) {
					targetString[i] = originalString[i][j] + splitKeyword;
				} else if (j == originalString[i].length - 1) {
					targetString[i] = originalString[i][j];
				}
			}
		}
		return targetString;
	}

	/**
	 * Convert double array to String array.
	 * 
	 * @param originalDouble
	 *            Original double type array.
	 * @return Target array.
	 */
	public static String[] convertDoubleToString(double[] originalDouble) {
		String[] targetString = new String[originalDouble.length];
		for (int i = 0; i <= originalDouble.length - 1; i++) {
			targetString[i] = originalDouble[i] + "";
		}
		return targetString;
	}
}
