/**
 * This class includes summarize methods.
 * 
 * @author Zhenbang Xiao
 *
 */
public class summarize {
	/**
	 * Summarize the occupation information.
	 * 
	 * @param userInfo
	 *            The array which consists occupation information.
	 * @param columnOfOccupation
	 *            The column contain the name of occupation
	 * @param decreaseOrder
	 *            Set "true" as order amount of people in decreasing. Otherwise use default order.
	 * @return A string array consists with the name of occupation and the amount of people undergoing with it.
	 */
	public static String[][] summarizeOccupation(String[][] userInfo, int columnOfOccupation, boolean decreaseOrder) {
		String[] rawList = new String[userInfo.length];
		int countOfOccupation = 0;
		int countOfRow = 0;
		for (int i = 0; i <= rawList.length - 1; i++) {									// Copy the occupation row to here
			rawList[i] = userInfo[i][columnOfOccupation];
		}
		for (int i = 0; i <= rawList.length - 1; i++) {									// Find and replace same occupation into "!null!"
			for (int j = i + 1; j <= rawList.length - 1; j++) {
				if ((rawList[j].equals(rawList[i])) && (!rawList[j].equals("!null!"))) {
					rawList[j] = "!null!";
				}
			}
		}
		for (int i = 0; i <= rawList.length - 1; i++) {									// Summarize the amount of occupation
			if (!rawList[i].equals("!null!")) {
				countOfOccupation++;
			}
		}
		String[][] occupationList = new String[countOfOccupation][2];					// Store array to a new array
		for (int i = 0; i <= rawList.length - 1; i++) {
			if (!rawList[i].equals("!null!")) {
				occupationList[countOfRow][0] = rawList[i];
				countOfRow++;
			}
		}
		for (int i = 0; i <= occupationList.length - 1; i++) {							// Accumulate the amount of people in one occupation
			int countOfOccurrance = 1;
			for (int j = 0; j <= userInfo.length - 1; j++) {
				if (occupationList[i][0].equals(userInfo[j][columnOfOccupation])) {
					occupationList[i][1] = countOfOccurrance++ + "";
				}
			}
		}
		for (int i = 0; i <= occupationList.length - 1; i++) {
			// occupationList[i][0]=occupationList[i][0].replaceAll(lineSeparator, "");
			// occupationList[i][1] = NumberFormat.getInstance().parse(occupationList[i][1]).intValue();
		}
		// print2DString(occupationList);
		String tmpAmount;
		String tmpName;
		if (decreaseOrder == false) {
			return occupationList;
		} else {
			for (int i = 0; i <= occupationList.length - 1; i++) {
				for (int j = occupationList.length - 1; j > i; j--) {
					if (Integer.parseInt(occupationList[j][1]) > Integer.parseInt(occupationList[j - 1][1])) {
						tmpAmount = occupationList[j - 1][0];
						tmpName = occupationList[j - 1][1];
						occupationList[j - 1][0] = occupationList[j][0];
						occupationList[j - 1][1] = occupationList[j][1];
						occupationList[j][0] = tmpAmount;
						occupationList[j][1] = tmpName;
					}
				}
			}
			return occupationList;
		}
	}

	/**
	 * Summarize the amount of user in two genders.
	 * 
	 * @param userInfo
	 *            The separated user String array.
	 * @param columnOfGender
	 *            The column of gender info.
	 * @return The integer array consist of 2 genders, while [0] is male [1] is female.
	 */
	public static int[] summarizeGender(String[][] userInfo, int columnOfGender) {
		int[] genderList = new int[2];
		for (int i = 0; i <= userInfo.length - 1; i++) {
			if (userInfo[i][columnOfGender].equals("M")) {
				genderList[0]++;
			} else if (userInfo[i][columnOfGender].equals("F")) {
				genderList[1]++;
			}
		}
		return genderList;
	}

