/**
 * Created by Zhenbang Xiao on 2015/2/24 0024.
 */

import java.io.*;
import java.util.*;

public class Tree implements Comparable<Tree> {
    public Node root;
    public int frequency = 0;

    public Tree() {
        root = null;
    }

    public int compareTo(Tree obj) {
        if (frequency - obj.frequency > 0) {
            return 1;
        } else if (frequency - obj.frequency < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    String path="error";

    public String getCode (char letter){
        path = Integer.toBinaryString((int)letter);
        return path;
    }

}
