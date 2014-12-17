import java.math.BigDecimal;

/**
 * 
 */

/**
 * @author stark
 *
 */
public class progressBarTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i <= 10000; i++) {
			progressBar(i, 10000);
		}

	}

	public static void progressBar(int current, int total) {
		int width = 50;
		System.out.print("\r[");
		int i = 0;
		while (i <= (int) ((double) (current) / (double) (total) * width)) {
			System.out.print("*");
			i++;
		}
		while (i <= width) {
			System.out.print(" ");
			i++;
		}
		System.out.print("]\t" + round((double) (current) / (double) (total) * 100, 2) + "% [" + current + "/" + total + "]");
		if (current == total) {
			System.out.println();
		}
	}

	public static String toSignificantFiguresString(BigDecimal bd, int significantFigures) {
		return String.format("%." + significantFigures + "G", bd);
	}

	public static double round(double value, int places) {
		// check if places to round up to is less than 0. Can't round to a negative number.
		if (places < 0){
			throw new IllegalArgumentException();
		}
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);

		return (double) tmp / factor;
	}
}