	/**
	 * Summarize the age distribution with gender.
	 * Age Sum Male Female
	 * 0~17 * * *
	 * 18~35 * * *
	 * 36~50 * * *
	 * 51~70 * * *
	 * 70~100 * * *
	 * 
	 * *indicates the data area
	 * 
	 * @param userInfo
	 *            User information string
	 * @param columnOfAge
	 *            Column indicates age
	 * @param columnOfGender
	 *            Column indicates gender
	 * @return A 5*3 integer array consists age distribution and sum of people in 2 genders.
	 *
	 */
	public static int[][] summarizeAge(String[][] userInfo, int columnOfAge, int columnOfGender) {
		// 0~17 18~35 36~50 51~70 71~100
		int[][] ageList = new int[5][3];
		// Age and gender summarize
		for (int i = 0; i <= userInfo.length - 1; i++) {
			if ((Integer.parseInt(userInfo[i][columnOfAge]) > 0) && (Integer.parseInt(userInfo[i][columnOfAge]) <= 17)) {
				ageList[0][0]++;
				if (userInfo[i][columnOfGender].equals("M")) {
					ageList[0][1]++;
				} else if (userInfo[i][columnOfGender].equals("F")) {
					ageList[0][2]++;
				}
			} else if ((Integer.parseInt(userInfo[i][columnOfAge]) > 17) && (Integer.parseInt(userInfo[i][columnOfAge]) <= 35)) {
				ageList[1][0]++;
				if (userInfo[i][columnOfGender].equals("M")) {
					ageList[1][1]++;
				} else if (userInfo[i][columnOfGender].equals("F")) {
					ageList[1][2]++;
				}
			} else if ((Integer.parseInt(userInfo[i][columnOfAge]) > 35) && (Integer.parseInt(userInfo[i][columnOfAge]) <= 50)) {
				ageList[2][0]++;
				if (userInfo[i][columnOfGender].equals("M")) {
					ageList[2][1]++;
				} else if (userInfo[i][columnOfGender].equals("F")) {
					ageList[2][2]++;
				}
			} else if ((Integer.parseInt(userInfo[i][columnOfAge]) > 50) && (Integer.parseInt(userInfo[i][columnOfAge]) <= 70)) {
				ageList[3][0]++;
				if (userInfo[i][columnOfGender].equals("M")) {
					ageList[3][1]++;
				} else if (userInfo[i][columnOfGender].equals("F")) {
					ageList[3][2]++;
				}
			} else if ((Integer.parseInt(userInfo[i][columnOfAge]) > 70) && (Integer.parseInt(userInfo[i][columnOfAge]) <= 100)) {
				ageList[4][0]++;
				if (userInfo[i][columnOfGender].equals("M")) {
					ageList[4][1]++;
				} else if (userInfo[i][columnOfGender].equals("F")) {
					ageList[4][2]++;
				}
			}
		}
		return ageList;
	}

	/**
	 * Get the ratio of two genders in one group of people. Use this method with
	 * summarizeAge(String[][] userInfo, int columnOfAge, int columnOfGender)
	 * 
	 * Age Male Female
	 * 0~17 * *
	 * 18~35 * *
	 * 36~50 * *
	 * 51~70 * *
	 * 70~100 * *
	 * 
	 * @param summarizedGenderWithAge
	 *            The output from summarizeAge(String[][] userInfo, int columnOfAge, int columnOfGender)
	 * @return A 5*2 String array consists the ratio of 2 genders.
	 * 
	 */
	public static String[][] getGenderRatio(int[][] summarizedGenderWithAge) {
		String[][] genderRatio = new String[summarizedGenderWithAge.length][2];
		for (int i = 0; i <= summarizedGenderWithAge.length - 1; i++) {
			for (int j = 1; j <= summarizedGenderWithAge[i].length - 1; j++) {
				if (summarizedGenderWithAge[i][j] * 100 / summarizedGenderWithAge[i][0] == 0) {
					genderRatio[i][j - 1] = 0 + "%";
				} else {
					genderRatio[i][j - 1] = calc.round(
							(float) ((float) summarizedGenderWithAge[i][j] * 100 / (float) summarizedGenderWithAge[i][0]), 2) + "%";
				}
			}
		}
		return genderRatio;
	}

	/**
	 * Get the age ratio from one section to all.
	 * 
	 * @param summarizedAge
	 *            The output from summarizeAge(String[][] userInfo, int columnOfAge, int columnOfGender)
	 * @param userAmount
	 *            The amount of users
	 * @return The string consists the percentage regarding to age distribution.
	 */
	public static String[] getAgeRatio(int[][] summarizedAge, int userAmount) {
		String[] ageRatio = new String[summarizedAge.length];
		for (int i = 0; i <= summarizedAge.length - 1; i++) {
			if ((float) ((float) summarizedAge[i][0] * 100) / (float) (userAmount) != 0) {
				ageRatio[i] = calc.round((float) ((float) summarizedAge[i][0] * 100) / (float) (userAmount), 2) + "%";
			} else {
				ageRatio[i] = 0 + "%";
			}
		}
		return ageRatio;
	}

	/**
	 * Get overall gender ratio.
	 * 
	 * @param summarizedGender
	 *            The output from summarizeGender(String[][] userInfo, int columnOfGender)
	 * @param userAmount
	 *            The amount of users
	 * @return The overall gender ratio. [0] is male, [1] is female.
	 */
	public static String[] getOverallGenderRatio(int[] summarizedGender, int userAmount) {
		String[] genderRatio = new String[summarizedGender.length];
		for (int i = 0; i <= summarizedGender.length - 1; i++) {
			genderRatio[i] = calc.round((float) ((float) summarizedGender[i] * 100) / (float) userAmount, 1) + "%";
		}
		return genderRatio;
	}
}
