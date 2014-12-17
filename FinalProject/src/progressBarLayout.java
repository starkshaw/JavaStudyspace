/**
 * This class includes a method of generating progress bar on command line interface.
 * 
 * @author Zhenbang Xiao
 *
 */
public class progressBarLayout {
	/**
	 * Generate a progress bar depends on the ratio of current finished and total.
	 * 
	 * @param current
	 *            Current progress
	 * @param total
	 *            Total progress.
	 */
	public static void progressBar(int current, int total) {
		int width = 60;
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
		System.out.print("]\t" + calc.round((double) (current) / (double) (total) * 100, 2) + "%");// [" + current + "/" + total + "]");
		if (current == total) {
			System.out.println();
		}
	}
}
