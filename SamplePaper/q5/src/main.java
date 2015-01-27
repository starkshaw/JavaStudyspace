/**
 * Created by Zhenbang Xiao on 2015/1/17 0017.
 */
public class main {
    public static void main(String[] args) {
        int[] sample = {-5, -1, 2, -3, 0, -3, 3};
        System.out.println(maxContiguousSubsequence(sample, 3));
        //System.out.println(sumByIndex(sample, 0, 2));
    }

    public static int maxContiguousSubsequence(int[] input, int n) {
        int max = Integer.MIN_VALUE+1;
        for (int i = 0; i <= input.length - n; i++) {
            //System.out.println(input[i] + "~" + input[i + n - 1] + " :" + sumByIndex(input, i, i + n - 1));
            max=Math.max(max, sumByIndex(input, i, i + n - 1));
        }
        return max;
    }

    public static int sumByIndex(int[] input, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum = sum + input[i];
        }
        return sum;
    }
}
