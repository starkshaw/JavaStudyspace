import java.math.BigDecimal;

/**
 * This class includes the mathematical calculation methods.
 * 
 * @author Zhenbang Xiao
 *
 */
public class calc {
	/**
	 * Get the maximum integer value in certain column of a 2D String array.
	 * 
	 * @param originalArray
	 *            Target array.
	 * @param columnNo
	 *            Column number.
	 * @return The maximum integer value.
	 */
	public static int maxInt2D(String[][] originalArray, int columnNo) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i <= originalArray.length - 1; i++) {
			if (Integer.parseInt(originalArray[i][columnNo]) > max) {
				max = Integer.parseInt(originalArray[i][columnNo]);
			}
		}
		return max;
	}

	/**
	 * Get the average score of each movie.
	 * 
	 * @param FilmWithRate
	 *            Film consists rating information
	 * @param maxFilmID
	 *            The maximum Film ID
	 * @return A double array constructed by the average of each film.
	 */
	public static double[] getAverage(String[][] FilmWithRate, int maxFilmID) {
		String[][] sorted = new String[maxFilmID][3];
		double[] average = new double[maxFilmID];
		for (int i = 0; i <= sorted.length - 1; i++) {
			int sum = 0;
			int people = 0;
			for (int j = 0; j <= FilmWithRate.length - 1; j++) {
				if (i + 1 == Integer.parseInt(FilmWithRate[j][1])) {
					sum = sum + Integer.parseInt(FilmWithRate[j][2].substring(0, 1));
					people++;
				}
			}
			sorted[i][0] = i + 1 + "";
			sorted[i][1] = sum + "";
			sorted[i][2] = people + "";
			progressBarLayout.progressBar(i, maxFilmID - 1);
		}
		for (int i = 0; i <= average.length - 1; i++) {
			average[i] = (double) (Integer.parseInt(sorted[i][1])) / (double) (Integer.parseInt(sorted[i][2]));
		}
		// Test the sorted data
		// print2DString(sorted);
		return average;
	}

	/**
	 * Get a string of significant figured big decimal.
	 * 
	 * @param bd
	 *            Big decimal. Also could use BigDEcimal.valueOf(double) to evaluate double type.
	 * @param significantFigures
	 *            The amount of significant digit.
	 * @return The string of rounded value.
	 */
	public static String toSignificantFiguresString(BigDecimal bd, int significantFigures) {
		return String.format("%." + significantFigures + "G", bd);
	}

	/**
	 * Find the Pearson coefficient array by the rating of x (x_rate) and rating of y (y_rate), and x, y are 2 persons.
	 * 
	 * @param x_rate
	 *            The rating of x to a movie.
	 * @param y_rate
	 *            The rating of y to a movie.
	 * @param x_mean
	 *            The overall average of x's rating.
	 * @param y_mean
	 *            The overall average of y's rating.
	 * @return The Pearson coefficient, r[x][y].
	 */
	public static double[][] getPearsonCoefficient(double[] x_rate, double[] y_rate, double x_mean, double y_mean) {
		double[][] r = new double[x_rate.length][y_rate.length];
		double sigmaNumerator = 0.0, sigmaDenominator = 0.0, sigmaX_square = 0.0, sigmaY_square = 0.0;
		for (int i = 0; i <= x_rate.length - 1; i++) {
			for (int j = 0; j <= y_rate.length - 1; j++) {
				sigmaNumerator = sigmaNumerator + (x_rate[i] - x_mean) * (y_rate[j] - y_mean);
				sigmaX_square = sigmaX_square + Math.pow(x_rate[i] - x_mean, 2);
				sigmaY_square = sigmaY_square + Math.pow(y_rate[j] - y_mean, 2);
				sigmaDenominator = Math.sqrt(sigmaX_square * sigmaY_square);
				r[i][j] = sigmaNumerator / sigmaDenominator;
			}
		}
		return r;
	}

	/**
	 * Approximate a double value.
	 * 
	 * @param value
	 *            The raw double value.
	 * @param places
	 *            The digits after decimal point.
	 * @return The rounded value.
	 */
	public static double round(double value, int places) {
		// check if places to round up to is less than 0. Can't round to a negative number.
		if (places < 0) {
			throw new IllegalArgumentException();
		}
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);

		return (double) tmp / factor;
	}

	public static double[][] overallPearsonCoefficient(String[][] separatedRatings, int User1, int User2) {
		String[][] x_rate_string = stringOperator.getRatingsByUserID(User1, separatedRatings);
		String[][] y_rate_string = stringOperator.getRatingsByUserID(User2, separatedRatings);
		double[] x_rate = new double[x_rate_string.length - 1];
		double[] y_rate = new double[y_rate_string.length - 1];
		for (int i = 0; i <= x_rate_string.length - 2; i++) {		// Note that for getRAtingsByUserID the last value is length-2
			x_rate[i] = Double.parseDouble(x_rate_string[i][1]);
		}
		for (int i = 0; i <= y_rate_string.length - 2; i++) {
			y_rate[i] = Double.parseDouble(y_rate_string[i][1]);
		}
		double x_mean = Double.parseDouble(x_rate_string[x_rate_string.length - 1][1]);
		double y_mean = Double.parseDouble(y_rate_string[y_rate_string.length - 1][1]);
		// print2DDouble(getPearsonCoefficient(x_rate, y_rate, x_mean, y_mean));
		return getPearsonCoefficient(x_rate, y_rate, x_mean, y_mean);
	}
}
