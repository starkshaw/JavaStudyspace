import java.util.LinkedList;

/**
 * Created by Zhenbang Xiao on 2015/1/16 0016.
 */
public class main {
    public static void main(String[] args) {
        LinkedList<Integer> test = new LinkedList<Integer>();
        for (int i = 0; i <= 5; i++) {
            test.add(i);
        }
        System.out.println(test);
        LinkedList<Integer> reversed = reverse(test);
        System.out.println(reversed);
    }

    public static LinkedList reverse(LinkedList example) {
        LinkedList reversed = new LinkedList();
        while(example.size()!=0){
            reversed.add(example.getLast());
            example.removeLast();
        }
        return reversed;
    }
}
