import java.util.Scanner;

/**
 * Created by Zhenbang Xiao on 2015/1/16 0016.
 */
public class main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a, count = 0;
        System.out.print("The prime numbers less than: ");
        a = in.nextInt();
        for (int i = 2; i <= a; i++) {
            if (isPrimeNumber(i) == true) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * Determine if a integer is prime number or not.
     * @param num   The integer to be examined.
     * @return      If returns false represent the number is not prime, and vise versa.
     */
    public static boolean isPrimeNumber(int num) {
        boolean result = false;
        if (num == 2) {
            result = true;
        } else if (num < 2) {
            result = false;
        } else {
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    result = false;
                    break;
                } else {
                    result = true;
                }
            }
        }
        return result;
    }
}

