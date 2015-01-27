import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Zhenbang Xiao on 2015/1/17 0017.
 */
public class main {
    public static void main(String[] args) {
        String[] sample = {"Dermot", "Tommy", "Siobhain", "Lisa", "Daniel", "Levin", "Jonatan", "Stark", "Aoife"};
        String[] sorted = sortByUniqueChar(sample);
        System.out.println(Arrays.toString(sorted));
    }

    public static int countUniqueChar(String in) {
        int count = 0;
        for (int i = 0; i <= in.length() - 1; i++) {
            for (int j = in.length() - 1; j > i; j--) {
                if (in.charAt(i) == in.charAt(j)) {
                    count++;                        // Count the amount of distinct repeating letter
                    break;
                }
            }
        }
        return in.length() - count;
    }

    public static String[] sortByUniqueChar(String[] in) {
        boolean swap;
        do {
            swap = false;
            for (int i = 0; i < in.length - 1; i++) {
                String current = in[i];
                if (countUniqueChar(in[i]) > countUniqueChar(in[i + 1])) {
                    in[i] = in[i + 1];
                    in[i + 1] = current;
                    swap = true;
                }
            }
        } while (swap == true);
        return in;
        /*LinkedList<Integer> indexOfUniqueChars = new LinkedList<Integer>();                       // Index from unique chars.
        String[] result = new String[in.length];
        int count = 0;
        for (int i = 0; i <= in.length - 1; i++) {              // Create an array consists by the amount of unique character
            indexOfUniqueChars.add(countUniqueChar(in[i]));
            //indexOfUniqueChars.add(i);
        }
        System.out.println(indexOfUniqueChars.size());
        System.out.println(indexOfUniqueChars);

        for (int i = 0; i <= result.length - 1; i++) {
            int min = indexOfUniqueChars.get(0);
            for (int j = 0; j <= indexOfUniqueChars.size() - 1; j++) {
                if (min > indexOfUniqueChars.get(j)) {
                    min = indexOfUniqueChars.get(j);
                    indexOfUniqueChars.remove(j);
                }if (min == indexOfUniqueChars.get(j)) {
                    min = indexOfUniqueChars.get(j);
                    indexOfUniqueChars.remove(j);
                }
            }
            System.out.println(indexOfUniqueChars);
        }
        while((count!=in.length-1)&&(indexOfUniqueChars.size()!=0)){
            int min = indexOfUniqueChars.getLast();
            for (int j = 0; j <= indexOfUniqueChars.size() - 1; j++) {
                if (min > indexOfUniqueChars.get(j)) {
                    min = indexOfUniqueChars.get(j);
                    indexOfUniqueChars.remove(j);
                }
            }
            System.out.println(indexOfUniqueChars);
        }*/
        //return result;
    }

    public static int getMinimum(LinkedList<Integer> list) {
        int min = list.getFirst();
        for (int i = 0; i <= list.size() - 1; i++) {
            if (list.get(i) < min) {
                min = list.get(i);
            }
        }
        return min;
    }
}
