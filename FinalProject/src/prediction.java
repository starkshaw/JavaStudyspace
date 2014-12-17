import java.io.File;

/**
 * The Final Project of CS210
 * 
 * @author Zhenbang Xiao
 * @author 13181866
 */
public class prediction {
	// Global Initialize

	/**
	 * @param args
	 *            -TEST List the separated TEST data.
	 * @param args
	 *            -USERS List the separated USERS data.
	 * @param args
	 *            -MOVIES List the separated MOVIES data.
	 * @param args
	 *            -RATINGS List the separated RATINGS data.
	 * @param args
	 *            -details Print out the amount of element in this project.
	 * @param args
	 *            -help Print out the help information.
	 * @param args
	 *            -reload Reload the cache, if the program behaves inappropriate try use this argument to fix.
	 * @param args
	 *            -save [filename] Save the result in a text-based file.
	 * 
	 */

	public static void main(String[] args) {
		// Initialize
		FileIO reader = new FileIO();
		String currentDir = new String(System.getProperty("user.dir"));				// Get the current directory
		String lineSeparator = System.getProperty("line.separator");
		File average_cache_RATINGS = new File(currentDir + "//data//average_ratings.txt");
		String[] AVERAGE_CACHE;														// Declare the cache
		String[] TEST = reader.load(currentDir + "//data//Test.txt");				// Load the summary
		// TEST (UserID, MovieID)
		String[] USERS = reader.load(currentDir + "//data//Users.txt");				// Load the user information
		// USERS (UserID, Age, Gender, Occupation, ZipCode)
		String[] MOVIES = reader.load(currentDir + "//data//Movies.txt");			// Load movie list
		// MOVIES (MovieID, MovieName, ReleaseDate, IMDB_URL, Genre_Boolean_Array[19])
		String[] RATINGS = reader.load(currentDir + "//data//Ratings.txt");			// Load ratings info
		// RATINGS (UserID, MovieID, Rating)
		// Separate TEST data
		String[][] separated_TEST = new String[TEST.length][2];						// Where 2 is the row value
		separated_TEST = stringOperator.separateString(TEST, "\t");
		// Separate USERS data
		String[][] separated_USERS = new String[USERS.length][5];
		separated_USERS = stringOperator.separateString(USERS, "\t");
		// Separate MOVIES data
		String[][] separated_MOVIES = new String[MOVIES.length][23];				// Where 23 is the row value includes
		separated_MOVIES = stringOperator.separateString(MOVIES, "\t");						// genre boolean values
		// Separate RATINGS data
		String[][] separated_RATINGS = new String[RATINGS.length][3];
		separated_RATINGS = stringOperator.separateString(RATINGS, "\t");
		// Find the amounts
		int countUSERS = calc.maxInt2D(separated_USERS, 0);
		int countMOVIES = calc.maxInt2D(separated_MOVIES, 0);
		// Summarize the gender
		int genderInfo[] = summarize.summarizeGender(separated_USERS, 2);
		String[] overallGenderRatio = summarize.getOverallGenderRatio(genderInfo, countUSERS);
		// Summarize the age
		int ageInfo[][] = summarize.summarizeAge(separated_USERS, 1, 2);
		String[][] ageGenderRatio = summarize.getGenderRatio(ageInfo);
		String[] ageRatio = summarize.getAgeRatio(ageInfo, countUSERS);
		// Summarize occupations
		String[][] occupationList = summarize.summarizeOccupation(separated_USERS, 3, true);
		// Mathematics variables
		double[] averageRATINGS = new double[countMOVIES];							// The average of each movie
		double[][] r;																// Pearson Coefficient
		// Argument handle
		if (args.length == 0) {
			// Find the average of rating of each movie based on the amount of user rated
			if (average_cache_RATINGS.exists() == true && average_cache_RATINGS.isDirectory() == true) {	// Handle cache
				average_cache_RATINGS.delete();
			} else if (average_cache_RATINGS.exists() == false) {
				System.out.println("\nLoading...");
				averageRATINGS = calc.getAverage(separated_RATINGS, countMOVIES);
				try {
					reader.save(currentDir + "//data//average_ratings.txt", stringOperator.convertDoubleToString(averageRATINGS));
					System.out.println("New cache created.\n");
				} catch (Exception e) {
					System.out.println(e.getClass());
				}
			}
			AVERAGE_CACHE = reader.load(currentDir + "//data//average_ratings.txt");
			System.out.println("Cache loaded.");
			// Find the Pearson Coefficient
			// String[][] test = getRatingsByUserID(1, separated_RATINGS);
			// Debug section
			// stdout.print2DString(separated_TEST);
			// stdout.print2DString(separated_USERS);
			// stdout.print2DString(separated_MOVIES);
			// stdout.print2DString(separated_RATINGS);
			// stdout.print2DString(separateString(RATINGS,"\t",3));
			// System.out.println(countUSERS);
			// System.out.println(countMOVIES);
			// stdout.printDoubleArray(averageRATINGS);
			// summarizeGender(separated_USERS,2,countUSERS);
			// stdout.print2DString(ageGenderRatio);
			// System.out.println(ageRatio[0] + ageRatio[1] + ageRatio[2] + ageRatio[3] + ageRatio[4]);
			// stdout.print2DString(test);
		} else if (args[0].equals("-TEST")) {
			stdout.print2DString(separated_TEST);
		} else if (args[0].equals("-USERS")) {
			stdout.print2DString(separated_USERS);
		} else if (args[0].equals("-MOVIES")) {
			stdout.print2DString(separated_MOVIES);
		} else if (args[0].equals("-RATINGS")) {
			stdout.print2DString(separated_RATINGS);
		} else if (args[0].equals("-help")) {
			System.out
					.println("\n   java prediction [-TEST] | [-USERS] | [-MOVIES] | [-RATINGS] | [-details] | [-help] | [-reload] | [-save filename]");
			System.out.println("  -TEST \tList the separated TEST data.\n");
			System.out.println("  -USERS \tList the separated USERS data.\n");
			System.out.println("  -MOVIES \tList the separated MOVIES data.\n");
			System.out.println("  -RATINGS \tList the separated RATINGS data.\n");
			System.out.println("  -details \tPrint out the amount of element in this project.\n");
			System.out.println("  -help \tPrint out the help information.\n");
			System.out.println("  -reload \tIf the program behaves inappropriate, try use this argument to repair.\n");
			System.out.println("  -save \tSave the result in a text-based file.");
		} else if (args[0].equals("-details")) {
			System.out.println("\nIn this project there are\n\n\t" + countMOVIES + " movies,\n\n\t" + countUSERS + " users,\n\n\t"
					+ separated_RATINGS.length + " rating information have been found.\n\nIn the user part there are\n\n\rcalct"
					+ genderInfo[0] + " [" + overallGenderRatio[0] + "] males and " + genderInfo[1] + " [" + overallGenderRatio[1]
					+ "] females.\n\nIn the age distribution part there are\n\n\t" + ageInfo[0][0] + " [" + ageRatio[0]
					+ "] people from 0 to 17 (M: " + ageInfo[0][1] + " [" + ageGenderRatio[0][0] + "], F: " + ageInfo[0][2] + " ["
					+ ageGenderRatio[0][1] + "]),\n\n\t" + ageInfo[1][0] + " [" + ageRatio[1] + "] people from 18 to 35 (M: "
					+ ageInfo[1][1] + " [" + ageGenderRatio[1][0] + "], F: " + ageInfo[1][2] + " [" + ageGenderRatio[1][1] + "]),\n\n\t"
					+ ageInfo[2][0] + " [" + ageRatio[2] + "] people from 36 to 50 (M: " + ageInfo[2][1] + " [" + ageGenderRatio[2][0]
					+ "], F: " + ageInfo[2][2] + " [" + ageGenderRatio[2][1] + "]),\n\n\t" + ageInfo[3][0] + " [" + ageRatio[3]
					+ "] people from 51 to 70 (M: " + ageInfo[3][1] + " [" + ageGenderRatio[3][0] + "], F: " + ageInfo[3][2] + " ["
					+ ageGenderRatio[3][1] + "]),\n\n\t" + ageInfo[4][0] + " [" + ageRatio[4] + "] people from 71 to 100 (M: "
					+ ageInfo[4][1] + " [" + ageGenderRatio[4][0] + "], F: " + ageInfo[4][2] + " [" + ageGenderRatio[4][1]
					+ "]).\n\nIn the occupation distribution part there are\n");
			stdout.printOccupationList(occupationList);
		} else if (args[0].equals("-reload")) {
			if (average_cache_RATINGS.exists() == true) {							// Remove exist cache anyway
				average_cache_RATINGS.delete();
			}
			System.out.println("\nLoading...");
			averageRATINGS = calc.getAverage(separated_RATINGS, countMOVIES);			// Create new cache
			try {
				reader.save(currentDir + "//data//average_ratings.txt", stringOperator.convertDoubleToString(averageRATINGS));
				System.out.println("New cache created.\n");
			} catch (Exception e) {
				System.out.println(e.getClass());
			}
		} else {																	// Exception handle
			String argsString = new String("");
			for (int i = 0; i <= args.length - 1; i++) {
				if (i != args.length - 1) {			// Handle the following space of last element in each line
					argsString = argsString + args[i] + " ";
				} else {
					argsString = argsString + args[i];
				}
			}
			System.out.println("\nArgument: \"" + argsString + "\" not found. Use \"-help\" for help.\n");
		}
	}
}
