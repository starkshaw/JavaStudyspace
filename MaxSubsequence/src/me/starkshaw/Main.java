package me.starkshaw;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] test = {4, -6, -2, 8, 2, 7, -2};
        System.out.println(Arrays.toString(findMaxSubsequence(test)));
    }

    public static int[] findMaxSubsequence(int[] sequence) {
        int posCount = 0;
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] >= 0) {
                posCount++;
            }
        }
        if (posCount == sequence.length) {
            return sequence;
        } else if (posCount == 0) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < sequence.length; i++) {
                if (sequence[i] > max) {
                    max = sequence[i];
                }
            }
            int[] result = new int[1];
            result[0] = max;
            return result;
        } else {
            int startIndex = 0, endIndex = 1, sum = 0, max = Integer.MIN_VALUE;
            for (int i = 0; i < sequence.length; i++) {
                while (sequence[endIndex] >= 0) {
                    startIndex = i;
                    sum += sequence[i];
                    endIndex++;
                }
                if (sum > max) {
                    max = sum;
                }
            }
            int[] result = new int[endIndex - startIndex];
            int count = 0;
            for (int i = startIndex; i <= endIndex; i++) {
                result[count] = sequence[i];
                count++;
            }
            return result;
        }
    }
}
