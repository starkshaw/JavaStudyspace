/**
 * This class includes standard output regarding array.
 * 
 * @author Zhenbang Xiao
 *
 */
public class stdout {
	/**
	 * Print out the certain 2-dimension String array.
	 * 
	 * @param input
	 *            2D String type array.
	 */
	public static void print2DString(String input[][]) {
		for (int i = 0; i <= input.length - 1; i++) {
			for (int j = 0; j <= input[i].length - 1; j++) {
				if (j != input[i].length - 1) {			// Handle the following space of last element in each line
					System.out.print(input[i][j] + " ");
				} else {
					System.out.print(input[i][j]);
				}
			}
			System.out.println();						// Change line
		}
	}

	/**
	 * Print out the certain 2-dimension double array.
	 * 
	 * @param input
	 *            2D double type array.
	 */
	public static void print2DDouble(double input[][]) {
		for (int i = 0; i <= input.length - 1; i++) {
			for (int j = 0; j <= input[i].length - 1; j++) {
				if (j != input[i].length - 1) {			// Handle the following space of last element in each line
					System.out.print(input[i][j] + " ");
				} else {
					System.out.println(input[i][j]);
				}
			}
			System.out.println();						// Change line
		}
	}

	/**
	 * Print out the certain 1-dimension double array.
	 * 
	 * @param input
	 *            Double type array.
	 */
	public static void printDoubleArray(double input[]) {
		for (int i = 0; i <= input.length - 1; i++) {
			System.out.println(input[i]);
		}
	}

	/**
	 * Print out the occupation list.
	 * 
	 * @param occupationList
	 *            Summarized from summarizeOccupation(String[][] userInfo, int columnOfOccupation).
	 */
	public static void printOccupationList(String[][] occupationList) {
		System.out.println("\tAmount\tOccupation");
		for (int i = 0; i <= occupationList.length - 1; i++) {
			for (int j = occupationList[i].length - 1; j >= 0; j--) {
				if (j != 0) {
					System.out.print("\t" + occupationList[i][j]);
				} else {
					System.out.println("\t" + occupationList[i][j]);
				}
			}
		}
		System.out.println();
	}
}
